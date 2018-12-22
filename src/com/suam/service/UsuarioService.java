package com.suam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.suam.bean.Usuario;
import com.suam.factory.ConnectionFactory;
import com.suam.util.ConverteValores;
import com.suam.util.DataUtils;

/**
 * <p>
 * ------------------------------------------------------------------------------------------
 * Classe de Serviço para objeto do tipo Usuario.
 * ------------------------------------------------------------------------------------------
 * <p>
 * Data de Criação: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class UsuarioService {

	
	/**
	 * Método para inserção de usuários no banco de dados.
	 * 
	 * @author Anderson Ferreira Canel
	 * @param usuario
	 *            - É passado um objeto do tipo Usuario.
	 * @return boolean - Se a operação for bem sucedida será retornado true senão
	 *         false.
	 * @link Sem links locais
	 * @throws SQLException
	 */
	public static Boolean inserir(Usuario usuario) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		// Código para Validar se existe usuário com mesmo login no banco.
		if (validaDML(usuario)) {
			// return true;
		} else {
			return false;
		}
		String sql = "INSERT INTO usuario (nome, sobrenome, endereco, senha, login, dataNascimento, isadm ) VALUES (?,?,?,?,?,?,?)";
		try {
			// Obtendo as chaves geradas
			// PreparedStatement ps = conexao.prepareStatement(sql,
			// Statement.RETURN_GENERATED_KEYS);

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSobrenome());
			ps.setString(3, usuario.getEndereco());
			ps.setString(4, usuario.getSenha());
			ps.setString(5, usuario.getLogin());
			ps.setString(6, DataUtils.formatarDataBarra_dd_mm_yyyy().format(usuario.getDataNascimento()));

			if (usuario.getIsAdm()) {
				ps.setInt(7, 1);
			} else {
				ps.setInt(7, 0);
			}
			// ps.setBoolean(7, usuario.getIsAdm());

			// Obtendo as chaves geradas
			// ALTERNATIVA AO - >PreparedStatement ps = conexao.prepareStatement(sql,
			// Statement.RETURN_GENERATED_KEYS);
			/*
			 * ResultSet resultSet = ps.getGeneratedKeys();
			 * 
			 * while (resultSet.next()) { String id = resultSet.getString("id");
			 * System.out.println(id + " gerado"); }
			 */

			System.out.println("SQL: " + ps);

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

	/**
	 * Método para validação de comandos DML, caso já exista algum valor este medodo
	 * não validará a operação.
	 * 
	 * @author Anderson Ferreira Canel
	 * @param user
	 *            - É passado um objeto do tipo Usuario.
	 * @return Boolean - Se a operação for bem sucedida será retornado true senão
	 *         false.
	 * @link Sem links locais
	 * @throws SQLException
	 */
	public static Boolean validaDML(Usuario user) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		List<Usuario> listaUsuario = ListaUsuarios();
		// Código para Validar se existe usuário com mesmo login no banco
		String sql = "Select  * from usuario   where login = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, user.getLogin());
			// ps.execute();

			System.out.println("SQL: " + ps);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setLogin(rs.getString("login"));
				try {
					String dataRecebida = rs.getString("datanascimento");
					usuario.setDataNascimento(DataUtils.formatarDataBarra_dd_mm_yyyy().parse(dataRecebida));
				} catch (ParseException e) {
					e.printStackTrace();
					System.out.println("A data não pode ser convertida");
				}
				usuario.setIsAdm(rs.getBoolean("isAdm"));
				listaUsuario.add(usuario);
			}

			conexao.commit();
			ps.close();
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new SQLException();
		} finally {
			conexao.close();
		}
		if (listaUsuario.size() > 0 || !listaUsuario.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método para atualização de registro de usuário no banco de dados.
	 * 
	 * @author Anderson Ferreira Canel
	 * @param usuario
	 *            - É passado um objeto do tipo Usuario.
	 * @return Boolean - Se a operação for bem sucedida será retornado true senão
	 *         false.
	 * @link Sem links locais
	 * @throws SQLException
	 */
	public static Boolean update(Usuario usuario) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		// Código para Validar se existe usuário com mesmo login no banco.
		// List<Usuario> listaUsuarios = ListaUsuarios();
		// for (Usuario u : listaUsuarios) {
		// if (!u.getId().equals(usuario.getId())) {
		// if (u.getLogin().equals(usuario.getLogin())) {
		// return false;
		// }
		// }
		// }
		// Código para Validar se existe usuário com mesmo login no banco.
		if (validaDML(usuario)) {
			// return true;
		} else {
			return false;
		}

		String sql = "UPDATE usuario SET nome = ?, sobrenome = ?, endereco = ?,  senha = ?, login = ?, dataNascimento = ?, isadm = ? WHERE idusuario = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSobrenome());
			ps.setString(3, usuario.getEndereco());
			ps.setString(4, usuario.getSenha());
			ps.setString(5, usuario.getLogin());
			ps.setString(6, DataUtils.formatarDataBarra_dd_mm_yyyy().format(usuario.getDataNascimento()));
			if (usuario.getIsAdm()) {
				ps.setInt(7, 1);
			} else {
				ps.setInt(7, 0);
			}
			// ps.setBoolean(7, usuario.getIsAdm());
			ps.setInt(8, usuario.getId());

			System.out.println("SQL: " + ps);

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

	/**
	 * Método para obtenção de toda a lista de usuários no banco de dados.
	 * 
	 * @author Anderson Ferreira Canel
	 * @param -
	 *            Esse método não recebe parâmetros.
	 * @return List<Usuario> - Esse método retorna a lista de usuários.
	 * @link Sem links locais
	 * @throws SQLException
	 */
	public static List<Usuario> ListaUsuarios() throws SQLException {

		Connection connection = ConnectionFactory.getConnection();
		List<Usuario> listaUsuario = new ArrayList<Usuario>();

		Statement statement = connection.createStatement();
		statement.execute("select * from usuario where exclusaoLogica = '1' and IDUSUARIO != '0' LIMIT 100");

		ResultSet rs = statement.getResultSet();

		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("idusuario"));
			usuario.setNome(rs.getString("nome"));
			usuario.setSobrenome(rs.getString("sobrenome"));
			usuario.setEndereco(rs.getString("endereco"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setLogin(rs.getString("login"));
			try {
				String dataRecebida = rs.getString("datanascimento");
				usuario.setDataNascimento(DataUtils.formatarDataBarra_dd_mm_yyyy().parse(dataRecebida));
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("A data não pode ser convertida");
			}
			usuario.setIsAdm(rs.getBoolean("isAdm"));
			listaUsuario.add(usuario);
		}

		System.out.println("SQL: " + rs);
		rs.close();
		statement.close();
		connection.close();
		return listaUsuario;
	}

	/**
	 * Método para obtenção de uma lista fracionada referente a toda lista de
	 * usuários no banco de dados.
	 * 
	 * @author Anderson Ferreira Canel
	 * @param String
	 *            pagIni - Indice inicial(linha no banco de dados)
	 * @param String
	 *            pagFim - Indice Final(linha no banco de dados)
	 * 
	 * @return List<Usuario> - Esse método retorna a lista de usuários.
	 * @link Sem links locais
	 * @throws SQLException
	 */
	public static List<Usuario> ListaUsuariosFracao(String pagIni, String pagFim) throws SQLException {

		Connection conexao = ConnectionFactory.getConnection();
		List<Usuario> listaUsuario = new ArrayList<Usuario>();

		String sql = "SELECT * FROM usuario  where exclusaoLogica = '1' and IDUSUARIO != '0' LIMIT ? OFFSET ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, ConverteValores.StringParaInteger(pagFim));
			ps.setInt(2, ConverteValores.StringParaInteger(pagIni));

			System.out.println("CONSULTAR SELECT: " + ps);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setLogin(rs.getString("login"));
				try {
					usuario.setDataNascimento(
							DataUtils.formatarDataBarra_dd_mm_yyyy().parse(rs.getString("datanascimento")));
				} catch (ParseException e) {
					e.printStackTrace();
					System.out.println("A data não pode ser convertida");
				}
				usuario.setIsAdm(rs.getBoolean("isAdm"));
				listaUsuario.add(usuario);
			}
			System.out.println("SQL: " + ps);
			conexao.commit();
			ps.close();

		} catch (SQLException e) {
			System.out.println("ERRO: " + e);
			// conexao.rollback();
		} finally {
			conexao.close();
		}
		return listaUsuario;
	}

	/**
	 * Método para remoção de um usuário no banco de dados.
	 * 
	 * @author Anderson Ferreira Canel
	 * @param -
	 *            Este método não possui parâmetros
	 * 
	 * @return- Esse método não retorna nada.
	 * @link Sem links locais
	 * @throws SQLException
	 */
	public static void delete(Usuario usuario) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();

		// String sql = "DELETE FROM usuario WHERE idusuario = ?";
		String sql = "UPDATE usuario SET exclusaoLogica = '0' WHERE idusuario = ? ";

		System.out.println("Usuario a ser removido: " + usuario.getNome() + " - " + usuario.getSobrenome() + " - "
				+ usuario.getEndereco() + " - " + usuario.getSenha() + " - " + usuario.getLogin() + " - "
				+ usuario.getDataNascimento() + " - " + usuario.getIsAdm());
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			// String id = usuario.getId().toString();
			ps.setInt(1, usuario.getId());

			System.out.println("SQL: " + ps);
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

	// Retorna Usuário através de login e senha
	public static List<Usuario> consultarLoginSenha(String login, String senha) throws SQLException {
		System.out.println("CONSULTAR ==> USUARIO_LOGIN: " + login + " USUARIO_SENHA: " + senha);
		Connection conexao = ConnectionFactory.getConnection();
		List<Usuario> listaUsuario = new ArrayList<Usuario>();

		String sql = "SELECT * FROM usuario where login=? and senha=? and exclusaoLogica = '1' and IDUSUARIO != '0'";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, senha);

			System.out.println("CONSULTAR SELECT: " + ps);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setLogin(rs.getString("login"));
				try {
					usuario.setDataNascimento(
							DataUtils.formatarDataBarra_dd_mm_yyyy().parse(rs.getString("datanascimento")));
				} catch (ParseException e) {
					e.printStackTrace();
					System.out.println("A data não pode ser convertida");
				}
				usuario.setIsAdm(rs.getBoolean("isAdm"));
				listaUsuario.add(usuario);
			}
			System.out.println("SQL: " + ps);
			conexao.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println("ERRO: " + e);
			// conexao.rollback();
		} finally {
			conexao.close();
		}
		return listaUsuario;
	}

	// Se a lista retornada for vazia usuário não está cadastrado
	public static boolean autenticar(String login, String senha) throws SQLException {
		List<Usuario> listaUsuario = consultarLoginSenha(login, senha);
		if (!listaUsuario.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	// Buscar um usuário pelo seu atributo identificador
	public static Usuario buscaUsuarioPelaId(String idUser) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		// String sql = "SELECT * FROM usuario where idusuario = ? and exclusaoLogica =
		// '1' ";
		String sql = "SELECT * FROM usuario where idusuario = ? and exclusaoLogica = '1' ";
		Usuario usuario = new Usuario();

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			// ps.setInt(1, id);
			ps.setInt(1, ConverteValores.StringParaInteger(idUser));
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				usuario.setId(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setLogin(rs.getString("login"));
				try {
					usuario.setDataNascimento(
							DataUtils.formatarDataBarra_dd_mm_yyyy().parse(rs.getString("datanascimento")));
				} catch (ParseException e) {
					e.printStackTrace();
					System.out.println("A data não pode ser convertida");
				}
				usuario.setIsAdm(rs.getBoolean("isAdm"));
			}

			System.out.println("SQL: " + ps);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// conexao.rollback();
		} finally {
			conexao.close();
		}
		return usuario;
	}

	public static Usuario buscaUsuarioPelaIdHistoricoDeCompras(Integer id) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM usuario where idusuario = ? ";
		Usuario usuario = new Usuario();

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				usuario.setId(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setLogin(rs.getString("login"));
				try {
					usuario.setDataNascimento(
							DataUtils.formatarDataBarra_dd_mm_yyyy().parse(rs.getString("datanascimento")));
				} catch (ParseException e) {
					e.printStackTrace();
					System.out.println("A data não pode ser convertida");
				}
				usuario.setIsAdm(rs.getBoolean("isAdm"));
			}
			System.out.println("SQL: " + ps);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// conexao.rollback();
		} finally {
			conexao.close();
		}
		return usuario;
	}

}
