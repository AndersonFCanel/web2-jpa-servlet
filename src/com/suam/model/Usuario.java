package com.suam.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Nome da cluna diferente do nome do atributo
	@Column(name = "idusuario")
	protected Integer id;
	@Column
	protected String nome;
	@Column
	protected String sobrenome;
	// DATE: somente a data, sem a hora;
	// TIME: somente a hora, sem data;
	// TIMESTAMP: tanto data quanto hora.
	@Temporal(TemporalType.DATE)
	protected Date dataNascimento;
	@Column
	protected String endereco;
	@Column
	protected String login;
	@Column
	protected String senha;
	@Column
	protected String confirmaSenha;
	@Column
	protected Boolean isAdm;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {// String id) {
		this.id = id;
	}

	public Boolean getIsAdm() {
		return isAdm;
	}

	public void setIsAdm(Boolean isAdm) {
		this.isAdm = isAdm;
	}

	/*
	 * public String isAdm() { String adm; if (isAdm) { adm = "true"; return adm;
	 * }else { adm = "false"; return adm; } }
	 * 
	 * public void setAdm(boolean isAdm) { this.isAdm = isAdm; }
	 */
	/*
	 * public boolean ehIgual(String login, String senha) {
	 * if(!this.login.equals(login)) { return false; }
	 * 
	 * if(!this.senha.equals(senha)) { return false; }
	 * 
	 * return true; }
	 */

}
