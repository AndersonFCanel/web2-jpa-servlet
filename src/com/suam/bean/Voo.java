package com.suam.bean;

//import java.sql.Date;
import java.util.Date;

public class Voo {
	protected Integer idVoo;
	protected Date dataPartida;
	protected String destino;
	protected String origem;
	protected Boolean confirmacao;
	protected Integer ValorVoo;
	
	public Integer getValorVoo() {
		return ValorVoo;
	}

	public void setValorVoo(Integer valorVoo) {
		ValorVoo = valorVoo;
	}

	public Integer getIdVoo() {
		return idVoo;
	}

	public void setIdVoo(Integer idVoo) {
		this.idVoo = idVoo;
	}

	public Date getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(Date ida) {
		this.dataPartida = ida;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public Boolean getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(Boolean string) {
		this.confirmacao = string;
	}
}
