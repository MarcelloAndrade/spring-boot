package com.springboot.api.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class EnderecoDto {

	private Long idEndereco;

	@NotEmpty(message = "CEP não pode ser vazio.")
	private String cep;

	@NotEmpty(message = "Logradouro não pode ser vazio.")
	@Length(min = 3, max = 255, message = "Logradouro deve conter entre 3 e 255 caracteres.")
	private String logradouro;

	@NotEmpty(message = "Bairro não pode ser vazio.")
	@Length(min = 3, max = 120, message = "Bairro deve conter entre 3 e 120 caracteres.")
	private String bairro;

	@NotEmpty(message = "Número não pode ser vazio.")
	private String numero;

	@Length(max = 120, message = "Complemento deve conter no maximo 120 caracteres.")
	private String complemento;

	@NotEmpty(message = "Cidade não pode ser vazio.")
	@Length(min = 3, max = 120, message = "Cidade deve conter entre 3 e 120 caracteres.")
	private String cidade;

	@NotEmpty(message = "Estado não pode ser vazio.")
	@Length(min = 2, max = 3, message = "Estado deve conter entre 2 e 3 caracteres.")
	private String estado;

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

	@Override
	public String toString() {
		return "EnderecoDto [idEndereco=" + idEndereco + ", cep=" + cep + ", logradouro=" + logradouro + ", bairro="
				+ bairro + ", numero=" + numero + ", complemento=" + complemento + ", cidade=" + cidade + ", estado="
				+ estado + "]";
	}

}
