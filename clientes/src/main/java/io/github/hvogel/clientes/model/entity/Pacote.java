package io.github.hvogel.clientes.model.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "pacote", schema = "meusservicos")
public class Pacote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 300)
	private String descricao;

	@Column(nullable = false, length = 300)
	private String justificativa;

	@Column(name = "data", columnDefinition = "DATE", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	@Column(name = "data_previsao", columnDefinition = "DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPrevisao;

	@Column(name = "data_conclusao", columnDefinition = "DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataConclusao;

	@Column(name = "status", nullable = false, length = 1)
	private String status;

	@PrePersist
	public void prePersist() {
		setData(LocalDate.now());
	}

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

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDate getDataPrevisao() {
		return dataPrevisao;
	}

	public void setDataPrevisao(LocalDate dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}

	public LocalDate getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDate dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, dataConclusao, dataPrevisao, descricao, id, justificativa, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pacote other = (Pacote) obj;
		return Objects.equals(data, other.data) && Objects.equals(dataConclusao, other.dataConclusao)
				&& Objects.equals(dataPrevisao, other.dataPrevisao) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && Objects.equals(justificativa, other.justificativa)
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Pacote [id=" + id + ", descricao=" + descricao + ", justificativa=" + justificativa + ", data=" + data
				+ ", dataPrevisao=" + dataPrevisao + ", dataConclusao=" + dataConclusao + ", status=" + status
				+ "]";
	}

}
