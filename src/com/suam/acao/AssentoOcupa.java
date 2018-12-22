package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.suam.bean.Assento;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.constantes.Constantes.NomeAcao;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.service.AssentoService;
import com.suam.util.ConverteValores;

public class AssentoOcupa implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("AÇÃO => OCUPANDO ASSENTOS");
		String idVoo = request.getParameter(NomeParametro.VOO_ID);
		System.out.println("idvoo" + idVoo);
		String ocupa = request.getParameter(NomeParametro.ASSENTO_OCUPA);
		String desocupa = request.getParameter(NomeParametro.ASSENTO_DESOCUPA);
		String ocupante = request.getParameter(NomeParametro.ASSENTO_OCUPANTE);
		String[] numeroAssento = request.getParameterValues(NomeParametro.ASSENTO_NUMERO);
		String[] numeroAssentoOcupado = request.getParameterValues(NomeParametro.ASSENTO_NUMERO_OCUPADO);
		String[] numeroAssentoVolta = request.getParameterValues(NomeParametro.ASSENTO_NUMERO_VOLTA);
		String[] numeroAssentoOcupadoVolta = request.getParameterValues(NomeParametro.ASSENTO_NUMEROA_OCUPADO_VOLTA);
		String idVooVolta = request.getParameter(NomeParametro.ASSENTO_ID_VOO_VOLTA);

		Assento assentoIda = new Assento();
		Assento assentoVolta = new Assento();
		Integer idOcupante = ConverteValores.StringParaInteger(ocupante);
		Integer idVolta = null;
		String info = null;

		if (idVooVolta != null && idVooVolta != "" && idVooVolta != "null") {
			idVolta = Integer.valueOf(idVooVolta);
		}

		Integer id = null;
		if (idVoo != null && idVoo != "" && idVoo != "null") {
			id = Integer.valueOf(idVoo);
		}

		if (numeroAssento != null || numeroAssentoOcupado != null
				|| (numeroAssentoVolta != null || numeroAssentoOcupadoVolta != null)) {
		} else {
			info = InfoCampos.ASSENTO_NAO_SELECIONADO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		if (idVoo != null) {
			if (numeroAssento != null) {
				for (String assentoNum : numeroAssento) {
					System.out.println("Assento numero: " + assentoNum);
					System.out.println("Voo id: " + id);
					assentoIda.setIdVoo(id);
					assentoIda.setNumeroAssento(ConverteValores.StringParaInteger(assentoNum));
					assentoIda.setOcupante(idOcupante);
					if (ocupa != null) {
						assentoIda.setOcupado(true);
					} else {
						assentoIda.setOcupado(false);
					}
					assentoIda.setOcupante(idOcupante);
					try {
						AssentoService.update(assentoIda);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// List<String> listaAssentosOcupados = new ArrayList<String>();
			if (numeroAssentoOcupado != null) {
				for (String assentoNum : numeroAssentoOcupado) {
					System.out.println("Assento numero: " + assentoNum);
					System.out.println("Voo id: " + id);

					assentoIda.setIdVoo(id);
					assentoIda.setNumeroAssento(ConverteValores.StringParaInteger(assentoNum));
					assentoIda.setOcupante(idOcupante);
					if (desocupa != null) {
						assentoIda.setOcupado(false);
					} else {
						assentoIda.setOcupado(true);
					}
					try {
						AssentoService.update(assentoIda);
						// listaAssentosOcupados.add(assentoNum);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (idVolta != null) {

			if (numeroAssentoVolta != null) {
				for (String assentoNumVolta : numeroAssentoVolta) {
					System.out.println("Assento numero: " + assentoNumVolta);
					System.out.println("Voo id: " + idVooVolta);

					assentoVolta.setIdVoo(idVolta);
					assentoVolta.setNumeroAssento(ConverteValores.StringParaInteger(assentoNumVolta));
					assentoVolta.setOcupante(idOcupante);
					if (ocupa != null) {
						assentoVolta.setOcupado(true);
					} else {
						assentoVolta.setOcupado(false);
					}
					assentoVolta.setOcupante(idOcupante);
					try {
						AssentoService.update(assentoVolta);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// List<String> listaAssentosOcupados = new ArrayList<String>();
			if (numeroAssentoOcupadoVolta != null) {
				for (String assentoNumVolta : numeroAssentoOcupadoVolta) {
					System.out.println("Assento numero: " + assentoNumVolta);
					System.out.println("Voo id: " + idVooVolta);

					assentoVolta.setIdVoo(idVolta);
					assentoVolta.setNumeroAssento(ConverteValores.StringParaInteger(assentoNumVolta));
					assentoVolta.setOcupante(idOcupante);
					if (desocupa != null) {
						assentoVolta.setOcupado(false);
					} else {
						assentoVolta.setOcupado(true);
					}
					try {
						AssentoService.update(assentoVolta);
						// listaAssentosOcupados.add(assentoNum);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return "redirect:entrada?acao=" + NomeAcao.LISTA_ASSENTO + "&vooId=" + idVoo + "&voltaId=" + idVooVolta;
		}

		return "redirect:entrada?acao=" + NomeAcao.LISTA_ASSENTO + "&vooId=" + assentoIda.getIdVoo();

	}
}
