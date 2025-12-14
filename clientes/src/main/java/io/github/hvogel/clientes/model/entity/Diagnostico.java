package io.github.hvogel.clientes.model.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import io.github.hvogel.clientes.infra.IBaseEntity;

@Entity
@Table(name = "diagnostico", schema = "meusservicos")
public class Diagnostico implements IBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 150)
	@NotNull(message = "{campo.diagnostico.descricao.obrigatorio}")
	private String descricao;
	
	@ManyToOne
    @JoinColumn(name = "servico_prestado_id")
    private ServicoPrestado servicoPrestado;

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

	public ServicoPrestado getServicoPrestado() {
		return servicoPrestado;
	}

	public void setServicoPrestado(ServicoPrestado servicoPrestado) {
		this.servicoPrestado = servicoPrestado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, id, servicoPrestado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diagnostico other = (Diagnostico) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(servicoPrestado, other.servicoPrestado);
	}

	@Override
	public String toString() {
		return "Diagnostico [id=" + id + ", descricao=" + descricao + ", servicoPrestado=" + servicoPrestado + "]";
	}

}
