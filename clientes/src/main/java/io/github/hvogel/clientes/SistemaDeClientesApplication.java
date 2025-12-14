package io.github.hvogel.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SistemaDeClientesApplication {

	private final Environment env;

	public SistemaDeClientesApplication(Environment env) {
		this.env = env;
	}

	@GetMapping("/")
	public String getAmbiente() {
		String ambienteAtual = "DES";

		if (env.getActiveProfiles().length > 0) {
			ambienteAtual = env.getActiveProfiles()[0].toUpperCase();
		}

		String appName = env.getProperty("application.env");
		return String.format("Ambiente: %s | versao: %s", ambienteAtual, appName);
	}

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeClientesApplication.class, args);
	}
}
