package br.com.suam.TESTE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.suam.bean.Voo;
import com.suam.factory.ConnectionFactory;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		

		
			Connection conexao = ConnectionFactory.getConnection();
			
			String sql = "INSERT INTO assento VALUES(?,false,?,?)";
		

			for (int assento = 0; assento < 100; assento++) {
				try {
					PreparedStatement ps = conexao.prepareStatement(sql);
					ps.setInt(1, assento);
					ps.setInt(2, 5);
					ps.setInt(3, 1);
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
}
