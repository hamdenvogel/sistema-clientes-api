package io.github.hvogel.clientes.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import io.github.hvogel.clientes.exception.SenhaInvalidaException;
import io.github.hvogel.clientes.model.entity.Usuario;
import io.github.hvogel.clientes.model.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UsuarioService service;

    @Test
    void testSalvar_Success() {
        Usuario usuario = Usuario.builder().username("user").password("pass").build();
        when(repository.existsByUsername(anyString())).thenReturn(false);
        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario saved = service.salvar(usuario);
        assertNotNull(saved);
        verify(repository).save(usuario);
    }

    @Test
    void testSalvar_UserExists() {
        Usuario usuario = Usuario.builder().username("user").password("pass").build();
        when(repository.existsByUsername(anyString())).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> service.salvar(usuario));
    }

    @Test
    void testAutenticar_Success() {
        Usuario usuario = Usuario.builder().username("user").password("pass").build();
        Usuario storedUser = Usuario.builder().username("user").password("encodedPass").build();

        when(repository.findByUsername("user")).thenReturn(Optional.of(storedUser));
        when(encoder.matches("pass", "encodedPass")).thenReturn(true);

        UserDetails userDetails = service.autenticar(usuario);
        assertNotNull(userDetails);
        assertEquals("user", userDetails.getUsername());
    }

    @Test
    void testAutenticar_SenhaInvalida() {
        Usuario usuario = Usuario.builder().username("user").password("pass").build();
        Usuario storedUser = Usuario.builder().username("user").password("encodedPass").build();

        when(repository.findByUsername("user")).thenReturn(Optional.of(storedUser));
        when(encoder.matches("pass", "encodedPass")).thenReturn(false);

        assertThrows(SenhaInvalidaException.class, () -> service.autenticar(usuario));
    }

    @Test
    void testLoadUserByUsername_Success() {
        Usuario storedUser = Usuario.builder().username("user").password("encodedPass").build();
        when(repository.findByUsername("user")).thenReturn(Optional.of(storedUser));

        UserDetails userDetails = service.loadUserByUsername("user");
        assertNotNull(userDetails);
        assertEquals("user", userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsername_NotFound() {
        when(repository.findByUsername("unknown")).thenReturn(Optional.empty());

        UsernameNotFoundException ex = assertThrows(UsernameNotFoundException.class,
                () -> service.loadUserByUsername("unknown"));
        assertEquals("Login não encontrado.", ex.getMessage());
    }

    @Test
    void testSalvar_UserExists_Message() {
        Usuario usuario = Usuario.builder().username("user").password("pass").build();
        when(repository.existsByUsername(anyString())).thenReturn(true);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> service.salvar(usuario));
        assertTrue(ex.getReason().contains("já existe"));
    }
}
