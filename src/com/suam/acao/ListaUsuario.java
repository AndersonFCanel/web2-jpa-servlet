package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.bean.Usuario;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.service.UsuarioService;
import com.suam.util.ConverteValores;

/**
 * Classe de para gerar lista de usuários, fracionada ou não.
 * Data de Criação: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class ListaUsuario implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AÇÃO = LISTANDO USUÁRIOS");

		String paginaIni = request.getParameter(NomeParametro.PAGINA_INICIO);
		String paginaFim = request.getParameter(NomeParametro.PAGINA_FIM);

		// A entrega será dividida em parciais
		if (paginaIni == null || paginaFim == null) {
			List<Usuario> listaUsuario = null;
			try {
				listaUsuario = UsuarioService.ListaUsuarios();// A query buscará todos os registros de usuário
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute(NomeParametro.OBJETO_LISTA_USUARIO, listaUsuario);
			return "forward:" + NomeView.LISTA_USUARIO;
		} else {
			List<Usuario> listaDividida = null;
			try {
				listaDividida = UsuarioService.ListaUsuariosFracao(paginaIni, paginaFim);// A query buscará todos os
																							// registros de usuário
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute(NomeParametro.OBJETO_LISTA_USUARIO, listaDividida);
		}
		return "forward:" + NomeView.LISTA_USUARIO;
	}

}
