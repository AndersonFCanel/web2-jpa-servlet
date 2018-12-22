package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.suam.bean.Usuario;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.constantes.Constantes.NomeView;
import com.suam.service.AssentoService;
import com.suam.service.CartaoDeCreditoService;
import com.suam.service.CompraVooService;
import com.suam.service.UsuarioService;

/**
 * Classe para remover objeto do tipo usuario.
 * Data de Criação: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class RemoveUsuario implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AÇÃO = REMOVENDO USUARIO");

		String usuarioId = request.getParameter(NomeParametro.USUARIO_ID_USER);

		Usuario usuario = null;
		try {
			usuario = UsuarioService.buscaUsuarioPelaId(usuarioId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			CartaoDeCreditoService.deleteCartoes(usuario);
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		try {
			AssentoService.desocuparAssentoPorUsuarioId(usuario.getId());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			CompraVooService.deleteCompraPorUsuario(usuario.getId());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			UsuarioService.delete(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// request.setAttribute("usuario",usuario);
		//return "redirect:entrada?acao=" + NomeAcao.LISTA_USUARIO;
		
		String info = InfoCampos.SUCESSO;
		request.setAttribute(NomeParametro.ERRO, info);
		return "forward:" + NomeView.INFO_VIEW;
	}
}
