package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.suam.bean.CartaoDeCredito;
import com.suam.bean.Usuario;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.constantes.Constantes.NomeAcao;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.service.CartaoDeCreditoService;
import com.suam.service.UsuarioService;
import com.suam.util.ConverteValores;
import com.suam.util.DataUtils;

/**
 * Classe de para inserir objeto do tipo Cartao. Data de Criação: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class NovoCartao implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("AÇÃO = CRIANDO CARTAO");

		String nome = request.getParameter(NomeParametro.USUARIO_NOME);
		String numero = request.getParameter(NomeParametro.CARTAO_NUMERO);
		String data = request.getParameter(NomeParametro.CARTAO_DATA_VENCIMENTO_CARTAO);
		String idUser = request.getParameter(NomeParametro.USUARIO_ID_USER);

		String info = null;
		CartaoDeCredito cartao = new CartaoDeCredito();

		if (nome == null || numero == null || data == null || idUser == null) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		if (nome.equals("") || nome.length() < 2) {
			info = InfoCampos.NOME_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (numero.equals("") || numero.length() < 19 || numero.length() > 19) {
			info = InfoCampos.NUMERO_CARTAO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (data.equals("") || data.length() < 10 || data.length() > 10) { // MELHORAR
			info = InfoCampos.DATA_PROBLEMA;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		} else if (idUser.equals("")) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		cartao.setTitular(nome);
		cartao.setNumeroCartao(numero);
		try {
			cartao.setDataVencimento(DataUtils.formatarDataBarra_dd_mm_yyyy().parse(data));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		cartao.setIdUser(ConverteValores.StringParaInteger(idUser.trim()));

		Usuario usuario = new Usuario();
		try {
			usuario = UsuarioService.buscaUsuarioPelaId(idUser.trim());
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			if (CartaoDeCreditoService.inserirCartao(cartao)) {
			} else {
				info = "Cartão inválido, favor verificar";
				request.setAttribute(NomeParametro.ERRO, info);
				request.setAttribute(NomeParametro.OBJETO_USUARIO, usuario);
				request.setAttribute("cartao", cartao);
				return "forward:" + NomeView.FORM_NOVO_CARTAO;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:entrada?acao=" + NomeAcao.MOSTRA_USUARIO + "&id=" + idUser;

	}
}
