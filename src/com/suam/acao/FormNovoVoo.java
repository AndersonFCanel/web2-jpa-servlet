package com.suam.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.constantes.Constantes.NomeView;

/**
 * Classe respons�vel pela redirecionamento ao formul�rio de inser��o de um
 * ojeto do tipo Voo. Data de Cria��o: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class FormNovoVoo implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "forward:" + NomeView.FORM_NOVO_VOO;
	}

}
