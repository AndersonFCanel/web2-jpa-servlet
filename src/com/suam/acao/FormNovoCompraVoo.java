package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.bean.Assento;
import com.suam.bean.CartaoDeCredito;
import com.suam.bean.Usuario;
import com.suam.bean.Voo;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.service.AssentoService;
import com.suam.service.CartaoDeCreditoService;
import com.suam.service.UsuarioService;
import com.suam.service.VooService;
import com.suam.util.ConverteValores;

public class FormNovoCompraVoo implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("FORM NOVO COMPRA");

		String compradorId = request.getParameter(NomeParametro.COMPRA_COMPRADOR_ID);
		Integer id = Integer.valueOf(compradorId);
		String voo_idvoo = request.getParameter(NomeParametro.COMPRA_VOO_IDVOO);
		String voo_idvooVolta = request.getParameter(NomeParametro.COMPRA_VOO_IDVOOVOLTA);

		String info = null;

		if (compradorId == null) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}
		if (compradorId.equals("")) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		Usuario user = new Usuario();
		List<CartaoDeCredito> listaCartao = null;
		boolean idaVolta = false;
		Voo vooIda = null;
		List<Assento> listaNumeroAssento = new ArrayList<Assento>();
		List<Assento> listaNumeroAssentoTratada = new ArrayList<Assento>();
		List<Assento> listaNumeroAssentoVolta = new ArrayList<Assento>();
		List<Assento> listaNumeroAssentoTratadaVolta = new ArrayList<Assento>();

		// usuario
		try {
			user = UsuarioService.buscaUsuarioPelaId(compradorId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute(NomeParametro.OBJETO_USUARIO, user);

		// lista de cartões do usuario
		try {
			listaCartao = CartaoDeCreditoService.buscaCartoesPeloIdUsuario(compradorId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute(NomeParametro.OBJETO_LISTA_CARTOES, listaCartao);

		// encontra voo
		try {
			vooIda = VooService.buscaVooPelaId(voo_idvoo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute(NomeParametro.COMPRA_VOO_IDVOO, vooIda);

		// Voo ida
		// LISTARÁ TODOS ===> FILTAR SOMENTE SO ESCOLHIDOS NAQUELE INSTANTE
		try {
			listaNumeroAssento = AssentoService.listarAssentosPorUsuarioIdVooId(id,
					ConverteValores.StringParaInteger(voo_idvoo));
			for (Assento numeroAssento : listaNumeroAssento) {
				if (numeroAssento.isComfirmaPagamento()) {
					System.out.println("ASSENTO JÁ COMPRADO: " + numeroAssento.getNumeroAssento());
					continue;
				} else {
					listaNumeroAssentoTratada.add(numeroAssento);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Voo Volta
		// LISTARÁ TODOS ===> FILTAR SOMENTE SO ESCOLHIDOS NAQUELE INSTANTE
		if (voo_idvooVolta != null && voo_idvooVolta != "" && voo_idvooVolta != "null") {
			idaVolta = true;
			// encontra voo
			Voo vooIdaVolta = null;
			try {
				vooIdaVolta = VooService.buscaVooPelaId(voo_idvooVolta);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute(NomeParametro.COMPRA_VOO_IDVOOVOLTA, vooIdaVolta);

			try {
				listaNumeroAssentoVolta = AssentoService.listarAssentosPorUsuarioIdVooId(id,
						ConverteValores.StringParaInteger(voo_idvooVolta));
				for (Assento numeroAssento : listaNumeroAssentoVolta) {
					if (numeroAssento.isComfirmaPagamento()) {
						System.out.println("ASSENTO JÁ COMPRADO: " + numeroAssento.getNumeroAssento());
						continue;
					} else {
						listaNumeroAssentoTratadaVolta.add(numeroAssento);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("idaVolta", idaVolta);
		request.setAttribute("assentos", listaNumeroAssentoTratada);
		request.setAttribute("assentosVolta", listaNumeroAssentoTratadaVolta);
		return "forward:" + NomeView.FORM_NOVO_COMPRA_VOO;
	}

}
