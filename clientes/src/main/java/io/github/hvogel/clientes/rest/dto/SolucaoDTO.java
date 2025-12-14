package io.github.hvogel.clientes.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class SolucaoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "{campo.solucao.descricao.obrigatorio}")
	private String descricao;
	
	private Integer servicoPrestadoId;

	private BigDecimal valor;
	
	private BigDecimal desconto;

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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(desconto, descricao, id, servicoPrestadoId, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolucaoDTO other = (SolucaoDTO) obj;
		return Objects.equals(desconto, other.desconto) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && Objects.equals(servicoPrestadoId, other.servicoPrestadoId)
				&& Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "SolucaoDTO [id=" + id + ", descricao=" + descricao + ", servicoPrestadoId=" + servicoPrestadoId
				+ ", valor=" + valor + ", desconto=" + desconto + "]";
	}
			
}
