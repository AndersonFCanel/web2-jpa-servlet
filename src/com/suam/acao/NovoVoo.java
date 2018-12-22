package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.bean.Voo;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.constantes.Constantes.NomeAcao;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.service.AssentoService;
import com.suam.service.VooService;

/**
 * Classe de para inserir objeto do tipo voo. Data de Criação: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class NovoVoo implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("AÇÃO = ALTERANDO VOO");

		String origem = request.getParameter(NomeParametro.VOO_ORIGEM);
		String destino = request.getParameter(NomeParametro.VOO_DESTINO);
		String dataPartida = request.getParameter(NomeParametro.VOO_DATA);
		String confirmacao = request.getParameter(NomeParametro.VOO_CONFIRMACAO);
		String valorVoo = request.getParameter(NomeParametro.VOO_VALOR);

		String info = null;

		if (origem == null || destino == null || dataPartida == null || confirmacao == null || valorVoo == null) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		if (origem.equals("") || origem.length() < 2) {
			info = InfoCampos.ORIGEM_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (destino.equals("") || destino.length() < 2) {
			info = InfoCampos.DESTINO_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (dataPartida.equals("") || dataPartida.length() < 10 || dataPartida.length() > 10) {
			info = InfoCampos.IDA_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (confirmacao.equals("")) {
			info = InfoCampos.CONFIRMACAO_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (valorVoo.equals("") || Integer.valueOf(valorVoo) < 0) {
			info = InfoCampos.VALOR_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		Voo voo = new Voo();

		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date = null;
		try {
			date = formato.parse(dataPartida);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		java.sql.Date sqlIda = new java.sql.Date(date.getTime());

		voo.setDataPartida(sqlIda);

		if (confirmacao != null) {
			if (confirmacao.equals("true") || confirmacao.equals("administrador") || confirmacao.equals("1")) {
				voo.setConfirmacao(true);
			} else if (confirmacao.equals("cliente") || confirmacao.equals("") || confirmacao.equals("0")) {
				voo.setConfirmacao(false);
			}
		} else {
			voo.setConfirmacao(false);
		}

		voo.setOrigem(origem);
		voo.setDestino(destino);
		voo.setValorVoo(Integer.valueOf(valorVoo));

		try {
			if (VooService.inserir(voo)) {
				System.out.println("Inserido com sucesso");
			} else {
				request.setAttribute(NomeParametro.OBJETO_VOO, voo);
				return "forward:" + NomeView.NOVO_VOO;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			AssentoService.inserirAssentosPorVoo(100);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// request.setAttribute("voo", voo);
		// return
		// "redirect:entrada?acao="+NomeAcao.MOSTRA_VOO+"&"+NomeParametro.VOO_ID+"=" +
		// idVoo;
		return "redirect:entrada?acao=" + NomeAcao.LISTA_VOO;
	}
}
