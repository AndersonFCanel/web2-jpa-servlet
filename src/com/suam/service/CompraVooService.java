package com.suam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.suam.VO.CompraVooVO;
import com.suam.bean.Assento;
import com.suam.bean.Usuario;
import com.suam.factory.ConnectionFactory;
import com.suam.util.DataUtils;

public class CompraVooService {

	public static String idUltimaCompra() throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		
		String sql;

		sql = "SELECT MAX(idcompraVoo) as 'idcompraVoo' FROM compravoo ";

		String idCompra = null;
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				idCompra = rs.getString("idcompraVoo");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// conexao.close();
		return idCompra;
	}

	public static void inserir(CompraVooVO compra) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		String sql;
		if (compra.getIdVooVolta() != null) {
			sql = "INSERT INTO compravoo (" + "voo_idvoo, " + "voo_idvooVolta, " + "valorTotalCompra, "
					+ "usuario_idusuario, " + "cartaodecredito_numerocartao," + "horaDaCompra) "
					+ "VALUES(?,?,?,?,?,?)";
		} else {
			sql = "INSERT INTO compravoo (" + "voo_idvoo, " + "valorTotalCompra, " + "usuario_idusuario, "
					+ "cartaodecredito_numerocartao," + "horaDaCompra) " + "VALUES(?,?,?,?,?)";
		}

		System.out.println("Dados da Compra:: \n ID CARTÃO: " + compra.getIdCartao() + "\n ID VOO IDA: "
				+ compra.getIdVoo().toString() + "\n ID VOO VOLTA " + compra.getIdVooVolta() + "\n ASSENTOS IDA: "
				+ compra.getListaAssentosIda().toString() + " \n ASSENTOS VOLTA: " + " \nID DA COMPRA "
				+ compra.getIdUser() + " \nVALOR TOTAL: " + compra.getValorTotalCompra());

		String agora = DataUtils.gravarDataEHoraAtualBD();
		System.out.println("HORA====>>> " + agora);

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setInt(1, compra.getIdVoo());
			if (compra.getIdVooVolta() != null) {
				ps.setInt(2, compra.getIdVooVolta());
				ps.setInt(3, compra.getValorTotalCompra());
				ps.setInt(4, compra.getIdUser());
				ps.setString(5, compra.getIdCartao());
				ps.setString(6, agora);
			} else {
				ps.setInt(2, compra.getValorTotalCompra());
				ps.setInt(3, compra.getIdUser());
				ps.setString(4, compra.getIdCartao());
				ps.setString(5, agora);
			}
			ps.execute();
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} /*
			 * finally { conexao.close(); }
			 */

		String sql1 = "INSERT INTO assentosCompradosPorVoo (assenNumero, vooId, "
				+ " compravoo_idcompraVoo) VALUES (?,?,?)";
		String sql2 = "INSERT INTO assentosCompradosPorVoo (assenNumero, vooIdVolta,"
				+ " compravoo_idcompraVoo) VALUES (?,?,?)";

		List<Assento> listaAssentoIda = new ArrayList<Assento>();
		listaAssentoIda = compra.getListaAssentosIda();
		for (Assento assento : listaAssentoIda) {
			try {
				PreparedStatement ps = conexao.prepareStatement(sql1);

				ps.setInt(1, assento.getNumeroAssento());
				ps.setInt(2, compra.getIdVoo());
				ps.setInt(3, Integer.valueOf(idUltimaCompra()));
				ps.execute();

				conexao.commit();
			} catch (SQLException e) {
				conexao.rollback();
				e.printStackTrace();
				throw new SQLException();
			}
		}
		if (compra.getIdVooVolta() != null) {
			List<Assento> listaAssentoVolta = new ArrayList<Assento>();
			listaAssentoVolta = compra.getListaAssentosVolta();
			for (Assento assento : listaAssentoVolta) {
				try {
					PreparedStatement ps = conexao.prepareStatement(sql2);

					ps.setInt(1, assento.getNumeroAssento());
					ps.setInt(2, compra.getIdVooVolta());
					ps.setInt(3, Integer.valueOf(idUltimaCompra()));

					ps.execute();
					conexao.commit();
				} catch (SQLException e) {
					conexao.rollback();
					e.printStackTrace();
					throw new SQLException();
				}
			}
		}

		conexao.close();

	}

	// Deletando compras feitas por um usuario
	public static void deleteCompraPorVoo(Integer vooId) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		// String sql = "DELETE FROM compravoo WHERE usuario_IDUSUARIO = ?";
		String sql = "UPDATE compravoo SET exclusaoLogica = '0' WHERE voo_idvoo = ?";

		try {
			PreparedStatement ps1 = conexao.prepareStatement(sql);
			ps1.setInt(1, vooId);
			ps1.execute();
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}
	}

	// Deletando voos comprados =>> AO DELETAR UM VOO ELE PERMANECERÁ REGISTRADO
	public static void deleteCompraPorUsuario(Integer usuarioId) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		// String sql = "DELETE FROM compravoo WHERE usuario_IDUSUARIO = ?";
		String sql = "UPDATE compravoo SET exclusaoLogica = '0' WHERE usuario_IDUSUARIO = ?";

		try {
			PreparedStatement ps1 = conexao.prepareStatement(sql);
			ps1.setInt(1, usuarioId);
			ps1.execute();
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}
	}

	// Listando Todas as Compras
	public static List<CompraVooVO> ListaCompras() throws SQLException {

		Connection connection = ConnectionFactory.getConnection();
		List<CompraVooVO> listaCompras = new ArrayList<CompraVooVO>();

		Statement statement = connection.createStatement();
		statement.execute("select * from compravoo ");

		ResultSet rs = statement.getResultSet();

		while (rs.next()) {
			CompraVooVO compraVooVO = new CompraVooVO();
			compraVooVO.setIdUser(rs.getInt("usuario_IDUSUARIO"));
			compraVooVO.setIdCartao(rs.getString("cartaodecredito_NUMEROCARTAO"));
			compraVooVO.setIdVoo(rs.getInt("voo_idvoo"));
			compraVooVO.setIdVooVolta(rs.getInt("voo_idvooVolta"));
			compraVooVO.setValorTotalCompra(rs.getInt("valorTotalCompra"));
			compraVooVO.setIdCompra(rs.getString("idcompraVoo"));
			Usuario usuario = new Usuario();
			usuario = UsuarioService.buscaUsuarioPelaIdHistoricoDeCompras(compraVooVO.getIdUser());
			compraVooVO.setNomeUsuario(usuario.getNome() + " " + usuario.getSobrenome());

			String dataRecebida = rs.getString("horaDaCompra");
			compraVooVO.setHoraCompra(dataRecebida);

			listaCompras.add(compraVooVO);
		}

		for (CompraVooVO compraVooVO : listaCompras) {
			System.out.println("HORA: "+compraVooVO.getHoraCompra());
			System.out.println("ID USER "+ compraVooVO.getIdUser());
		}

		rs.close();
		statement.close();
		connection.close();
		return listaCompras;
	}

	// Listando Todas as Compras
	public static CompraVooVO comprasPorId(Integer idcompraVoo) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		String sql = "select * from compravoo where idcompraVoo = ?  ";

		CompraVooVO compraVooVO = new CompraVooVO();
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, idcompraVoo);
			ps.execute();
			System.out.println("CONSULTAR SELECT: " + ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				compraVooVO.setIdUser(rs.getInt("usuario_IDUSUARIO"));
				compraVooVO.setIdCartao(rs.getString("cartaodecredito_NUMEROCARTAO"));
				compraVooVO.setIdVoo(rs.getInt("voo_idvoo"));
				compraVooVO.setIdVooVolta(rs.getInt("voo_idvooVolta"));
				compraVooVO.setValorTotalCompra(rs.getInt("valorTotalCompra"));
				compraVooVO.setIdCompra(rs.getString("idcompraVoo"));

				String dataRecebida = rs.getString("horaDaCompra");
				compraVooVO.setHoraCompra(dataRecebida);
			}
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}

		return compraVooVO;
	}

	public static List<Integer> comprasAssentosVooIda(Integer idcompraVoo, Integer idVoo) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		String sql = "select * from assentoscompradosporvoo where compravoo_idcompraVoo = ? and vooId = ? ";
		List<Integer> listaAssentos = new ArrayList<Integer>();

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, idcompraVoo);
			ps.setInt(2, idVoo);
			ps.execute();
			System.out.println("CONSULTAR SELECT: " + ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listaAssentos.add(rs.getInt("assenNumero"));

			}
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}

		return listaAssentos;
	}

	public static List<Integer> comprasAssentosVooVolta(Integer idcompraVoo, Integer idVooVolta) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		String sql = "select * from assentoscompradosporvoo where compravoo_idcompraVoo = ? and vooIdVolta = ? ";
		List<Integer> listaAssentos = new ArrayList<Integer>();

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, idcompraVoo);
			ps.setInt(2, idVooVolta);
			ps.execute();
			System.out.println("CONSULTAR SELECT: " + ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listaAssentos.add(rs.getInt("assenNumero"));
			}
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}

		return listaAssentos;
	}

}
