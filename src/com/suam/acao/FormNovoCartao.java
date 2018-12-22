package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.bean.Usuario;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.service.UsuarioService;

public class FormNovoCartao implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paramId = request.getParameter(NomeParametro.USUARIO_ID_USER);
		Usuario usuario = null;
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

		try {
			usuario = UsuarioService.buscaUsuarioPelaId(paramId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute(NomeParametro.OBJETO_USUARIO, usuario);

		return "forward:" + NomeView.FORM_NOVO_CARTAO;
	}

}
