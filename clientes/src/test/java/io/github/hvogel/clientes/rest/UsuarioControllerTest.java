package io.github.hvogel.clientes.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.hvogel.clientes.model.entity.Usuario;
import io.github.hvogel.clientes.service.impl.UsuarioService;
import io.github.hvogel.clientes.util.HttpServletReqUtil;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioService service;

    @MockBean
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @MockBean
    private io.github.hvogel.clientes.service.TotalUsuariosService totalUsuariosService;

    @MockBean
    private HttpServletReqUtil reqUtil;

    @MockBean
    private io.github.hvogel.clientes.security.jwt.JwtUtils jwtUtils;

    @MockBean
    private io.github.hvogel.clientes.service.impl.UserDetailsServiceImpl userDetailsService;

    @MockBean
    private io.github.hvogel.clientes.security.jwt.AuthEntryPointJwt unauthorizedHandler;

    @Test
    void testSalvar() {
        Usuario usuario = new Usuario();
        usuario.setUsername("user");
        usuario.setPassword("password");

        when(service.salvar(any(Usuario.class))).thenReturn(usuario);

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated()));
    }

    @Test
    void testObterTotalUsuarios() {
        when(totalUsuariosService.obterTotalUsuarios()).thenReturn(100L);

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> mockMvc.perform(get("/api/usuarios/totalUsuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.totalUsuarios")
                        .value(100)));
    }
}
