package io.github.hvogel.clientes.model.entity;

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
@Table(name = "equipamento", schema = "meusservicos")
public class Equipamento implements IBaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	@NotNull(message = "{campo.equipamento.descricao.obrigatorio}")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "servico_prestado_id")
	private ServicoPrestado servicoPrestado;

	@Column(name = "marca", length = 50)
	private String marca;

	@Column(name = "modelo", length = 50)
	private String modelo;

	@Column(name = "anoFabricacao")
	private Integer anoFabricacao;

	@Column(name = "anoModelo")
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

	public ServicoPrestado getServicoPrestado() {
		return servicoPrestado;
	}

	public void setServicoPrestado(ServicoPrestado servicoPrestado) {
		this.servicoPrestado = servicoPrestado;
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
	public String toString() {
		return "Equipamento [id=" + id + ", descricao=" + descricao + ", servicoPrestado=" + servicoPrestado
				+ ", marca=" + marca + ", modelo=" + modelo + ", anoFabricacao=" + anoFabricacao + ", anoModelo="
				+ anoModelo + "]";
	}

}
