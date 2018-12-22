package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.VO.CompraVooVO;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.service.CompraVooService;


public class ListaCompras implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("AÇÃO = LISTANDO COMPRAS");

		List<CompraVooVO> listaCompras = new ArrayList<CompraVooVO>();
		// HashMap<Integer, CompraVoo> mapCompras = new HashMap<Integer, CompraVoo>();

		try {
			listaCompras = CompraVooService.ListaCompras();//MELHORAR - IMPLEMENTAR A ENTREGA DE PARCIAIS
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// request.setAttribute("map", mapCompras);
		// request.setAttribute("usuario", listaUsuario);
		// request.setAttribute("compras", listaCompras);
		request.setAttribute(NomeParametro.OBJETO_LISTA_COMPRAS, listaCompras);
		return "forward:"+NomeView.LISTA_COMPRAS;
	}

}
