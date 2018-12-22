package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.bean.CartaoDeCredito;
import com.suam.bean.Usuario;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.service.CartaoDeCreditoService;
import com.suam.service.UsuarioService;
import com.suam.util.DataUtils;

/**
 * Classe de para gerar objeto usuario. Data de Criação: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class MostraUsuario implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AÇÃO = MOSTRANDO DADOS DO USUARIO");

		String usuarioId = request.getParameter(NomeParametro.USUARIO_ID_USER);
		String info = null;
		Usuario usuario = null;

		if (usuarioId == null) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}
		if (usuarioId.equals("")) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		try {
			usuario = UsuarioService.buscaUsuarioPelaId(usuarioId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<CartaoDeCredito> listaCartao = null;
		try {
			listaCartao = CartaoDeCreditoService.buscaCartoesPeloIdUsuario(usuarioId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!listaCartao.isEmpty()) {
			request.setAttribute(NomeParametro.OBJETO_CARTOES, listaCartao);
		} else {
			info = "Usuário sem cartão cadastrado  ";
			request.setAttribute(NomeParametro.ERRO, info);
			// return "forward:"+NomeView.ERRO_VIEW;
		}

	
		request.setAttribute(NomeParametro.OBJETO_USUARIO, usuario);
		return "forward:" + NomeView.FORM_ALTERA_USUARIO;
	}
}
