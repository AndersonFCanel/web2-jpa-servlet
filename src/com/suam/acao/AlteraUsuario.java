package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.suam.bean.Usuario;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.constantes.Constantes.Validacao;
import com.suam.service.UsuarioService;
import com.suam.util.DataUtils;

/**
 * Classe  para alteração objeto do tipo usuario.
 * Data de Criação: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class AlteraUsuario implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AÇÃO = ALTERANDO USUARIO");

		String nome = request.getParameter(NomeParametro.USUARIO_NOME);
		String sobrenome = request.getParameter(NomeParametro.USUARIO_SOBRENOME);
		String endereco = request.getParameter(NomeParametro.USUARIO_ENDERECO);
		String senha = request.getParameter(NomeParametro.USUARIO_SENHA);
		String login = request.getParameter(NomeParametro.USUARIO_LOGIN);
		String data = request.getParameter(NomeParametro.USUARIO_DATA);
		String usuarioId = request.getParameter(NomeParametro.USUARIO_ID_USER);
		String ehAdm = request.getParameter(NomeParametro.USUARIO_EH_ADM);

		String info = null;
		Usuario usuario = null;

		if (nome == null || sobrenome == null || endereco == null || senha == null || login == null || data == null
				|| usuarioId == null) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		if (nome.equals("") || nome.length() < 2) {
			info = InfoCampos.NOME_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (sobrenome.equals("") || sobrenome.length() < 2) {
			info = InfoCampos.SOBRENOME_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (endereco.equals("") || endereco.length() < 2) {
			info = InfoCampos.ENDERECO_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (senha.equals("") || senha.length() < 2) {
			info = InfoCampos.SENHA_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (login.equals("") || login.length() < 2) {
			info = InfoCampos.LOGIN_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (data.equals("") || data.length() < 10 || data.length() > 10) {
			info = InfoCampos.DATA_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (usuarioId.equals("")) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

	
		try {
			usuario = UsuarioService.buscaUsuarioPelaId(usuarioId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!data.matches(Validacao.VALIDA_DATA_BARRA_dd_mm_yyyy)) {
			info = InfoCampos.DATA_PROBLEMA + "\n Pode ser que tenha digitado no formado inadequado!";
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		usuario.setNome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setEndereco(endereco);
		usuario.setSenha(senha);
		usuario.setLogin(login);
		try {
			usuario.setDataNascimento(DataUtils.formatarDataBarra_dd_mm_yyyy().parse(data));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if (ehAdm != null) {
			System.out.println("NOVO USUARIO, USUARIO_LOGIN: " + usuario.getLogin() + " É ADM: " + ehAdm);
			if (ehAdm.equals("true") || ehAdm.equals("administrador") || ehAdm.equals("1")) {
				usuario.setIsAdm(true);
			} else if (ehAdm.equals("cliente") || ehAdm.equals("") || ehAdm.equals("0")) {
				usuario.setIsAdm(false);
			}
		} else {
			usuario.setIsAdm(false);
		}

		try {
			if (UsuarioService.update(usuario)) {
				System.out.println("Usuario a ser Alterado: " + usuario.getNome() + " \n " + usuario.getSobrenome()
						+ " \n " + usuario.getEndereco() + " \n " + usuario.getSenha() + " \n " + usuario.getLogin()
						+ " \n " + DataUtils.formatarDataBarra_dd_mm_yyyy().format(usuario.getDataNascimento()) + " \n "
						+ usuario.getIsAdm());
			} else {
				info = "Esse login já esta em uso, já foi cadastrado por outro usuário!";
				request.setAttribute(NomeParametro.OBJETO_USUARIO, usuario);
				request.setAttribute(NomeParametro.ERRO, info);
				return NomeView.FORM_ALTERA_USUARIO;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (usuario.getIsAdm()) {
			// return "redirect:entrada?acao=" + NomeAcao.LISTA_USUARIO;
			info = "Cadastro atualizado com sucesso!";
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}
		{
			// return "redirect:entrada?acao=MostraUsuario&id=" + usuario.getId();
			info = "Cadastro atualizado com sucesso!";
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}
	}
}
