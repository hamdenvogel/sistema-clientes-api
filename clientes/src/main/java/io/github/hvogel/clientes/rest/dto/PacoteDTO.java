package io.github.hvogel.clientes.rest.dto;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PacoteDTO {
	private Integer id;

	@NotEmpty(message = "{campo.descricao.pacote.obrigatorio}")
	private String descricao;

	@NotEmpty(message = "{campo.justificativa.pacote.obrigatorio}")
	private String justificativa;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPrevisao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataConclusao;

	@NotEmpty(message = "{campo.status.pacote.obrigatorio}")
	private String status;

	private InfoResponseDTO infoResponseDTO;

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

	public InfoResponseDTO getInfoResponseDTO() {
		return infoResponseDTO;
	}

	public void setInfoResponseDTO(InfoResponseDTO infoResponseDTO) {
		this.infoResponseDTO = infoResponseDTO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, dataConclusao, dataPrevisao, descricao, id, infoResponseDTO, justificativa, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PacoteDTO other = (PacoteDTO) obj;
		return Objects.equals(data, other.data) && Objects.equals(dataConclusao, other.dataConclusao)
				&& Objects.equals(dataPrevisao, other.dataPrevisao) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && Objects.equals(infoResponseDTO, other.infoResponseDTO)
				&& Objects.equals(justificativa, other.justificativa) && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "PacoteDTO [id=" + id + ", descricao=" + descricao + ", justificativa=" + justificativa + ", data="
				+ data + ", dataPrevisao=" + dataPrevisao + ", dataConclusao=" + dataConclusao + ", status="
				+ status + ", infoResponseDTO=" + infoResponseDTO + "]";
	}

}
