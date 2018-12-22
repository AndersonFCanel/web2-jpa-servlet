package com.suam.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.bean.Usuario;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.service.UsuarioService;

@WebServlet("/formEntradaCadastro")
public class FormEntradaCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cadastroNovo = request.getParameter("cadastrarNovo");

		if (cadastroNovo != null) {
			System.out.println("CADASTRAR USÁRIO ==> CLIENTE NOVO");
			request.getRequestDispatcher("WEB-INF/view/formNovoUsuarioClienteNaoCadastrado.jsp").forward(request,
					response);
		} else {
			System.out.println("NÃO FOI POSSÍVEL CADASTRAR USÁRIO ==> CLIENTE NOVO");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("AÇÃO = INSERINDO USUARIO");

		String nome = request.getParameter("nome");
		String sobrenome = request.getParameter("sobrenome");
		String endereco = request.getParameter("endereco");
		String senha = request.getParameter("senha");
		String confirmaSenha = request.getParameter("confirmaSenha");
		String login = request.getParameter("login");
		String data = request.getParameter("data");
		String ehAdm = request.getParameter("ehAdm");
		
		String info = null;

		if (nome == null || nome.equals("")) {
			info = InfoCampos.NOME_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			request.getRequestDispatcher(NomeView.ERRO_WEB_INF_VIEW).forward(request, response);
			return;
			//return "forward:erro.jsp";
		} else if (sobrenome == null || sobrenome.equals("")) {
			info = InfoCampos.SOBRENOME_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			request.getRequestDispatcher(NomeView.ERRO_WEB_INF_VIEW).forward(request, response);
			return;
			//return "forward:erro.jsp";
		} else if (endereco == null || endereco.equals("")) {
			info = InfoCampos.ENDERECO_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			request.getRequestDispatcher(NomeView.ERRO_WEB_INF_VIEW).forward(request, response);
			return;
			//return "forward:erro.jsp";
		} else if (senha == null || senha.equals("")) {
			info = InfoCampos.SENHA_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			request.getRequestDispatcher(NomeView.ERRO_WEB_INF_VIEW).forward(request, response);
			return;
			//return "forward:erro.jsp";
		} else if (login == null || login.equals("")) {
			info = InfoCampos.LOGIN_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			request.getRequestDispatcher(NomeView.ERRO_WEB_INF_VIEW).forward(request, response);
			return;
			//return "forward:erro.jsp";
		} else if (data == null || data.equals("")) {
			info = InfoCampos.DATA_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			request.getRequestDispatcher(NomeView.ERRO_WEB_INF_VIEW).forward(request, response);
			return;
			//return "forward:erro.jsp";
		}else if (!senha.equals(confirmaSenha)) {
			info = "Senhas e confirmação de senha iincompatíveis!";
			request.setAttribute(NomeParametro.ERRO, info);
			request.getRequestDispatcher(NomeView.ERRO_WEB_INF_VIEW).forward(request, response);
			return;
			//return "forward:erro.jsp";
		}
		
		Usuario usuario = new Usuario();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		usuario.setNome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setEndereco(endereco);
		usuario.setSenha(senha);
		usuario.setLogin(login);
		try {
			usuario.setDataNascimento(formato.parse(data));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if (ehAdm != null) {
			// if (ehAdm.equalsIgnoreCase("TRUE")) {
			usuario.setIsAdm(true);
			// }
		} else {
			usuario.setIsAdm(false);
		}

		Boolean validaInsere;
		if (senha.equals(confirmaSenha)) {
			try {
				validaInsere = UsuarioService.inserir(usuario);
				if (validaInsere) {
					System.out.println("Inserido com sucesso");
				} else {
					info = InfoCampos.LOGIN_JA_EXISTE;

				}
				request.getRequestDispatcher("formLogin.jsp").forward(request,
						response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("AS SENHAS NÃO CONFEREM");
			request.setAttribute(NomeParametro.ERRO, info);
			request.getRequestDispatcher(NomeView.ERRO_WEB_INF_VIEW).forward(request, response);
		}

	}
}