package com.suam.WebService.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.suam.bean.Usuario;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.constantes.Constantes.NomeView;
import com.suam.service.UsuarioService;
import com.thoughtworks.xstream.XStream;


@WebServlet("/ListaUsuario")
public class ListaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListaUsuario() {
		super();
	}

	// Classe resposável por receber a requisição do serviço
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("AÇÃO = LISTANDO USUÁRIOS");
		List<Usuario> listaUsuario = null;
		try {
			listaUsuario = UsuarioService.ListaUsuarios();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// A query buscará todos os registros de usuário

		// valor é definido pelo cliente WEBSERVICE
		String valor = request.getHeader("accept");

		if (valor.endsWith("json")) {
			// DEVOLVENDO JSON COMO RESPOSTA
			Gson gson = new Gson();
			String json = gson.toJson(listaUsuario);
			response.setContentType("application/json");
			response.getWriter().print(json);
		} else if (valor.endsWith("xml")) {
			// DEVOLVENDO XML COMO RESPOSTA
			XStream xtream = new XStream();
			// Quando encontrar um objeto do tipo empresa nomear como empresa no XML
			xtream.alias("Usuario", Usuario.class);
			String xml = xtream.toXML(listaUsuario);
			response.setContentType("application/xml");
			response.getWriter().print(xml);
		} else {
			response.setContentType("application/json");
			response.getWriter().print("{'message': 'no content'}");
		}

		// OBS: Então como funciona a autenticação no Web Service? API Key

	}
}
