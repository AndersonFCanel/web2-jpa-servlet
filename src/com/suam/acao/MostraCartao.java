package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
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

public class MostraCartao implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AÇÃO = MOSTRANDO DADOS DO CARTAO");

		String paramId = request.getParameter("id");
		String numeroCartao = request.getParameter("numeroCartao");

		String info = null;
		if (paramId == null) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		if (paramId.equals("")) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		Usuario usuario = null;
		try {
			usuario = UsuarioService.buscaUsuarioPelaId(paramId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		CartaoDeCredito cartao = null;
		try {
			cartao = CartaoDeCreditoService.buscaCartaoPelaIdUsuario_NumeroCartao(usuario.getId().toString(), numeroCartao);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//cartao.setTitular(usuario.getNome() + " " + usuario.getSobrenome());

		request.setAttribute(NomeParametro.OBJETO_CARTAO, cartao);

		return "forward:" + NomeView.FORM_ALTERA_CARTAO;
	}
}
