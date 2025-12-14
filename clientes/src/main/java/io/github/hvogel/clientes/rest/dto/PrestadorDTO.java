package io.github.hvogel.clientes.rest.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;

import io.github.hvogel.clientes.service.validation.ExtendedEmail;


public class PrestadorDTO {
	private Integer id;
	
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@NotNull(message = "{campo.cpf.obrigatorio}")
	@CPF(message="{campo.cpf.invalido}")
	private String cpf;
	
	private String pix;
	
	@NotNull(message = "{campo.avaliacao.obrigatorio}")
	@Range(min = 1, max = 5, message = "A avaliação deve estar entre 1 a 5.")
	private Integer avaliacao;
	
	@NotNull(message = "{campo.profissao.obrigatorio}")
	private Integer idProfissao;
	
	private String captcha;
	
	@ExtendedEmail
	private String email;
	
	private InfoResponseDTO infoResponseDTO;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPix() {
		return pix;
	}

	public void setPix(String pix) {
		this.pix = pix;
	}

	public Integer getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Integer avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Integer getIdProfissao() {
		return idProfissao;
	}

	public void setIdProfissao(Integer idProfissao) {
		this.idProfissao = idProfissao;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InfoResponseDTO getInfoResponseDTO() {
		return infoResponseDTO;
	}

	public void setInfoResponseDTO(InfoResponseDTO infoResponseDTO) {
		this.infoResponseDTO = infoResponseDTO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avaliacao, captcha, cpf, email, id, idProfissao, infoResponseDTO, nome, pix);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrestadorDTO other = (PrestadorDTO) obj;
		return Objects.equals(avaliacao, other.avaliacao) && Objects.equals(captcha, other.captcha)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(idProfissao, other.idProfissao)
				&& Objects.equals(infoResponseDTO, other.infoResponseDTO) && Objects.equals(nome, other.nome)
				&& Objects.equals(pix, other.pix);
	}

	@Override
	public String toString() {
		return "PrestadorDTO [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", pix=" + pix + ", avaliacao="
				+ avaliacao + ", idProfissao=" + idProfissao + ", captcha=" + captcha + ", email=" + email
				+ ", infoResponseDTO=" + infoResponseDTO + "]";
	}
		
}
