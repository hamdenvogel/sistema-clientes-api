package io.github.hvogel.clientes.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.hvogel.clientes.exception.SenhaInvalidaException;
import io.github.hvogel.clientes.model.entity.Usuario;
import io.github.hvogel.clientes.model.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

	private final PasswordEncoder encoder;
	private final UsuarioRepository repository;

	public Usuario salvar(Usuario usuario) {
		boolean exists = repository.existsByUsername(usuario.getUsername());
		if (exists) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"O usuário " + usuario.getUsername() + " já existe.");
		}
		return repository.save(usuario);
	}

	public UserDetails autenticar(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getUsername());
		boolean senhasBatem = encoder.matches(usuario.getPassword(), user.getPassword());

		if (senhasBatem) {
			return user;
		}

		throw new SenhaInvalidaException();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Logger logger = LoggerFactory.getLogger(UserDetails.class);
		logger.info("init loadUserByUsername {} user", username);
		Usuario usuario = repository
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));
		logger.info("retrieving loadUserByUsername");

		return User
				.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.roles("USER")
				.build();
	}
}
