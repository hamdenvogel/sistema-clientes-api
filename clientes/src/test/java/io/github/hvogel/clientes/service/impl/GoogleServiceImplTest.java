package io.github.hvogel.clientes.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import io.github.hvogel.clientes.exception.RegraNegocioException;
import io.github.hvogel.clientes.model.entity.Captcha;
import io.github.hvogel.clientes.rest.dto.GoogleRecaptchaDTO;
import io.github.hvogel.clientes.service.ReCaptchaAttemptService;
import io.github.hvogel.clientes.service.ValidadorService;

@ExtendWith(MockitoExtension.class)
class GoogleServiceImplTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private ReCaptchaAttemptService reCaptchaAttemptService;

    @Mock
    private Captcha captcha;

    @Mock
    private ValidadorService validadorService;

    private GoogleServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new GoogleServiceImpl(request, reCaptchaAttemptService, captcha, validadorService);
    }

    @Test
    void testValidarToken_Success() {
        String token = "valid-token";
        String clientIp = "127.0.0.1";

        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn(clientIp);
        when(reCaptchaAttemptService.isBlocked(clientIp)).thenReturn(false);
        when(captcha.getSecret()).thenReturn("secret");

        GoogleRecaptchaDTO googleResponse = new GoogleRecaptchaDTO();
        googleResponse.setSuccess(true);

        try (MockedConstruction<RestTemplate> mockedRestTemplate = Mockito.mockConstruction(RestTemplate.class,
                (mock, context) -> {
                    when(mock.getForObject(any(URI.class), eq(GoogleRecaptchaDTO.class))).thenReturn(googleResponse);
                })) {

            GoogleRecaptchaDTO result = service.validarToken(token);

            assertNotNull(result);
            assertTrue(result.isSuccess());
            verify(reCaptchaAttemptService).reCaptchaSucceeded(clientIp);
        }
    }

    @Test
    void testValidarToken_NullResponse() {
        String token = "valid-token";
        String clientIp = "127.0.0.1";

        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn(clientIp);
        when(reCaptchaAttemptService.isBlocked(clientIp)).thenReturn(false);
        when(captcha.getSecret()).thenReturn("secret");

        try (MockedConstruction<RestTemplate> mockedRestTemplate = Mockito.mockConstruction(RestTemplate.class,
                (mock, context) -> {
                    when(mock.getForObject(any(URI.class), eq(GoogleRecaptchaDTO.class))).thenReturn(null);
                })) {

            assertThrows(RegraNegocioException.class, () -> service.validarToken(token));
        }
    }

    private void assertTrue(boolean success) {
        assertEquals(true, success);
    }

    @Test
    void testValidarToken_Blocked() {
        String token = "token";
        String clientIp = "127.0.0.1";

        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn(clientIp);
        when(reCaptchaAttemptService.isBlocked(clientIp)).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> service.validarToken(token));
    }

    @Test
    void testValidarToken_InvalidToken() {
        String token = "invalid-token";
        doThrow(new RegraNegocioException("Invalid token")).when(validadorService).validarTokenGoogle(token);

        assertThrows(ResponseStatusException.class, () -> service.validarToken(token));
    }

    @Test
    void testObterClientIP_XForwardedFor() {
        when(request.getHeader("X-Forwarded-For")).thenReturn("10.0.0.1, 10.0.0.2");

        String ip = service.obterClientIP();

        assertEquals("10.0.0.1", ip);
    }

    @Test
    void testObterClientIP_RemoteAddr() {
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        String ip = service.obterClientIP();

        assertEquals("127.0.0.1", ip);
    }

    @Test
    void testZerarTentativasMalSucedidas() {
        service.zerarTentativasMalSucedidas();
        verify(reCaptchaAttemptService).reCaptchaDeleteAllEntriesInTheCache();
    }

    @Test
    void testObterInformacaoNumeroDeTentativasDeAcessoAoCapctha() {
        when(reCaptchaAttemptService.reCapthaSizeOfCacheVersusTotalAttempts()).thenReturn("Info");

        String info = service.obterInformacaoNumeroDeTentativasDeAcessoAoCapctha();

        assertEquals("Info", info);
    }

    @Test
    void testValidarCaptchaPreenchido_Success() {
        assertDoesNotThrow(() -> service.validarCaptchaPreenchido("captcha"));
    }

    @Test
    void testValidarCaptchaPreenchido_Null() {
        assertThrows(RegraNegocioException.class, () -> service.validarCaptchaPreenchido(null));
    }

    @Test
    void testValidarCaptchaPreenchido_Empty() {
        assertThrows(RegraNegocioException.class, () -> service.validarCaptchaPreenchido(""));
    }
}
