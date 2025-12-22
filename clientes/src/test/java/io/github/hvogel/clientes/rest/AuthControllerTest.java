package io.github.hvogel.clientes.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.hvogel.clientes.enums.EPerfil;
import io.github.hvogel.clientes.model.entity.Perfil;
import io.github.hvogel.clientes.model.repository.PerfilRepository;
import io.github.hvogel.clientes.model.repository.UsuarioRepository;
import io.github.hvogel.clientes.rest.dto.CredenciaisDTO;
import io.github.hvogel.clientes.rest.dto.SignupDTO;
import io.github.hvogel.clientes.service.impl.UserDetailsImpl;

import io.github.hvogel.clientes.test.base.BaseControllerTest;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest extends BaseControllerTest {

        @MockBean
        private AuthenticationManager authenticationManager;

        @MockBean
        private UsuarioRepository userRepository;

        @MockBean
        private PerfilRepository roleRepository;

        @MockBean
        private PasswordEncoder encoder;

        @Test
        void testAuthenticateUser() throws Exception {
                CredenciaisDTO credenciais = new CredenciaisDTO();
                credenciais.setLogin("user");
                credenciais.setSenha("password");

                UserDetailsImpl userDetails = new UserDetailsImpl(1L, "user", "user@test.com", "password",
                                Collections.emptyList());
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);

                when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                                .thenReturn(authentication);
                when(jwtUtils.gerarToken(authentication)).thenReturn("jwt-token");

                mockMvc.perform(post("/api/auth/signin")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(credenciais)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.token").value("jwt-token"))
                                .andExpect(jsonPath("$.username").value("user"));
        }

        @Test
        void testRegisterUser_Success() throws Exception {
                SignupDTO signupDTO = new SignupDTO();
                signupDTO.setUsername("newuser");
                signupDTO.setEmail("newuser@test.com");
                signupDTO.setPassword("password");
                signupDTO.setRole(Collections.singleton("user"));

                when(userRepository.existsByUsername("newuser")).thenReturn(false);
                when(userRepository.existsByEmail("newuser@test.com")).thenReturn(false);
                when(encoder.encode("password")).thenReturn("encodedPassword");

                Perfil perfilUser = new Perfil();
                perfilUser.setNome(EPerfil.ROLE_USER);
                when(roleRepository.findByNome(EPerfil.ROLE_USER)).thenReturn(Optional.of(perfilUser));

                mockMvc.perform(post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupDTO)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.message").value("User registered successfully!"));
        }

        @Test
        void testRegisterUser_UsernameTaken() throws Exception {
                SignupDTO signupDTO = new SignupDTO();
                signupDTO.setUsername("existinguser");
                signupDTO.setEmail("test@test.com");
                signupDTO.setPassword("password");

                when(userRepository.existsByUsername("existinguser")).thenReturn(true);

                mockMvc.perform(post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupDTO)))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.message").value("Error: Username is already taken!"));
        }

        @Test
        void testRegisterUser_EmailInUse() throws Exception {
                SignupDTO signupDTO = new SignupDTO();
                signupDTO.setUsername("newuser");
                signupDTO.setEmail("existing@test.com");
                signupDTO.setPassword("password");

                when(userRepository.existsByUsername("newuser")).thenReturn(false);
                when(userRepository.existsByEmail("existing@test.com")).thenReturn(true);

                mockMvc.perform(post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupDTO)))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.message").value("Error: Email is already in use!"));
        }

        @Test
        void testRegisterUser_NoRole() throws Exception {
                SignupDTO signupDTO = new SignupDTO();
                signupDTO.setUsername("noroleuser");
                signupDTO.setEmail("norole@test.com");
                signupDTO.setPassword("password");
                signupDTO.setRole(null);

                when(userRepository.existsByUsername("noroleuser")).thenReturn(false);
                when(userRepository.existsByEmail("norole@test.com")).thenReturn(false);
                when(encoder.encode("password")).thenReturn("encodedPassword");

                Perfil perfilUser = new Perfil();
                perfilUser.setNome(EPerfil.ROLE_USER);
                when(roleRepository.findByNome(EPerfil.ROLE_USER)).thenReturn(Optional.of(perfilUser));

                mockMvc.perform(post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupDTO)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.message").value("User registered successfully!"));
        }

        @Test
        void testRegisterUser_AdminRole() throws Exception {
                SignupDTO signupDTO = new SignupDTO();
                signupDTO.setUsername("adminuser");
                signupDTO.setEmail("admin@test.com");
                signupDTO.setPassword("password");
                signupDTO.setRole(Collections.singleton("admin"));

                when(userRepository.existsByUsername("adminuser")).thenReturn(false);
                when(userRepository.existsByEmail("admin@test.com")).thenReturn(false);
                when(encoder.encode("password")).thenReturn("encodedPassword");

                Perfil perfilAdmin = new Perfil();
                perfilAdmin.setNome(EPerfil.ROLE_ADMIN);
                when(roleRepository.findByNome(EPerfil.ROLE_ADMIN)).thenReturn(Optional.of(perfilAdmin));

                mockMvc.perform(post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupDTO)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.message").value("User registered successfully!"));
        }

        @Test
        void testRegisterUser_ModRole() throws Exception {
                SignupDTO signupDTO = new SignupDTO();
                signupDTO.setUsername("moduser");
                signupDTO.setEmail("mod@test.com");
                signupDTO.setPassword("password");
                signupDTO.setRole(Collections.singleton("mod"));

                when(userRepository.existsByUsername("moduser")).thenReturn(false);
                when(userRepository.existsByEmail("mod@test.com")).thenReturn(false);
                when(encoder.encode("password")).thenReturn("encodedPassword");

                Perfil perfilMod = new Perfil();
                perfilMod.setNome(EPerfil.ROLE_MODERATOR);
                when(roleRepository.findByNome(EPerfil.ROLE_MODERATOR)).thenReturn(Optional.of(perfilMod));

                mockMvc.perform(post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupDTO)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.message").value("User registered successfully!"));
        }

        @Test
        void testRegisterUser_RoleNotFound() throws Exception {
                SignupDTO signupDTO = new SignupDTO();
                signupDTO.setUsername("noroleuser");
                signupDTO.setEmail("norole@test.com");
                signupDTO.setPassword("password");
                signupDTO.setRole(Collections.singleton("admin"));

                when(userRepository.existsByUsername("noroleuser")).thenReturn(false);
                when(userRepository.existsByEmail("norole@test.com")).thenReturn(false);
                when(encoder.encode("password")).thenReturn("encodedPassword");

                when(roleRepository.findByNome(EPerfil.ROLE_ADMIN)).thenReturn(Optional.empty());

                String content = objectMapper.writeValueAsString(signupDTO);
                org.junit.jupiter.api.Assertions.assertThrows(Exception.class,
                                () -> mockMvc.perform(post("/api/auth/signup")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(content)));
        }

        @Test
        void testRegisterUser_RoleUserNotFound_NullRoles() throws Exception {
                SignupDTO signupDTO = new SignupDTO();
                signupDTO.setUsername("nouserrole");
                signupDTO.setEmail("nouserrole@test.com");
                signupDTO.setPassword("password");
                signupDTO.setRole(null);

                when(userRepository.existsByUsername("nouserrole")).thenReturn(false);
                when(userRepository.existsByEmail("nouserrole@test.com")).thenReturn(false);
                when(encoder.encode("password")).thenReturn("encodedPassword");

                when(roleRepository.findByNome(EPerfil.ROLE_USER)).thenReturn(Optional.empty());

                String content = objectMapper.writeValueAsString(signupDTO);
                org.junit.jupiter.api.Assertions.assertThrows(Exception.class,
                                () -> mockMvc.perform(post("/api/auth/signup")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(content)));
        }

        @Test
        void testRegisterUser_RoleModNotFound() throws Exception {
                SignupDTO signupDTO = new SignupDTO();
                signupDTO.setUsername("nomodrole");
                signupDTO.setEmail("nomodrole@test.com");
                signupDTO.setPassword("password");
                signupDTO.setRole(Collections.singleton("mod"));

                when(userRepository.existsByUsername("nomodrole")).thenReturn(false);
                when(userRepository.existsByEmail("nomodrole@test.com")).thenReturn(false);
                when(encoder.encode("password")).thenReturn("encodedPassword");

                when(roleRepository.findByNome(EPerfil.ROLE_MODERATOR)).thenReturn(Optional.empty());

                String content = objectMapper.writeValueAsString(signupDTO);
                org.junit.jupiter.api.Assertions.assertThrows(Exception.class,
                                () -> mockMvc.perform(post("/api/auth/signup")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(content)));
        }

        @Test
        void testRegisterUser_RoleDefaultUserNotFound() throws Exception {
                SignupDTO signupDTO = new SignupDTO();
                signupDTO.setUsername("defaultuser");
                signupDTO.setEmail("default@test.com");
                signupDTO.setPassword("password");
                signupDTO.setRole(Collections.singleton("user"));

                when(userRepository.existsByUsername("defaultuser")).thenReturn(false);
                when(userRepository.existsByEmail("default@test.com")).thenReturn(false);
                when(encoder.encode("password")).thenReturn("encodedPassword");

                when(roleRepository.findByNome(EPerfil.ROLE_USER)).thenReturn(Optional.empty());

                String content = objectMapper.writeValueAsString(signupDTO);
                org.junit.jupiter.api.Assertions.assertThrows(Exception.class,
                                () -> mockMvc.perform(post("/api/auth/signup")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(content)));
        }

        @Test
        void testAuthenticateUser_Failure() throws Exception {
                CredenciaisDTO credenciais = new CredenciaisDTO();
                credenciais.setLogin("user");
                credenciais.setSenha("wrongpassword");

                when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                                .thenThrow(new org.springframework.security.authentication.BadCredentialsException(
                                                "Bad credentials"));

                String content = objectMapper.writeValueAsString(credenciais);
                org.junit.jupiter.api.Assertions.assertThrows(Exception.class,
                                () -> mockMvc.perform(post("/api/auth/signin")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(content)));
        }
}
