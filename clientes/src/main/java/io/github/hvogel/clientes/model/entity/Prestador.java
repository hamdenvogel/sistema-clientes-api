package io.github.hvogel.clientes.model.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

@Entity
@Table(name = "prestador", schema = "meusservicos")
public class Prestador implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;

	@Column(nullable = false, length = 11)
	@NotNull(message = "{campo.cpf.obrigatorio}")
	@CPF(message = "{campo.cpf.invalido}")
	private String cpf;

	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@Column(name = "pix", length = 50)
	private String pix;

	@Column(name = "avaliacao")
	@NotNull(message = "{campo.avaliacao.obrigatorio}")
	@Range(min = 1, max = 5, message = "A avaliação deve estar entre 1 a 5.")
	private Integer avaliacao;

	@ManyToOne
	@JoinColumn(name = "id_profissao")
	@NotNull(message = "{campo.profissao.obrigatorio}")
	private Profissao profissao;

	@Column(name = "email", length = 200)
	private String email;

	@Transient
	private String captcha;

	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}

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

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avaliacao, captcha, cpf, dataCadastro, email, id, nome, pix, profissao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestador other = (Prestador) obj;
		return Objects.equals(avaliacao, other.avaliacao) && Objects.equals(captcha, other.captcha)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(pix, other.pix)
				&& Objects.equals(profissao, other.profissao);
	}

	@Override
	public String toString() {
		return "Prestador [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dataCadastro=" + dataCadastro + ", pix="
				+ pix + ", avaliacao=" + avaliacao + ", profissao=" + profissao + ", email=" + email + ", captcha="
				+ captcha + "]";
	}

}
