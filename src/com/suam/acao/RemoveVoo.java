package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.bean.Voo;
import com.suam.constantes.Constantes.NomeAcao;
import com.suam.service.CompraVooService;
import com.suam.service.VooService;


/**
 * Classe  responsável por remover objeto do tipo Voo.
 * Data de Criação: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class RemoveVoo implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AÇÃO = REMOVENDO VOO");

		String paramId = request.getParameter("idvoo");
		Integer id = Integer.valueOf(paramId);

		Voo voo = null;
		
		try {
			CompraVooService.deleteCompraPorVoo(id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			voo = VooService.buscaVooPelaId(paramId);
			VooService.delete(voo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "redirect:entrada?acao="+NomeAcao.LISTA_VOO;
	}
}
