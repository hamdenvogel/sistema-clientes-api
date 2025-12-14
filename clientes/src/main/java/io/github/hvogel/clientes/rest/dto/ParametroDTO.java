package io.github.hvogel.clientes.rest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


public class ParametroDTO implements Serializable {	
	
	private static final long serialVersionUID = 1L;

	private Integer id;	

	@NotNull(message = "{campo.parametro.descricao.obrigatorio}")
	private String descricao;	

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, descricao, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParametroDTO other = (ParametroDTO) obj;
		return Objects.equals(data, other.data) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ParametroDTO [id=" + id + ", descricao=" + descricao + ", data=" + data + "]";
	}
		
}
