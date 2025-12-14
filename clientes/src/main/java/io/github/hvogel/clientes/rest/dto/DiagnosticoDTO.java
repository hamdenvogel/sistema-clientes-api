package io.github.hvogel.clientes.rest.dto;

import java.io.Serializable;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class DiagnosticoDTO implements Serializable {	
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "{campo.diagnostico.descricao.obrigatorio}")
	private String descricao;
	
	private Integer servicoPrestadoId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getServicoPrestadoId() {
		return servicoPrestadoId;
	}

	public void setServicoPrestadoId(Integer servicoPrestadoId) {
		this.servicoPrestadoId = servicoPrestadoId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, id, servicoPrestadoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiagnosticoDTO other = (DiagnosticoDTO) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(servicoPrestadoId, other.servicoPrestadoId);
	}

	@Override
	public String toString() {
		return "DiagnosticoDTO [id=" + id + ", descricao=" + descricao + ", servicoPrestadoId=" + servicoPrestadoId
				+ "]";
	}	

}
