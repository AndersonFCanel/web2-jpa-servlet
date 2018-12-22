package com.suam.VO;

import java.util.List;

import com.suam.bean.Assento;

public class CompraVooVO {

	// protected Integer idCompra;
	protected String idCompra;
	protected Integer idVoo;
	protected Integer idVooVolta;
	protected Integer idUser;
	protected String idCartao;
	protected Integer valorTotalCompra;
	protected String horaCompra;
	protected String nomeUsuario;
	protected List<Assento> listaAssentosIda;
	protected List<Assento> listaAssentosVolta;
	protected List<Integer> listaNumeroAssentosIda;
	protected List<Integer> listaNumeroAssentosVolta;

	public Integer getIdVoo() {
		return idVoo;
	}

	public void setIdVoo(Integer idVoo) {
		this.idVoo = idVoo;
	}

	public Integer getIdVooVolta() {
		return idVooVolta;
	}

	public void setIdVooVolta(Integer idVooVolta) {
		this.idVooVolta = idVooVolta;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getIdCartao() {
		return idCartao;
	}

	public void setIdCartao(String idCartao) {
		this.idCartao = idCartao;
	}

	public String getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(String idCompra) {
		this.idCompra = idCompra;
	}

	public Integer getValorTotalCompra() {
		return valorTotalCompra;
	}

	public void setValorTotalCompra(Integer valorTotalCompra) {
		this.valorTotalCompra = valorTotalCompra;
	}

	public String getHoraCompra() {
		return horaCompra;
	}

	public void setHoraCompra(String horaCompra) {
		this.horaCompra = horaCompra;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public List<Assento> getListaAssentosIda() {
		return listaAssentosIda;
	}

	public void setListaAssentosIda(List<Assento> listaAssentosIda) {
		this.listaAssentosIda = listaAssentosIda;
	}

	public List<Assento> getListaAssentosVolta() {
		return listaAssentosVolta;
	}

	public void setListaAssentosVolta(List<Assento> listaAssentosVolta) {
		this.listaAssentosVolta = listaAssentosVolta;
	}

	public List<Integer> getListaNumeroAssentosIda() {
		return listaNumeroAssentosIda;
	}

	public void setListaNumeroAssentosIda(List<Integer> listaNumeroAssentosIda) {
		this.listaNumeroAssentosIda = listaNumeroAssentosIda;
	}

	public List<Integer> getListaNumeroAssentosVolta() {
		return listaNumeroAssentosVolta;
	}

	public void setListaNumeroAssentosVolta(List<Integer> listaNumeroAssentosVolta) {
		this.listaNumeroAssentosVolta = listaNumeroAssentosVolta;
	}

	
	
	
}
