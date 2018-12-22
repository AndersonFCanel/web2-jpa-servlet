package br.com.suam.TESTE;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.suam.factory.ConnectionFactory;



public class TestaListagem {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection connection = ConnectionFactory.getConnection();
		Statement statement = connection.createStatement();
		boolean resultado = statement.execute("select * from cartaodecredito");
		ResultSet resultSet = statement.getResultSet();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("usuario_idusuario");
			String nome = resultSet.getString("numerocartao");
			System.out.println("id=" + id + ", numero=" + nome);
		}
		
		resultSet.close();
		statement.close();
		connection.close();
	}


}
