package br.com.suam.TESTE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.suam.factory.ConnectionFactory;

public class createDB {

	public static void main(String[] args) {

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
				if (linha !=null) {
					script = script + linha;
				}
			}

			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		// **************************************************************************

		String createDB = (script);
		// String insertDB = ("insert into usuario(idusuario, nome, sobrenome, endereco,
		// login, senha, datanascimento, isadm )\n"
		// + "values(0,'0' , '','', '', '', '15/04/1988', false);");

		Connection conexao = null;
		try {
			conexao = ConnectionFactory.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Statement st = null;
		ResultSet rs = null;
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
				System.out.println("COMANDO EXECUTADO: " + createDBaux[i] + ";");
				st.executeUpdate(createDBaux[i] + ";  ");
				// st.executeUpdate(insertDBaux[i] + "; ");
				conexao.commit();
			}

		} catch (SQLException e) {
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
