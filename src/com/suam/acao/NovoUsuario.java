package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.suam.bean.Usuario;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.constantes.Constantes.NomeAcao;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.Validacao;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.service.UsuarioService;
import com.suam.util.DataUtils;

/**
 * Classe de para inserir objeto do tipo Usuario. Data de Criação: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class NovoUsuario implements Acao {

	private Usuario usuario =  new Usuario();

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("AÇÃO = INSERINDO USUARIO");

		String nome = request.getParameter(NomeParametro.USUARIO_NOME);
		String sobrenome = request.getParameter(NomeParametro.USUARIO_SOBRENOME);
		String endereco = request.getParameter(NomeParametro.USUARIO_ENDERECO);
		String senha = request.getParameter(NomeParametro.USUARIO_SENHA);
		String login = request.getParameter(NomeParametro.USUARIO_LOGIN);
		String data = request.getParameter(NomeParametro.USUARIO_DATA);
		String ehAdm = request.getParameter(NomeParametro.USUARIO_EH_ADM);
		String confirmaSenha = request.getParameter(NomeParametro.USUARIO_CONFIRMA_SENHA);

		String info = null;

		if (nome == null || sobrenome == null || endereco == null || senha == null || login == null || data == null) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		if (nome.equals("") || nome.length() < 2 || nome.length() > 50) {
			info = InfoCampos.NOME_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (sobrenome.equals("") || sobrenome.length() < 2 || sobrenome.length() > 50) {
			info = InfoCampos.SOBRENOME_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (endereco.equals("") || endereco.length() < 2 || endereco.length() > 50) {
			info = InfoCampos.ENDERECO_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (senha.equals("") || senha.length() < 2 || senha.length() > 20) {
			info = InfoCampos.SENHA_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (login.equals("") || login.length() < 2 || login.length() > 30) {
			info = InfoCampos.LOGIN_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (data.equals("") || data.length() < 10 || data.length() > 10) {
			info = InfoCampos.DATA_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		if (!data.matches(Validacao.VALIDA_DATA_TRACO_yyyy_dd_mm)) {
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
			usuario.setDataNascimento(DataUtils.formatarDataTraco_yyyy_mm_dd().parse(data));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		if (ehAdm != null) {
			System.out.println("NOVO USUARIO É ADM: " + ehAdm);
			if (ehAdm.equals("true") || ehAdm.equals("administrador") || ehAdm.equals("1")) {
				usuario.setIsAdm(true);
			} else if (ehAdm.equals("cliente") || ehAdm.equals("") || ehAdm.equals("0")) {
				usuario.setIsAdm(false);
			}
		} else {
			usuario.setIsAdm(false);
		}

		if (senha.equals(confirmaSenha)) {
		} else {
			info = "AS SENHAS NÃO CONFEREM";
			request.setAttribute(NomeParametro.ERRO, info);
			request.setAttribute(NomeParametro.OBJETO_USUARIO, usuario);
			return "forward:" + NomeView.FORM_NOVO_USUARIO;
		}

		try {

			if (UsuarioService.inserir(usuario)) {
				System.out.println("Inserido com sucesso");
				System.out.println("Usuario a ser inserido: " + usuario.getNome() + " \n " + usuario.getSobrenome()
						+ " \n " + usuario.getEndereco() + " \n " + usuario.getSenha() + " \n " + usuario.getLogin()
						+ " - " + DataUtils.formatarDataBarra_dd_mm_yyyy().format(usuario.getDataNascimento()) + " \n "
						+ usuario.getIsAdm());
			} else {
				info = "Cheque o Login, parece já existir alguém usando esse nome!";
				request.setAttribute(NomeParametro.ERRO, info);
				request.setAttribute(NomeParametro.OBJETO_USUARIO, usuario);
				return "forward:" + NomeView.FORM_NOVO_USUARIO;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:entrada?acao=" + NomeAcao.LISTA_USUARIO;

	}
}
