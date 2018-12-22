package com.suam.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.suam.bean.PropriedadesDB;
import com.suam.constantes.Constantes.BancoDeDados;

/**
 * <p>
 * ------------------------------------------------------------------------------------------
 * Classe para conx�o com banco de dados.
 * ------------------------------------------------------------------------------------------
 * <p>
 * Data de Cria��o: 08/12/2018
 * 
 * @author Anderson Ferreira Canel
 * @version 1.00
 * @since Release 01
 */
public class ConnectionFactory {

	/**
	 * M�todo para obten��o de conex�o com banco de dados.
	 * 
	 * @author Anderson Ferreira Canel
	 * @return Connection - Retorna conex�o
	 * @throws SQLException
	 */
	public static java.sql.Connection getConnection() throws SQLException {
		// singleton COM PROPERTIES
		/*
		 * if (Controle.propriedadesDB == null) { Controle.propriedadesDB = new
		 * PropriedadesDB(); }
		 */

		Connection conexao = null;
		try {
			/* Obt�m o driver de conex�o com o banco de dados */
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			/* Tenta se conectar */
			conexao = DriverManager.getConnection(BancoDeDados.URL, BancoDeDados.USERNAME, BancoDeDados.PASSWORD);
			
			//Criando o pool de conex�es
			//conexao = ConnectionFactory.getConnection();
			
			
			/* Tenta se conectar COM PROPERTIES */
			/*
			 * conexao = DriverManager.getConnection(Controle.propriedadesDB.getUrl(),
			 * Controle.propriedadesDB.getUsername(),
			 * Controle.propriedadesDB.getPassword());
			 */

			/* Configura commit como N�O Autom�tico */
			conexao.setAutoCommit(false);

			/* Caso a conex�o ocorra com sucesso, a conex�o � retornada */
			// conexao com HSQLDB
			// conexao = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/dbweb2",
			// "SA", "");
			// System.out.println("CONEXAO FACTORY STRING: "+conexao );

			// TENTANDO SE CONECTAR A HSQLDB
			// Class.forName("org.hsqldb.jdbcDriver");

			/* Caso a conex�o ocorra com sucesso, a conex�o � retornada */
			// conexao com HSQLDB
			// conexao = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/dbweb2",
			// "SA", "");
			// System.out.println("CONEXAO FACTORY STRING: "+conexao );
			return conexao;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Nao foi possivel conectar ao banco de dados.");
			return null;
		}
	}
}