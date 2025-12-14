package io.github.hvogel.clientes.model.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.hvogel.clientes.enums.StatusChamado;
import io.github.hvogel.clientes.infra.IBaseEntity;

@Entity
@Table(name = "chamado", schema = "meusservicos")
public class Chamado implements IBaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 150)
	@NotNull(message = "{campo.chamado.descricao.obrigatorio}")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@Column(name = "data", updatable = false)	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@Column(name = "local_acontecimento", nullable = false, length = 150)
	@NotNull(message = "{campo.chamado.local.obrigatorio}")
	private String localAcontecimento;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false, length = 1)
	private StatusChamado status;

	@PrePersist
	public void prePersist() {
		setData(LocalDate.now());
	}

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		return Objects.hash(cliente, data, descricao, id, localAcontecimento, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chamado other = (Chamado) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(data, other.data)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(localAcontecimento, other.localAcontecimento) && status == other.status;
	}

	@Override
	public String toString() {
		return "Chamado [id=" + id + ", descricao=" + descricao + ", cliente=" + cliente + ", data=" + data
				+ ", localAcontecimento=" + localAcontecimento + ", status=" + status + "]";
	}
	
}
