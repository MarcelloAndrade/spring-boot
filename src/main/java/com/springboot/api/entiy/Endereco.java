package com.springboot.api.entiy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "tb_endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long idEndereco;
	
	@Column(name = "CEP", nullable = false, length = 10)
	private String cep;
	
	@Column(name = "LOGRADOURO", nullable = false)
	private String logradouro;
	
	@Column(name = "BAIRRO", nullable = false, length = 120)
	private String bairro;
	
	@Column(name = "NUMERO", nullable = false, length = 10)
	private String numero;
	
	@Column(name = "COMPLEMENTO", nullable = false, length = 120)
	private String complemento;
	
	@Column(name = "CIDADE", nullable = false, length = 120)
	private String cidade;
	
	@Column(name = "ESTADO", nullable = false, length = 120)
	private String estado;
	
	@Column(name = "DATA_CADASTRO", nullable = false)
	private Date dataCadastro;
	
	@Column(name = "DATA_ATUALIZACAO", nullable = false)
	private Date dataAtualizacao;

	@PrePersist
	public void prePersist() {
		Date date = new Date();
		dataCadastro = date;
		dataAtualizacao = date;
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
}
