package com.suam.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "cartaodecredito")
public class CartaoDeCredito {

	//Para que o atributo não seja mapeado
	@Transient
	protected String titular;
	
	@Column
	protected String  numeroCartao;
	@Temporal(TemporalType.DATE)
	@Column
	protected Date dataVencimento;
	@Column
	protected Integer idUser;

	public CartaoDeCredito() {
		super();
	}

	public CartaoDeCredito(String  numeroCartao, Date dataVencimento, Integer idUser) {
		super();
		this.numeroCartao = numeroCartao;
		this.dataVencimento = dataVencimento;
		this.idUser = idUser;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String  numeroCarto) {
		this.numeroCartao = numeroCarto;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}
}
