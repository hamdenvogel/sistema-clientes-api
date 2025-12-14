package io.github.hvogel.clientes.rest.dto;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class ProdutoDTO {
	
	private Integer id;
	
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;
	
	@NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal preco;
	
	private String marca;
	
	private String modelo;
	
	private Integer anoFabricacao;
	
	private Integer anoModelo;
	
	private InfoResponseDTO infoResponseDTO;

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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
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

	public InfoResponseDTO getInfoResponseDTO() {
		return infoResponseDTO;
	}

	public void setInfoResponseDTO(InfoResponseDTO infoResponseDTO) {
		this.infoResponseDTO = infoResponseDTO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anoFabricacao, anoModelo, descricao, id, infoResponseDTO, marca, modelo, preco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoDTO other = (ProdutoDTO) obj;
		return Objects.equals(anoFabricacao, other.anoFabricacao) && Objects.equals(anoModelo, other.anoModelo)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(infoResponseDTO, other.infoResponseDTO) && Objects.equals(marca, other.marca)
				&& Objects.equals(modelo, other.modelo) && Objects.equals(preco, other.preco);
	}

	@Override
	public String toString() {
		return "ProdutoDTO [id=" + id + ", descricao=" + descricao + ", preco=" + preco + ", marca=" + marca
				+ ", modelo=" + modelo + ", anoFabricacao=" + anoFabricacao + ", anoModelo=" + anoModelo
				+ ", infoResponseDTO=" + infoResponseDTO + "]";
	}
	
	
}
