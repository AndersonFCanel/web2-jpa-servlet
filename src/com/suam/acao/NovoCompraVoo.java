/*package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.suam.bean.Assento;
import com.suam.bean.CartaoDeCredito;
import com.suam.bean.CompraVoo;
import com.suam.bean.Usuario;
import com.suam.bean.Voo;
import com.suam.service.AssentoService;
import com.suam.service.CartaoDeCreditoService;
import com.suam.service.CompraVooService;
import com.suam.service.UsuarioService;
import com.suam.service.VooService;

public class NovoCompraVoo implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paramId = request.getParameter("idUser");
		String[] assento = request.getParameterValues("assento");
		String[] assentoVolta = request.getParameterValues("assentoVolta");
		String valorTotalCompra = request.getParameter("valorTotalCompra");
		String[] voo_idvoo = request.getParameterValues("idVoo");
		String cartaodecredito_numerocartao = request.getParameter("numerocartao");
		
		System.out.println("Assentos Volta: "+Arrays.toString(assentoVolta));
		System.out.println(paramId + " - " + Arrays.toString(assento) + " - " + Arrays.toString(voo_idvoo) + " - "
				+ valorTotalCompra + " - " + cartaodecredito_numerocartao);

		Usuario usuario = new Usuario();
		try {
			usuario = UsuarioService.buscaUsuarioPelaId(Integer.valueOf(paramId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		CartaoDeCredito cartaoDeCredito = new CartaoDeCredito();
		try {
			cartaoDeCredito = CartaoDeCreditoService.buscaCartaoPeloNumero(cartaodecredito_numerocartao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		List<Integer> listaNumeroVoo = new ArrayList<Integer>();
		Voo voo = new Voo();
		List<Assento> listaNumeroAssento = new ArrayList<Assento>();
		Integer totalCompra = 0;
		for (String vooIdVoo : voo_idvoo) {
			try {
				if(vooIdVoo != null && vooIdVoo != "" && vooIdVoo !="null") { 
				listaNumeroAssento = AssentoService.listarAssentosPorUsuarioIdVooIdPagamentoNaoConfirmado(usuario.getId(),
						Integer.valueOf(vooIdVoo));
				for (Assento assento2 : listaNumeroAssento) {
					AssentoService.PagamentoAssentoPorUsuarioId(usuario.getId(), assento2.getNumeroAssento(),
							Integer.valueOf(vooIdVoo));
				}

				voo = VooService.buscaVooPelaId(Integer.valueOf(vooIdVoo));
				listaNumeroVoo.add(Integer.valueOf(vooIdVoo));
				totalCompra += (voo.getValorVoo() * listaNumeroAssento.size());
				System.out.println("VALOR TOTAL:  " + valorTotalCompra);
				}
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}

		// Criando a compra
		CompraVoo compra = new CompraVoo();
		// compra.setAssento(listaAssentosNumeros);
		compra.setIdCartao(cartaodecredito_numerocartao);
		compra.setIdUser(usuario.getId());
		compra.setIdVoo(listaNumeroVoo);
		compra.setValorTotalCompra(totalCompra);
		// compra.setIdVooVolta(idVooVolta);

		// pagamento por assento
		for (String assent : assento) {
			try {
				AssentoService.PagamentoAssentoPorUsuarioId(usuario.getId(), Integer.valueOf(assent),
						Integer.valueOf(voo_idvoo[0]));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			CompraVooService.inserir(compra);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("compradorId", paramId);
		request.setAttribute("idParam", paramId);
		
		return "forward:compraRealizadaComSucesso.jsp";
	}

}
*/

package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.VO.CompraVooVO;
import com.suam.bean.Assento;
import com.suam.bean.CartaoDeCredito;
import com.suam.bean.Usuario;
import com.suam.bean.Voo;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.constantes.Constantes.NomeAcao;
import com.suam.constantes.Constantes.NomeView;
import com.suam.constantes.Constantes.NomeParametro;
import com.suam.service.AssentoService;
import com.suam.service.CartaoDeCreditoService;
import com.suam.service.CompraVooService;
import com.suam.service.UsuarioService;
import com.suam.service.VooService;

