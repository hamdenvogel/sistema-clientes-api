package io.github.hvogel.clientes.model.entity;

import java.math.BigDecimal;
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
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.hvogel.clientes.enums.StatusServico;

import java.io.Serializable;

@Entity
@Table(name = "servicoprestado", schema = "meusservicos")
public class ServicoPrestado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@Column
	private BigDecimal valor;

	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataConclusao;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 1)
	private StatusServico status;

	@Transient
	private String captcha;

	@ManyToOne
	@JoinColumn(name = "id_prestador")
	private Prestador prestador;

	@Column(name = "tipo", nullable = false, length = 1)
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "id_natureza")
	private Natureza natureza;

	@ManyToOne
	@JoinColumn(name = "id_atividade")
	private Atividade atividade;

	@Column(name = "local_atendimento", nullable = true, length = 150)
	private String localAtendimento;

	@Column(name = "conclusao", nullable = true, length = 150)
	private String conclusao;

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDate getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDate dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public StatusServico getStatus() {
		return status;
	}

	public void setStatus(StatusServico status) {
		this.status = status;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Natureza getNatureza() {
		return natureza;
	}

	public void setNatureza(Natureza natureza) {
		this.natureza = natureza;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
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
		return Objects.hash(atividade, captcha, cliente, conclusao, data, dataConclusao, descricao, id,
				localAtendimento, natureza, prestador, status, tipo, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServicoPrestado other = (ServicoPrestado) obj;
		return Objects.equals(atividade, other.atividade) && Objects.equals(captcha, other.captcha)
				&& Objects.equals(cliente, other.cliente) && Objects.equals(conclusao, other.conclusao)
				&& Objects.equals(data, other.data) && Objects.equals(dataConclusao, other.dataConclusao)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(localAtendimento, other.localAtendimento) && Objects.equals(natureza, other.natureza)
				&& Objects.equals(prestador, other.prestador) && status == other.status
				&& Objects.equals(tipo, other.tipo) && Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "ServicoPrestado [id=" + id + ", descricao=" + descricao + ", cliente=" + cliente + ", valor=" + valor
				+ ", data=" + data + ", dataConclusao=" + dataConclusao + ", status=" + status + ", captcha="
				+ captcha + ", prestador=" + prestador + ", tipo=" + tipo + ", natureza=" + natureza + ", atividade="
				+ atividade + ", localAtendimento=" + localAtendimento + ", conclusao=" + conclusao + "]";
	}

}
