package io.github.hvogel.clientes.rest.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import io.github.hvogel.clientes.enums.StatusServico;

public class ServicoPrestadoDTO {
	
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	
	@NotEmpty(message = "{campo.preco.obrigatorio}")
	private String preco;
	
	@NotEmpty(message = "{campo.data.obrigatorio}")
	private String data;
	
	@NotNull(message = "{campo.cliente.obrigatorio}")
	private Integer idCliente;
	
	private StatusServico status;
	
	private InfoResponseDTO infoResponseDTO;
	
	private String captcha;
	
	private Integer idPrestador;
	
	@NotEmpty(message = "{campo.tipo.servico.obrigatorio}")
	private String tipo;
	
	@NotNull(message = "{campo.natureza.descricao.obrigatorio}")
	private Long idNatureza;
	
	@NotNull(message = "{campo.atividade.descricao.obrigatorio}")
	private Long idAtividade;
	
	private String localAtendimento;
	
	private String conclusao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public StatusServico getStatus() {
		return status;
	}

	public void setStatus(StatusServico status) {
		this.status = status;
	}

	public InfoResponseDTO getInfoResponseDTO() {
		return infoResponseDTO;
	}

	public void setInfoResponseDTO(InfoResponseDTO infoResponseDTO) {
		this.infoResponseDTO = infoResponseDTO;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public Integer getIdPrestador() {
		return idPrestador;
	}

	public void setIdPrestador(Integer idPrestador) {
		this.idPrestador = idPrestador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdNatureza() {
		return idNatureza;
	}

	public void setIdNatureza(Long idNatureza) {
		this.idNatureza = idNatureza;
	}

	public Long getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(Long idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getLocalAtendimento() {
		return localAtendimento;
	}

	public void setLocalAtendimento(String localAtendimento) {
		this.localAtendimento = localAtendimento;
	}

	public String getConclusao() {
		return conclusao;
	}

	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(captcha, conclusao, data, descricao, idAtividade, idCliente, idNatureza, idPrestador,
				infoResponseDTO, localAtendimento, preco, status, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServicoPrestadoDTO other = (ServicoPrestadoDTO) obj;
		return Objects.equals(captcha, other.captcha) && Objects.equals(conclusao, other.conclusao)
				&& Objects.equals(data, other.data) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(idAtividade, other.idAtividade) && Objects.equals(idCliente, other.idCliente)
				&& Objects.equals(idNatureza, other.idNatureza) && Objects.equals(idPrestador, other.idPrestador)
				&& Objects.equals(infoResponseDTO, other.infoResponseDTO)
				&& Objects.equals(localAtendimento, other.localAtendimento) && Objects.equals(preco, other.preco)
				&& status == other.status && Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "ServicoPrestadoDTO [descricao=" + descricao + ", preco=" + preco + ", data=" + data + ", idCliente="
				+ idCliente + ", status=" + status + ", infoResponseDTO=" + infoResponseDTO + ", captcha=" + captcha
				+ ", idPrestador=" + idPrestador + ", tipo=" + tipo + ", idNatureza=" + idNatureza + ", idAtividade="
				+ idAtividade + ", localAtendimento=" + localAtendimento + ", conclusao=" + conclusao + "]";
	}
		
}
