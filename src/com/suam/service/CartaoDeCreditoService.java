package com.suam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.suam.bean.CartaoDeCredito;
import com.suam.bean.Usuario;
import com.suam.factory.ConnectionFactory;
import com.suam.util.ConverteValores;
import com.suam.util.DataUtils;

public class CartaoDeCreditoService {

	public static boolean inserirCartao(CartaoDeCredito cartao) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		String sql = "INSERT INTO cartaodecredito (numeroCartao, dataVencimento, usuario_idusuario) VALUES (?,?,?)";

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, cartao.getNumeroCartao().trim());
			ps.setString(2, formato.format(cartao.getDataVencimento()));
			ps.setString(3, cartao.getIdUser().toString().trim());

			ps.execute();
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			conexao.close();
			return false;
		} finally {
			conexao.close();
		}
		return true;
	}

	public static Boolean update(CartaoDeCredito cartao) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		String sql = "UPDATE cartaodecredito SET dataVencimento= ? WHERE numerocartao = ?";

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			// ps.setString(1, cartao.getNumeroCartao());
			ps.setString(1, formato.format(cartao.getDataVencimento()));
			ps.setString(2, cartao.getNumeroCartao());
			ps.execute();
			ps.close();
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			return false;
		} finally {
			conexao.close();
		}
		return true;
	}

	public static List<CartaoDeCredito> ListaCartoes() throws SQLException {
		System.out.println("LISTANDO TODOS!");
		Connection connection = ConnectionFactory.getConnection();
		List<CartaoDeCredito> listaCartoes = new ArrayList<CartaoDeCredito>();

		Statement statement = connection.createStatement();
		statement.execute("select * from cartaodecredito where exclusaoLogica = '1'");
		ResultSet rs = statement.getResultSet();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		while (rs.next()) {
			CartaoDeCredito cartao = new CartaoDeCredito();
			cartao.setIdUser(rs.getInt("usuario_idusuario"));
			cartao.setNumeroCartao(rs.getString("numeroCartao"));
			try {
				cartao.setDataVencimento(formato.parse(rs.getString("dataVencimento")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			listaCartoes.add(cartao);
		}
		rs.close();
		statement.close();
		connection.close();
		return listaCartoes;
	}

	public static void delete(CartaoDeCredito cartao) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		// String sql = "DELETE FROM cartaodecredito WHERE numerocartao = ?";
		String sql = "UPDATE cartaodecredito SET exclusaoLogica = '0' WHERE numerocartao = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			String id = cartao.getNumeroCartao().toString();
			ps.setString(1, id);

			ps.execute();
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}
	}

	public static void deleteCartoes(Usuario usuario) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		// String sql = "DELETE FROM cartaodecredito WHERE USUARIO_IDUSUARIO = ?";
		String sql = "UPDATE cartaodecredito SET exclusaoLogica = '0' WHERE USUARIO_IDUSUARIO = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			// String id = usuario.getId().toString();
			ps.setInt(1, usuario.getId());
			ps.execute();
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}
	}

	// Listar todos os cartões de um usuário.
	public static List<CartaoDeCredito> buscaCartoesPeloIdUsuario(String idUser) throws SQLException {
		List<CartaoDeCredito> listaCartoes = new ArrayList<CartaoDeCredito>();
		Connection conexao = ConnectionFactory.getConnection();

		String sql = ("select * from cartaodecredito where usuario_idusuario=? and exclusaoLogica ='1'");

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, idUser);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CartaoDeCredito cartao = new CartaoDeCredito();
				cartao.setIdUser(rs.getInt("usuario_idusuario"));
				cartao.setNumeroCartao(rs.getString("numeroCartao"));
				Usuario usuario = new Usuario();
				usuario = UsuarioService.buscaUsuarioPelaId(cartao.getIdUser().toString());
				cartao.setTitular(usuario.getNome() + " " + usuario.getSobrenome());
				try {
					cartao.setDataVencimento(DataUtils.formatarDataBarra_dd_mm_yyyy().parse(rs.getString("dataVencimento")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				listaCartoes.add(cartao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return listaCartoes;
	}

	// Listar todos os cartões de um usuário.
	public static CartaoDeCredito buscaCartaoPelaIdUsuario_NumeroCartao(String idUser, String numeroCartao)
			throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		String sql = ("select * from cartaodecredito where usuario_idusuario=? and NUMEROCARTAO = ? and exclusaoLogica ='1'");
		CartaoDeCredito cartao = new CartaoDeCredito();
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, idUser);
			ps.setString(2, numeroCartao);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				cartao.setIdUser(rs.getInt("usuario_idusuario"));
				cartao.setNumeroCartao(rs.getString("numeroCartao"));
				Usuario usuario = new Usuario();
				usuario = UsuarioService.buscaUsuarioPelaId(cartao.getIdUser().toString());
				cartao.setTitular(usuario.getNome() + " " + usuario.getSobrenome());
				try {
					cartao.setDataVencimento(DataUtils.formatarDataBarra_dd_mm_yyyy().parse(rs.getString("dataVencimento")));
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return cartao;
	}

	/*
	 * public static CartaoDeCredito buscaCartaoPelaId(Integer id) throws
	 * SQLException { List<CartaoDeCredito> lista = ListaCartoes(); for
	 * (CartaoDeCredito cartao : lista) { if (cartao.getIdUser() == id) { return
	 * cartao; } } return null; }
	 */

	public static CartaoDeCredito buscaCartaoPeloNumero(String numero) throws SQLException {
		List<CartaoDeCredito> lista = ListaCartoes();
		String num = numero;
		for (CartaoDeCredito cartao : lista) {
			if (cartao.getNumeroCartao().equalsIgnoreCase(num.trim())) {
				return cartao;
			}
		}
		return null;
	}
}
