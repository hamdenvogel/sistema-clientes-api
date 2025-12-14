package io.github.hvogel.clientes.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.hvogel.clientes.enums.EPerfil;
import io.github.hvogel.clientes.model.entity.Perfil;
import io.github.hvogel.clientes.model.entity.Usuario;
import io.github.hvogel.clientes.model.repository.PerfilRepository;
import io.github.hvogel.clientes.model.repository.UsuarioRepository;
import io.github.hvogel.clientes.response.jwt.JwtResponse;
import io.github.hvogel.clientes.response.jwt.MessageResponse;
import io.github.hvogel.clientes.rest.dto.CredenciaisDTO;
import io.github.hvogel.clientes.rest.dto.SignupDTO;
import io.github.hvogel.clientes.security.jwt.JwtUtils;
import io.github.hvogel.clientes.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private static final String ROLE_NOT_FOUND_MSG = "Error: Role is not found.";

  private final AuthenticationManager authenticationManager;
  private final UsuarioRepository userRepository;
  private final PerfilRepository roleRepository;
  private final PasswordEncoder encoder;
  private final JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody CredenciaisDTO credenciaisDTO) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(credenciaisDTO.getLogin(), credenciaisDTO.getSenha()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.gerarToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .toList();

    return ResponseEntity.ok(new JwtResponse(jwt,
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupDTO signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    Usuario user = new Usuario(signUpRequest.getUsername(),
        signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Perfil> roles = new HashSet<>();

    if (strRoles == null) {
      Perfil userRole = roleRepository.findByNome(EPerfil.ROLE_USER)
          .orElseThrow(() -> new IllegalStateException(ROLE_NOT_FOUND_MSG));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Perfil adminRole = roleRepository.findByNome(EPerfil.ROLE_ADMIN)
                .orElseThrow(() -> new IllegalStateException(ROLE_NOT_FOUND_MSG));
            roles.add(adminRole);

            break;
          case "mod":
            Perfil modRole = roleRepository.findByNome(EPerfil.ROLE_MODERATOR)
                .orElseThrow(() -> new IllegalStateException(ROLE_NOT_FOUND_MSG));
            roles.add(modRole);

            break;
          default:
            Perfil userRole = roleRepository.findByNome(EPerfil.ROLE_USER)
                .orElseThrow(() -> new IllegalStateException(ROLE_NOT_FOUND_MSG));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