public class NovoCompraVoo implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ACAO, NOVA COMPRA");
		String paramId = request.getParameter("idUser");
		String[] assento = request.getParameterValues("assento");
		String[] assentoVolta = request.getParameterValues("assentoVolta");
		String valorTotalCompra = request.getParameter("valorTotalCompra");
		String voo_idvoo = request.getParameter("idVoo");
		String voo_idvooVolta = request.getParameter("idVooVolta");
		String cartaodecredito_numerocartao = request.getParameter("numerocartao");

		String info = null;
		CartaoDeCredito cartao = new CartaoDeCredito();

		if (paramId == null || paramId.equals("")) {
			info = InfoCampos.GENERICO;
			request.setAttribute(NomeParametro.ERRO, info);
			return "forward:" + NomeView.INFO_VIEW;
		}

		/*
		 * System.out.println(paramId + " - " + Arrays.toString(assento) + " - " +
		 * voo_idvoo + " - " + voo_idvooVolta + " - " + Arrays.toString(assentoVolta) +
		 * " - " + valorTotalCompra + " - " + cartaodecredito_numerocartao);
		 */

		Usuario usuario = new Usuario();
		CartaoDeCredito cartaoDeCredito = new CartaoDeCredito();
		Voo voo = new Voo();
		Integer totalCompraVoo1 = 0;
		Integer totalCompraVoo2 = 0;
		Integer idVoo = Integer.valueOf(voo_idvoo);
		Integer idVooVolta = null;
		if (voo_idvooVolta != null && voo_idvooVolta != "" && voo_idvooVolta != "null") {
			idVooVolta = Integer.valueOf(voo_idvooVolta);
		}
		CompraVooVO compra = new CompraVooVO();
		List<Assento> listaNumeroAssentoIda = new ArrayList<Assento>();
		List<Assento> listaNumeroAssentoVolta = new ArrayList<Assento>();

		try {
			usuario = UsuarioService.buscaUsuarioPelaId(paramId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (assento == null) {
			info = InfoCampos.ASSENTO_NAO_SELECIONADO;
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		}

		if (cartaodecredito_numerocartao == null) {

			info = "Usuário sem cartão cadastrado\n  <a href=\"entrada?acao=FormNovoCartao&idUser=" + paramId
					+ "\"><button>Cadastrar\r\n" + "				Novo Cartão</button></a>";
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		}

		try {
			cartaoDeCredito = CartaoDeCreditoService.buscaCartaoPeloNumero(cartaodecredito_numerocartao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			if (voo_idvoo != null && voo_idvoo != "" && voo_idvoo != "null") {
				listaNumeroAssentoIda = AssentoService.listarAssentosPorUsuarioIdVooIdPagamentoNaoConfirmado(
						usuario.getId(), Integer.valueOf(voo_idvoo));
				for (Assento assento2 : listaNumeroAssentoIda) {
					AssentoService.PagamentoAssentoPorUsuarioId(usuario.getId(), assento2.getNumeroAssento(),
							Integer.valueOf(voo_idvoo));
				}
				voo = VooService.buscaVooPelaId(voo_idvoo);
				totalCompraVoo1 += (voo.getValorVoo() * listaNumeroAssentoIda.size());
				System.out.println("VALOR TOTAL (voo1 ):  " + valorTotalCompra);
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}

		try {
			if (voo_idvooVolta != null && voo_idvooVolta != "" && voo_idvooVolta != "null") {
				listaNumeroAssentoVolta = AssentoService.listarAssentosPorUsuarioIdVooIdPagamentoNaoConfirmado(
						usuario.getId(), Integer.valueOf(voo_idvooVolta));
				for (Assento assento2 : listaNumeroAssentoVolta) {
					AssentoService.PagamentoAssentoPorUsuarioId(usuario.getId(), assento2.getNumeroAssento(),
							Integer.valueOf(voo_idvooVolta));
				}
				voo = VooService.buscaVooPelaId(voo_idvooVolta);
				totalCompraVoo2 += (voo.getValorVoo() * listaNumeroAssentoVolta.size());
				System.out.println("VALOR TOTAL (voo2 ):  " + valorTotalCompra);
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}

		// pagamento por assento
		for (String assent : assento) {
			try {
				AssentoService.PagamentoAssentoPorUsuarioId(usuario.getId(), Integer.valueOf(assent),
						Integer.valueOf(voo_idvoo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// pagamento por assento
		if (voo_idvooVolta != null && voo_idvooVolta != "" && voo_idvooVolta != "null") {
			for (String assent : assentoVolta) {
				try {
					AssentoService.PagamentoAssentoPorUsuarioId(usuario.getId(), Integer.valueOf(assent),
							Integer.valueOf(voo_idvooVolta));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// Criando a compra
		compra.setIdCartao(cartaodecredito_numerocartao);
		compra.setIdUser(usuario.getId());
		compra.setIdVoo(idVoo);
		if (idVooVolta != null) {
			compra.setIdVooVolta(idVooVolta);
			compra.setListaAssentosVolta(listaNumeroAssentoVolta);
		}
		compra.setListaAssentosIda(listaNumeroAssentoIda);
		compra.setValorTotalCompra(totalCompraVoo1 + totalCompraVoo2);

		try {
			CompraVooService.inserir(compra);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("compradorId", paramId);
		request.setAttribute("idParam", paramId);

		request.setAttribute("info", InfoCampos.SUCESSO);
		return "forward:compraRealizadaComSucesso.jsp";

	}

}
