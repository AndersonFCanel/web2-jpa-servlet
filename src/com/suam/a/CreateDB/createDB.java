package com.suam.a.CreateDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.suam.factory.ConnectionFactory;

public class createDB {

	public static void main(String[] args) throws SQLException {

		// **************************************
		// **************************************
		// **********************CRIAÇÃO DO BANCO
		// **************************************
		// **************************************
		Connection conexao = null;
		Statement stmt = null;
		Statement stmt0 = null;

		String URL = "jdbc:mysql://localhost?useTimezone=true&serverTimezone=UTC";
		String USERNAME = "root";
		String PASSWORD = "root";

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// STEP 3: Open a connection
			// System.out.println("Connecting to database...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {

			e.printStackTrace();
			return;
		}
		try {
			// STEP 4: Execute a query
			System.out.println("Creating database...");
			stmt0 = conexao.createStatement();
			stmt = conexao.createStatement();

			String sql0 = "Drop DATABASE IF EXISTS dbweb2";
			String sql = "CREATE DATABASE dbweb2";
			stmt0.executeUpdate(sql0);
			stmt.executeUpdate(sql);

			System.out.println("Database created successfully...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e1) {
			// Handle errors for Class.forName
			e1.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conexao != null)
					conexao.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Agora vamoos criar tabelas e inserir dados>>>!");
		// end main

		// ********LEITURA DO SCRIPT
		// Scanner ler = new Scanner(System.in);
		// System.out.printf("Informe o nome de arquivo texto:\n");
		// String nome = ler.nextLine();

		String nome = "arquivos/dbweb2.txt";
		String script = "";

		// System.out.printf("\nConteúdo do arquivo texto:\n");
		try {
			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();

			// lê a primeira linha
			// a variável "linha" recebe o valor "null" quando o processo
			// de repetição atingir o final do arquivo texto
			while (linha != null) {
				// System.out.printf("%s\n", linha);
				linha = lerArq.readLine(); // lê da segunda até a última linha
				if (linha != null) {
					script = script + linha;
				}
			}
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		// **************************************
		// **************************************
		// *********************CRIAÇÃO DAS TABELAS
		// **************************************
		// **************************************
		String createDB = (script);
		// String insertDB = ("insert into usuario(idusuario, nome, sobrenome, endereco,
		// login, senha, datanascimento, isadm )\n"
		// + "values(0,'0' , '','', '', '', '15/04/1988', false);");
		conexao = null;
		try {
			conexao = ConnectionFactory.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Statement st = null;
		//ResultSet rs = null;
		try {
			st = conexao.createStatement();
			// rs = st.executeQuery("show databases");
			// while (rs.next()) {
			// int i = 1;
			// System.out.println(rs.getString(i));
			// i++;
			// }

			String[] createDBaux = createDB.split(";");
			// String[] insertDBaux = insertDB.split(";");

			for (int i = 0; i < createDBaux.length; i++) {

				if (createDBaux[i].contains("DROP")) {
					System.out.println("COMANDO EXECUTADO: execute " + createDBaux[i] + ";");
					// st.execute(createDBaux[i] + "; ");
				} else {
					System.out.println("COMANDO EXECUTADO: executeUpdate " + createDBaux[i] + ";");
					st.executeUpdate(createDBaux[i] + ";  ");
				}
				// st.executeUpdate(insertDBaux[i] + "; ");
			}
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			System.out.println(e);
		} finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
