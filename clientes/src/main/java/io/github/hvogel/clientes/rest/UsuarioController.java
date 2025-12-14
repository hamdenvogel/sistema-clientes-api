package io.github.hvogel.clientes.rest;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.hvogel.clientes.model.entity.Usuario;
import io.github.hvogel.clientes.rest.dto.TotalUsuariosDTO;
import io.github.hvogel.clientes.service.TotalUsuariosService;
import io.github.hvogel.clientes.service.impl.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final TotalUsuariosService totalUsuariosService;    
    
    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder,
			TotalUsuariosService totalUsuariosService) {
		super();
		this.usuarioService = usuarioService;
		this.passwordEncoder = passwordEncoder;
		this.totalUsuariosService = totalUsuariosService;
	}

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario ){
        String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);
        return usuarioService.salvar(usuario);
    }
    
    @GetMapping("totalUsuarios")
	public TotalUsuariosDTO obterTotalUsuarios() {
		return TotalUsuariosDTO.builder()
				.withTotalUsuarios(totalUsuariosService.obterTotalUsuarios())
				.build();
	}
}

