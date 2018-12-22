package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.suam.service.AssentoService;
import com.suam.service.UsuarioService;

public class Logout implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("idUsuario");
		Integer usuarioId = Integer.valueOf(id);
	
		try {
			if (!UsuarioService.buscaUsuarioPelaId(id).getIsAdm()) {
				try {
					AssentoService.desocupaLogofAssentoPagamentoNaoConfirmado(usuarioId);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession sessao = request.getSession();
		// sessao.removeAttribute("usuarioLogado");
		// Outra forma de fazer, invalidando toda a sessao http:
		sessao.invalidate();

		return "redirect:entrada?acao=LoginForm";
	}
}
