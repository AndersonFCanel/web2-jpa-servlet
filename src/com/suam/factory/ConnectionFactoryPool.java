package com.suam.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;
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
public class ConnectionFactoryPool {

	private MysqlDataSource dataSource;

	/**
	 * M�todo para obten��o de conex�o com banco de dados.
	 * 
	 * @author Anderson Ferreira Canel
	 * @return Connection - Retorna conex�o
	 * @throws SQLException
	 */
	public ConnectionFactoryPool() {
		MysqlDataSource pool = new MysqlDataSource();
		pool.setUrl(BancoDeDados.URL);
		pool.setUser(BancoDeDados.USERNAME);
		pool.setPassword(BancoDeDados.PASSWORD);
		this.dataSource = pool;
	}

	public  java.sql.Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
        return connection;
	}
}