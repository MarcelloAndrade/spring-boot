package com.springboot.api.dto;

import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

public class UsuarioDto {

	private Long idUsuario;

	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 255, message = "Nome deve conter entre 3 e 255 caracteres.")
	private String nome;

	@NotEmpty(message = "CPF não pode ser vazio.")
	@CPF(message = "CPF inválido.")
	private String cpf;

	@NotEmpty(message = "Email não pode ser vazio.")
	@Length(min = 3, max = 120, message = "Email deve conter entre 3 e 120 caracteres.")
	@Email(message = "Email inválido.")
	private String email;

	@Length(min = 8, max = 16, message = "Senha deve conter entre 8 e 16 caracteres.")
	private Optional<String> senha = Optional.empty();

	@NotNull(message = "Data de Nascimento não pode ser vazia.")
	private Date dataNascimento;

	private Date dataCadastro;

	private Date dataAtualizacao;
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Optional<String> getSenha() {
		return senha;
	}

	public void setSenha(Optional<String> senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	@Override
	public String toString() {
		return "UsuarioDto [idUsuario=" + idUsuario + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email
				+ ", senha=" + senha + ", dataNascimento=" + dataNascimento + ", dataCadastro=" + dataCadastro
				+ ", dataAtualizacao=" + dataAtualizacao + "]";
	}

}
