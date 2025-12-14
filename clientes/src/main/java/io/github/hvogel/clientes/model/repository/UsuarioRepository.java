package io.github.hvogel.clientes.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.hvogel.clientes.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>  {
	Optional<Usuario> findByUsername(String username);
	boolean existsByUsername(String username);
	long count();
	boolean existsByEmail(String email);
}
