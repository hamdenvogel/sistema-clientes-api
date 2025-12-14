package io.github.hvogel.clientes.rest.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotEmpty;


public class ProfissaoDTO {
	
	private Integer id;
	
	@NotEmpty(message = "{campo.profissao.codigo.obrigatorio}")
	private Integer codigo;
	
	@NotEmpty(message = "{campo.profissao.descricao.obrigatorio}")
	private String descricao;
	
	private InfoResponseDTO infoResponseDTO;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public InfoResponseDTO getInfoResponseDTO() {
		return infoResponseDTO;
	}

	public void setInfoResponseDTO(InfoResponseDTO infoResponseDTO) {
		this.infoResponseDTO = infoResponseDTO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, descricao, id, infoResponseDTO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfissaoDTO other = (ProfissaoDTO) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && Objects.equals(infoResponseDTO, other.infoResponseDTO);
	}

	@Override
	public String toString() {
		return "ProfissaoDTO [id=" + id + ", codigo=" + codigo + ", descricao=" + descricao + ", infoResponseDTO="
				+ infoResponseDTO + "]";
	}
		
}
