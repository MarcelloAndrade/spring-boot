package com.springboot.api.entiy;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.springboot.api.enums.EnumPerfil;
import com.springboot.api.enums.EnumStatusAtivo;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long idUsuario;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "CPF", nullable = false, length = 11)
	private String cpf;

	@Column(name = "EMAIL", nullable = false, length = 120)
	private String email;

	@Column(name = "SENHA", nullable = false)
	private String senha;

	@Column(name = "DATA_NASCIMENTO", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(name = "DATA_CADASTRO", nullable = false)
	private Date dataCadastro;

	@Column(name = "DATA_ATUALIZACAO", nullable = false)
	private Date dataAtualizacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "PERFIL", nullable = false, length = 20)
	private EnumPerfil perfil;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS_ATIVO", nullable = false, length = 5)
	private EnumStatusAtivo statusAtivo;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="ID_ENDERECO", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USUARIO_X_ENDERECO"))
	private Endereco endereco;
	
	@PrePersist
	public void prePersist() {
		Date date = new Date();
		dataCadastro = date;
		dataAtualizacao = date;
		statusAtivo = EnumStatusAtivo.SIM;
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
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

	public EnumPerfil getPerfil() {
		return perfil;
	}

	public void setPerfil(EnumPerfil perfil) {
		this.perfil = perfil;
	}

	public EnumStatusAtivo getStatusAtivo() {
		return statusAtivo;
	}

	public void setStatusAtivo(EnumStatusAtivo statusAtivo) {
		this.statusAtivo = statusAtivo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", senha="
				+ senha + ", dataNascimento=" + dataNascimento + ", dataCadastro=" + dataCadastro + ", dataAtualizacao="
				+ dataAtualizacao + ", perfil=" + perfil + ", statusAtivo=" + statusAtivo + ", endereco=" + endereco
				+ "]";
	}

}
