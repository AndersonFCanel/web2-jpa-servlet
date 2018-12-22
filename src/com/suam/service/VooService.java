package com.suam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.suam.bean.Voo;
import com.suam.factory.ConnectionFactory;
import com.suam.util.ConverteValores;
import com.suam.util.DataUtils;

/**
 * Classe de Serviço para objeto do tipo Voo. Data de Criação: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class VooService {

	public static boolean inserir(Voo voo) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		String sql = "INSERT INTO voo ( ida, origem, destino, confirmacao, valorVoo) VALUES (?,?,?,?,?)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, DataUtils.formatarDataBarra_dd_mm_yyyy().format(voo.getDataPartida()));
			ps.setString(2, voo.getOrigem());
			ps.setString(3, voo.getDestino());
			ps.setBoolean(4, voo.getConfirmacao());
			ps.setInt(5, voo.getValorVoo());

			ps.execute();
			conexao.commit();
			ps.close();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}
		return true;
	}

	public static Boolean update(Voo voo) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		String sql = "UPDATE voo SET  ida = ?,origem = ?,  destino = ?, confirmacao = ?, valorVoo = ? WHERE idVoo = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setDate(1, DataUtils.dateJavaUtiltoSql(voo.getDataPartida()));
			ps.setString(2, voo.getOrigem());
			ps.setString(3, voo.getDestino());
			if (voo.getConfirmacao()) {
				ps.setString(4, "1");
			} else {
				ps.setString(4, "0");
			}
			ps.setInt(5, voo.getValorVoo());
			ps.setString(6, voo.getIdVoo().toString());

			System.out.println("SQL: " + ps);
			ps.execute();
			conexao.commit();
			ps.close();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			return false;
		} finally {
			conexao.close();
		}
		return true;
	}

	public static List<Voo> ListaVoo() throws SQLException {

		Connection connection = ConnectionFactory.getConnection();
		List<Voo> listaVoos = new ArrayList<Voo>();

		Statement statement = connection.createStatement();
		statement.execute("select * from voo where exclusaoLogica = '1' LIMIT 100");

		ResultSet rs = statement.getResultSet();

		while (rs.next()) {
			Voo voo = new Voo();
			voo.setIdVoo(rs.getInt("idvoo"));
			voo.setDataPartida(DataUtils.dateSqltoJavaUtil(rs.getDate("ida")));
			voo.setConfirmacao(rs.getBoolean("confirmacao"));
			voo.setOrigem(rs.getString("origem"));
			voo.setDestino(rs.getString("destino"));
			voo.setValorVoo(rs.getInt("valorVoo"));
			listaVoos.add(voo);
		}
		System.out.println("SQL: " + rs);
		rs.close();
		statement.close();
		rs.close();
		connection.close();
		return listaVoos;
	}

	public static List<Voo> ListaVooFracionado(String pagIni, String pagFim) throws SQLException {

		Connection conexao = ConnectionFactory.getConnection();
		List<Voo> listaVoos = new ArrayList<Voo>();

		String sql = "SELECT * FROM voo  where exclusaoLogica = '1'  LIMIT ? OFFSET ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, ConverteValores.StringParaInteger(pagFim));
			ps.setInt(2, ConverteValores.StringParaInteger(pagIni));

			System.out.println("CONSULTAR SELECT: " + ps);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Voo voo = new Voo();
				voo.setIdVoo(rs.getInt("idvoo"));
				voo.setDataPartida(rs.getDate("ida"));
				voo.setConfirmacao(rs.getBoolean("confirmacao"));
				voo.setOrigem(rs.getString("origem"));
				voo.setDestino(rs.getString("destino"));
				voo.setValorVoo(rs.getInt("valorVoo"));
				listaVoos.add(voo);
			}
			System.out.println("SQL: " + ps);
			ps.close();
			conexao.commit();
		} catch (SQLException e) {
			System.out.println("ERRO: " + e);
			 conexao.rollback();
		} finally {
			conexao.close();
		}
		return listaVoos;
	}

	public static Voo buscaVooPelaId(String idVoo) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		List<Voo> listaVoo = new ArrayList<>();
		// Código para Validar se existe usuário com mesmo login no banco
		String sql = "Select  * from voo   where idvoo = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			// ps.setInt(1, voo.getIdVoo());
			// ps.execute();
			ps.setInt(1, ConverteValores.StringParaInteger(idVoo));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Voo voo = new Voo();
				voo.setIdVoo(rs.getInt("idvoo"));
				voo.setDataPartida(rs.getDate("ida"));
				voo.setConfirmacao(rs.getBoolean("confirmacao"));
				voo.setOrigem(rs.getString("origem"));
				voo.setDestino(rs.getString("destino"));
				voo.setValorVoo(rs.getInt("valorVoo"));
				listaVoo.add(voo);
			}
			System.out.println("SQL: " + ps);
			conexao.commit();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}
		if (listaVoo.size() > 0 || !listaVoo.isEmpty()) {
			return listaVoo.get(0);
		} else {
			Voo vooNull = null;
			return vooNull;
		}
	}

	public static Boolean validaDML(Voo idVoo) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		List<Voo> listaVoo = new ArrayList<>();
		// Código para Validar se existe usuário com mesmo login no banco
		String sql = "Select  * from voo   where idvoo = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, idVoo.getIdVoo());
			// ps.execute();

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Voo voo = new Voo();
				voo.setIdVoo(rs.getInt("idvoo"));
				voo.setDataPartida(rs.getDate("ida"));
				voo.setConfirmacao(rs.getBoolean("confirmacao"));
				voo.setOrigem(rs.getString("origem"));
				voo.setDestino(rs.getString("destino"));
				voo.setValorVoo(rs.getInt("valorVoo"));
				listaVoo.add(voo);
			}
			System.out.println("SQL: " + ps);
			conexao.commit();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}
		if (listaVoo.size() > 0 || !listaVoo.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static void delete(Voo voo) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		// String sql = "DELETE FROM voo WHERE idVoo = ?";
		String sql = "UPDATE voo SET exclusaoLogica = '0' WHERE idVoo = ?";
		// String sql1 = "DELETE FROM ASSENTO WHERE voo_idVoo =?";
		String sql1 = "UPDATE assento SET exclusaoLogica = '0' WHERE voo_idvoo = ?";

		try {
			PreparedStatement ps1 = conexao.prepareStatement(sql1);
			ps1.setInt(1, voo.getIdVoo());
			ps1.execute();
			conexao.commit();
			ps1.close();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, voo.getIdVoo());
			ps.execute();
			conexao.commit();
			ps.close();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}
	}

}
