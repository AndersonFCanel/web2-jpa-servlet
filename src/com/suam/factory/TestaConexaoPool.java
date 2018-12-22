package com.suam.factory;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class TestaConexaoPool {

	public static void main(String[] args) throws SQLException {
		ConnectionFactoryPool database = new ConnectionFactoryPool();

        for (int i = 0; i < 100; i++) {
            Connection connection = database.getConnection();

            Statement statement = connection.createStatement();
            boolean resultado = statement.execute("select * from usuario");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("idusuario");
                String nome = resultSet.getString("nome");
                String sobrenome = resultSet.getString("sobrenome");
                System.out.println(id);
                System.out.println(nome);
                System.out.println(sobrenome);
            }
            resultSet.close();
            statement.close();

            connection.close();
        }
	}

}
