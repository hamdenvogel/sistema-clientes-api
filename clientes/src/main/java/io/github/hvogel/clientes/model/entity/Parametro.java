package io.github.hvogel.clientes.model.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.hvogel.clientes.infra.IBaseEntity;



@Entity
@Table(name = "parametro", schema = "meusservicos")
public class Parametro implements IBaseEntity{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 300)
	@NotNull(message = "{campo.parametro.descricao.obrigatorio}")
	private String descricao;
	
	@Column(name="data", columnDefinition = "DATE")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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
		Parametro other = (Parametro) obj;
		return Objects.equals(data, other.data) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Parametro [id=" + id + ", descricao=" + descricao + ", data=" + data + "]";
	}
		
}
