package io.github.hvogel.clientes.rest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.hvogel.clientes.enums.StatusChamado;


public class ChamadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull(message = "{campo.chamado.descricao.obrigatorio}")
	private String descricao;
	
	private Integer clienteId;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@NotNull(message = "{campo.chamado.local.obrigatorio}")
	private String localAcontecimento;
	
	private StatusChamado status;

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

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getLocalAcontecimento() {
		return localAcontecimento;
	}

	public void setLocalAcontecimento(String localAcontecimento) {
		this.localAcontecimento = localAcontecimento;
	}

	public StatusChamado getStatus() {
		return status;
	}

	public void setStatus(StatusChamado status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clienteId, data, descricao, id, localAcontecimento, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChamadoDTO other = (ChamadoDTO) obj;
		return Objects.equals(clienteId, other.clienteId) && Objects.equals(data, other.data)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(localAcontecimento, other.localAcontecimento) && status == other.status;
	}

	@Override
	public String toString() {
		return "ChamadoDTO [id=" + id + ", descricao=" + descricao + ", clienteId=" + clienteId + ", data=" + data
				+ ", localAcontecimento=" + localAcontecimento + ", status=" + status + "]";
	}
	
	
}
