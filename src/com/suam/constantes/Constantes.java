package com.suam.constantes;

/**
 * Classe utilizada para definição de CONSTANTES.
 * Data de Criação: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class Constantes {
	public static class InfoCampos {

		public static final String GENERICO = "Trabalharemos para solucionar seu problema!";
		public static final String NOME_PROBLEMA = "Verifique o valor informado no campo nome!";
		public static final String SOBRENOME_PROBLEMA = "Verifique o valor informado no campo sobrenome!";
		public static final String DATA_PROBLEMA = "Verifique a data informada!";
		public static final String VALOR_AUSENTE_GENERICO = "Verifique se preencheu todos os campos do fromulário";
		public static final String NUMERO_CARTAO = "Verifique o número do cartão!";
		public static final String ENDERECO_PROBLEMA = "Verifique o valor informado no campo endereço!";
		public static final String SENHA_PROBLEMA = "Verifique o valor informado no campo senha!";
		public static final String LOGIN_PROBLEMA = "Verifique o valor informado no campo login!";
		public static final String ORIGEM_PROBLEMA = "Verifique o valor informado no campo origem!";
		public static final String DESTINO_PROBLEMA = "Verifique o valor informado no campo destino!";
		public static final String IDA_PROBLEMA = "Verifique o valor informado no campo ida!";
		public static final String VOLTA_PROBLEMA = "Verifique o valor informado no campo volta!";
		public static final String CONFIRMACAO_PROBLEMA = "Verifique o valor informado no campo confirmação!";
		public static final String VALOR_PROBLEMA = "Verifique o valor informado no campo valor!";
		public static final String ASSENTO_NAO_SELECIONADO = "Você não selecionou nenhum assento";
		public static final String SUCESSO = "Que bom!\nOperação realizada com sucesso!\nTudo ocorreu conforme o esperado!";
		public static final String LOGIN_JA_EXISTE = "Login já existente!\n Use outro login por favor!";
		public static final String VAZIA = "";

	}

	public static class NomeView {
		public static final String ERRO_WEB_INF_VIEW = "WEB-INF/view/erro.jsp";
		public static final String INFO_VIEW = "erro.jsp";
		public static final String FORM_NOVO_USUARIO = "formNovoUsuario.jsp";
		public static final String FORM_NOVO_VOO = "formNovoVoo.jsp";
		public static final String FORM_NOVO_CARTAO = "formNovoCartao.jsp";
		public static final String FORM_NOVO_COMPRA_VOO = "formNovoCompraVoo.jsp";
		public static final String FORM_ALTERA_USUARIO = "formAlteraUsuario.jsp";
		public static final String FORM_ALTERA_VOO = "formAlteraVoo.jsp";
		public static final String FORM_ALTERA_CARTAO = "formAlteraCartao.jsp";
		public static final String LISTA_COMPRAS = "listaCompras.jsp";
		public static final String LISTA_USUARIO = "listaUsuario.jsp";
		public static final String LISTA_VOOS = "listaVoos.jsp";
		public static final String MOSTRA_COMPRA = "mostraCompra.jsp";
		public static final String NOVO_VOO = "NovoVoo.jsp";
	}

	public static class NomeAcao {
		public static final String MOSTRA_USUARIO = "MostraUsuario";
		public static final String LISTA_USUARIO = "ListaUsuario";
		public static final String MOSTRA_VOO = "MostraVoo";
		public static final String LISTA_ASSENTO = "ListaAssento";
		public static final String LISTA_VOO = "ListaVoo";
	}

	public static class NomeParametro {

		public static final String ERRO = "erro";
		public static final String USUARIO_NOME = "nome";
		public static final String USUARIO_SOBRENOME = "sobrenome";
		public static final String USUARIO_ENDERECO = "endereco";
		public static final String USUARIO_SENHA = "senha";
		public static final String USUARIO_CONFIRMA_SENHA = "confirmaSenha";
		public static final String USUARIO_LOGIN = "login";
		public static final String USUARIO_DATA = "data";
		public static final String USUARIO_ID_USER = "id";
		public static final String USUARIO_EH_ADM = "ehAdm";
		public static final String OBJETO_USUARIO = "usuario";
		public static final String OBJETO_LISTA_USUARIO = "usuarios";

		public static final String OBJETO_CARTOES = "cartoes";
		public static final String OBJETO_LISTA_CARTOES = "cartoes";
		public static final String OBJETO_CARTAO = "cartao";
		public static final String CARTAO_NUMERO = "numero";
		public static final String CARTAO_DATA_VENCIMENTO_CARTAO = "dataVencimento";

		public static final String VOO_ORIGEM = "origem";
		public static final String VOO_DESTINO = "destino";
		public static final String VOO_DATA = "ida";
		public static final String VOO_CONFIRMACAO = "confirmacao";
		public static final String VOO_ID_IDA = "idVooIda";
		public static final String VOO_ID = "idvoo";
		public static final String VOO_VALOR = "valorVoo";
		public static final String OBJETO_VOO = "voo";

		public static final String COMPRA_COMPRADOR_ID = "compradorId";
		public static final String COMPRA_VOO_IDVOO = "idvoo";
		public static final String COMPRA_VOO_IDVOOVOLTA = "idvooVolta";
		public static final String OBJETO_LISTA_COMPRAS = "compras";
		public static final String OBJETO_COMPRA = "compra";
		public static final String ID_COMPRA = "idCompra";

		public static final String ASSENTO_OCUPA = "ocupa";
		public static final String ASSENTO_DESOCUPA = ("desocupa");
		public static final String ASSENTO_OCUPANTE = ("ocupante");
		public static final String ASSENTO_NUMERO = ("numeroAssento");
		public static final String ASSENTO_NUMERO_OCUPADO = ("numeroAssentoOcupado");
		public static final String ASSENTO_NUMERO_VOLTA = ("numeroAssentoVolta");
		public static final String ASSENTO_NUMEROA_OCUPADO_VOLTA = ("numeroAssentoOcupadoVolta");
		public static final String ASSENTO_ID_VOO_VOLTA = ("idVooVolta");

		public static final String PAGINA_INICIO = "pagina_inicio";
		public static final String PAGINA_FIM = "pagina_fim";
	}

	public static class Validacao {
		public static final String VALIDA_DATA_TRACO_dd_mm_yyyy = "\\d{2}-\\d{2}-\\d{4}";
		public static final String VALIDA_DATA_TRACO_yyyy_dd_mm = "\\d{4}-\\d{2}-\\d{2}";
		public static final String VALIDA_DATA_BARRA_dd_mm_yyyy = "\\d{2}/\\d{2}/\\d{4}";
		public static final String VALIDA_DATA_BARRA_yyyy_mm_dd = "\\d{2}/\\d{2}/\\d{4}";
	}

	public static class BancoDeDados {
		/* Constantes com os parâmetros da conexão */
		// MYSQL
		public static final String URL = "jdbc:mysql://localhost:3306/dbweb2?useTimezone=true&serverTimezone=UTC";
		public static final String USERNAME = "root";
		public static final String PASSWORD = "root";
		public static final String STRINGCONEXAO = URL + USERNAME + PASSWORD;
	}
}
