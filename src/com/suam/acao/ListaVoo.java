package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.suam.bean.Voo;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.service.VooService;

/**
 * Classe responsável pela geração de lista de objetos do tipo Voo. Data de
 * Criação: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class ListaVoo implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("AÇÃO = LISTANDO VOOS");

		String paginaIni = request.getParameter(NomeParametro.PAGINA_INICIO);
		String paginaFim = request.getParameter(NomeParametro.PAGINA_FIM);

		// A entrega será dividida em parciais
		if (paginaIni == null || paginaFim == null) {
			List<Voo> listaVoos = null;
			try {
				listaVoos = VooService.ListaVoo(); // MELHORAR - IMPLEMENTAR A ENTREGA DE PARCIAIS
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("voos", listaVoos);
			return "forward:" + NomeView.LISTA_VOOS;

		} else {
			List<Voo> listaDividida = null;
			try {
				listaDividida = VooService.ListaVooFracionado(paginaIni, paginaFim);// A query buscará todos os
																				// registros de usuário
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("voos", listaDividida);
			return "forward:" + NomeView.LISTA_VOOS;
		}
	}

}
