package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.suam.bean.Usuario;
import com.suam.service.UsuarioService;

public class Login implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		System.out.println("AÇÃO = LOGANDO USUARIO: "+ login);


		boolean autenticou = false;
		try {
			autenticou = UsuarioService.autenticar(login, senha);
			System.out.println("OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NOK");
		}

		List<Usuario> listaUsuario = null;
		try {
			listaUsuario = UsuarioService.consultarLoginSenha(login, senha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Usuario user = new Usuario();
		for (Usuario usuario : listaUsuario) {
			user = usuario;
		}

		if (autenticou) {
			if (user.getIsAdm()) {
				HttpSession sessao = request.getSession();
				sessao.setAttribute("usuarioLogado", user);
				System.out.println("Usuároio administrador AUTENTICADO COM SUCESSO!");
				// return "redirect:entrada?acao=ListaUsuario";
				return "redirect:entrada?acao=TelaInicial";
			} else {
				// criar paginas e funções para Usuarios não adm
				HttpSession sessao = request.getSession();
				sessao.setAttribute("usuarioLogado", user);
				System.out.println("Uusário cliente AUTENTICADO COM SUCESSO!");
				return "redirect:entrada?acao=TelaInicial";
				// return "redirect:usuarioNAOADMINISTRADOR.html";
			}
		} else {
			return "redirect:entrada?acao=LoginForm";
		}

		/*
		 * //REPASSANDO SEM REDIRECIONAR PARA O SERVLET DE ENTRADA if (autenticou) {
		 * RequestDispatcher r =
		 * request.getRequestDispatcher("usuario-autenticado.html"); r.forward(request,
		 * response); }
		 */
	}
}
