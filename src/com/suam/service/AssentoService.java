package com.suam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.suam.bean.Assento;
import com.suam.bean.Voo;
import com.suam.factory.ConnectionFactory;

public class AssentoService {

	public static void inserir(String assento, Integer idVoo) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		String sql = "INSERT INTO assento VALUES(?,true,?,0)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, assento);
			ps.setInt(2, idVoo);

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

	public static Boolean update(Assento assento) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		String sql = "UPDATE assento SET  ocupado = ?, usuario_idusuario = ? WHERE voo_idVoo = ? and idassento =? ";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			if (assento.isOcupado()) {
				ps.setBoolean(1, true);
			} else {
				ps.setBoolean(1, false);
			}
			ps.setInt(2, assento.getOcupante());
			ps.setInt(3, assento.getIdVoo());
			ps.setInt(4, assento.getNumeroAssento());

			ps.execute();
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}
		return true;
	}

	public static List<Assento> ListaAssentos(Integer idVoo) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		List<Assento> listaAssento = new ArrayList<Assento>();

		String sql = "select * from assento where voo_idvoo=?  and exclusaoLogica = '1' order by idassento";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, idVoo);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Assento assento = new Assento();
				assento.setNumeroAssento(rs.getInt("idassento"));
				assento.setIdVoo(rs.getInt("voo_idvoo"));
				assento.setOcupado(rs.getBoolean("ocupado"));
				assento.setOcupante(rs.getInt("usuario_idusuario"));
				assento.setComfirmaPagamento(rs.getBoolean("comfirmaPagamento"));
				if (assento.isOcupado()) {
					listaAssento.add(assento);
				} else {

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAssento;
	}

	public static List<Assento> ListaAssentosDesocupados(Integer idVoo) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		List<Assento> listaAssento = new ArrayList<Assento>();

		String sql = "select * from assento where voo_idvoo=?  and exclusaoLogica = '1' order by idassento";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, idVoo);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Assento assento = new Assento();
				assento.setNumeroAssento(rs.getInt("idassento"));
				assento.setIdVoo(rs.getInt("voo_idvoo"));
				assento.setOcupado(rs.getBoolean("ocupado"));
				assento.setComfirmaPagamento(rs.getBoolean("comfirmaPagamento"));
				if (assento.isOcupado()) {

				} else {
					listaAssento.add(assento);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAssento;
	}

	// Insere assentos ao cadastrar um voo voos
	public static void inserirAssentosPorVoo(Integer qtdDeAssentos) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		Voo voo = new Voo();

		String sql = "INSERT INTO assento VALUES(?,false,?,0,1,0)";
		String sql1 = "SELECT * FROM voo WHERE idvoo= (SELECT MAX(idvoo) FROM voo)  and exclusaoLogica = '1'";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql1);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				voo.setIdVoo(rs.getInt("idVoo"));
				rs.close();
			}
		} catch (SQLException e) {

		}

		for (int assento = 0; assento < qtdDeAssentos; assento++) {
			try {
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setInt(1, assento);
				ps.setInt(2, voo.getIdVoo());

				ps.execute();
				conexao.commit();
			} catch (SQLException e) {
				conexao.rollback();
				e.printStackTrace();
				throw new SQLException();
			}
		}
		conexao.close();
	}

	public static List<Assento> listarAssentosPorUsuarioIdVooId(Integer usuarioId, Integer vooId) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		List<Assento> listaAssentos = new ArrayList<Assento>();

		String sql = "SELECT * FROM assento WHERE USUARIO_IDUSUARIO = ? and voo_idvoo =?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, usuarioId);
			ps.setInt(2, vooId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Assento assento = new Assento();
				assento.setNumeroAssento(rs.getInt("idassento"));
				assento.setIdVoo(rs.getInt("voo_idvoo"));
				assento.setOcupado(rs.getBoolean("ocupado"));
				assento.setComfirmaPagamento(rs.getBoolean("comfirmaPagamento"));
				if (assento.isOcupado()) {
					assento.setOcupado(true);
					listaAssentos.add(assento);
				} else {
					assento.setOcupado(false);
					listaAssentos.remove(assento);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAssentos;
	}

	public static List<Assento> listarAssentosPorUsuarioId(Integer usuarioId) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		List<Assento> listaAssentos = new ArrayList<Assento>();

		String sql = "SELECT * FROM assento WHERE USUARIO_IDUSUARIO = ? and exclusaoLogica = '1'";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, usuarioId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Assento assento = new Assento();
				assento.setNumeroAssento(rs.getInt("idassento"));
				assento.setIdVoo(rs.getInt("voo_idvoo"));
				assento.setOcupado(rs.getBoolean("ocupado"));
				assento.setComfirmaPagamento(rs.getBoolean("comfirmaPagamento"));
				if (assento.isOcupado()) {
					assento.setOcupado(true);
					listaAssentos.add(assento);
				} else {
					assento.setOcupado(false);
					listaAssentos.remove(assento);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAssentos;
	}

	// Ao excluir um usuario, desocupar seus assentos
	public static Boolean desocuparAssentoPorUsuarioId(Integer usuarioId) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		String sql = "UPDATE assento SET ocupado ='0' WHERE USUARIO_IDUSUARIO = ? ";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, usuarioId);

			ps.execute();
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}
		return true;
	}

	// Ao excluir um usuario desocupar seus assentos
	public static Boolean PagamentoAssentoPorUsuarioId(Integer usuarioId, Integer numeroAssento, Integer vooId)
			throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		String sql = "UPDATE assento SET comfirmaPagamento ='1',  USUARIO_IDUSUARIO = ? WHERE idassento = ? and voo_idvoo = ?";

		try {
			System.out.println("GRAVANDO CONFIRMAÇÃO DE PAGAMENTO");
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, usuarioId);
			ps.setInt(2, numeroAssento);
			ps.setInt(3, vooId);

			ps.execute();
			conexao.commit();
		} catch (SQLException e) {
			System.out.println("ERRO AO GRAVAR CONFIRMAÇÃO DE PAGAMENTO");
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}
		return true;
	}

	public static List<Assento> listarAssentosPagosPorUsuarioIdVooId(Integer usuarioId, Integer vooId)
			throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		List<Assento> listaAssentos = new ArrayList<Assento>();

		String sql = "SELECT * FROM assento WHERE USUARIO_IDUSUARIO = ? and voo_idvoo =? and comfirmaPagamento ='1'";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, usuarioId);
			ps.setInt(2, vooId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Assento assento = new Assento();
				assento.setNumeroAssento(rs.getInt("idassento"));
				assento.setIdVoo(rs.getInt("voo_idvoo"));
				assento.setOcupado(rs.getBoolean("ocupado"));
				assento.setComfirmaPagamento(rs.getBoolean("comfirmaPagamento"));
				if (assento.isOcupado()) {
					assento.setOcupado(true);
					listaAssentos.add(assento);
				} else {
					assento.setOcupado(false);
					listaAssentos.remove(assento);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAssentos;
	}

	public static List<Assento> listarAssentosPorUsuarioIdVooIdPagamentoNaoConfirmado(Integer usuarioId, Integer vooId)
			throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		List<Assento> listaAssentos = new ArrayList<Assento>();

		String sql = "SELECT * FROM assento WHERE USUARIO_IDUSUARIO = ? and voo_idvoo =? and comfirmaPagamento = '0'";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, usuarioId);
			ps.setInt(2, vooId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Assento assento = new Assento();
				assento.setNumeroAssento(rs.getInt("idassento"));
				assento.setIdVoo(rs.getInt("voo_idvoo"));
				assento.setOcupado(rs.getBoolean("ocupado"));
				assento.setComfirmaPagamento(rs.getBoolean("comfirmaPagamento"));
				if (assento.isOcupado()) {
					assento.setOcupado(true);
					listaAssentos.add(assento);
				} else {
					assento.setOcupado(false);
					listaAssentos.remove(assento);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAssentos;
	}

	public static void desocupaLogofAssentoPagamentoNaoConfirmado(Integer usuarioId) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		
		// String sql = "SELECT * FROM assento WHERE USUARIO_IDUSUARIO = ?
		// comfirmaPagamento = '0'";
		String sql = "UPDATE assento SET  ocupado = 0  WHERE  usuario_idusuario = ? and comfirmaPagamento = '0' ";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, usuarioId);

			ps.execute();
			conexao.commit();
		} catch (SQLException e) {
			System.out.println("ERRO AO GRAVAR CONFIRMAÇÃO DE PAGAMENTO");
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}

	}

}
