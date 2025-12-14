package io.github.hvogel.clientes.rest.dto;

import java.io.Serializable;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class EquipamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(message = "{campo.equipamento.descricao.obrigatorio}")
	private String descricao;

	private Integer servicoPrestadoId;

	private String marca;

	private String modelo;

	private Integer anoFabricacao;

	private Integer anoModelo;

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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Integer getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anoFabricacao, anoModelo, descricao, id, marca, modelo, servicoPrestadoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipamentoDTO other = (EquipamentoDTO) obj;
		return Objects.equals(anoFabricacao, other.anoFabricacao) && Objects.equals(anoModelo, other.anoModelo)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(marca, other.marca) && Objects.equals(modelo, other.modelo)
				&& Objects.equals(servicoPrestadoId, other.servicoPrestadoId);
	}

	@Override
	public String toString() {
		return "EquipamentoDTO [id=" + id + ", descricao=" + descricao + ", servicoPrestadoId=" + servicoPrestadoId
				+ ", marca=" + marca + ", modelo=" + modelo + ", anoFabricacao=" + anoFabricacao + ", anoModelo="
				+ anoModelo + "]";
	}

}
