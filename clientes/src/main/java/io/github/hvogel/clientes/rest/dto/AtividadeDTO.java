package io.github.hvogel.clientes.rest.dto;

import java.io.Serializable;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;



public class AtividadeDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull(message = "{campo.atividade.descricao.obrigatorio}")	
	private String descricao;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtividadeDTO other = (AtividadeDTO) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "AtividadeDTO [id=" + id + ", descricao=" + descricao + "]";
	}	
	
}
