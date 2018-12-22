package com.suam.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import com.suam.factory.ConnectionFactory;

public class DataUtils {

	// formatar datas
	public static SimpleDateFormat formatarDataBarra_dd_mm_yyyy() {
		// convertendo data para string
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		// Date data = formato.parse("23/11/2015");
		// Date data = formato.format("23/11/2015");
		return formato;
	}

	// formatar datas
	public static SimpleDateFormat formatarDataTraco_yyyy_mm_dd() {
		// convertendo data para string
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		// Date data = formato.parse("23/11/2015");
		// Date data = formato.format("23/11/2015");
		return formato;
	}

	// formatar datas
	public static SimpleDateFormat converterData_yyyy_mm_dd(String data) {
		String[] dataYYYY_MM_DD = data.split("/");
		String dataConvertida;
		int i;
		for (i = 2; i >= 0; i--) {
			if (i != 0) {
				dataConvertida = dataYYYY_MM_DD[i] + "/";
			} else {
				dataConvertida = dataYYYY_MM_DD[i];
			}
		}

		// convertendo data para string
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		// Date data = formato.parse("23/11/2015");
		// Date data = formato.format("23/11/2015");
		return formato;
	}

	public static String gravarDataEHoraAtualBD() throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		// PEGANDO A USUARIO_DATA ATUAL NO SERVER DB:::
		String agora = null;
		Statement statement = conexao.createStatement();
		try {
			statement.execute("SELECT DATE_FORMAT(now(), '%y-%m-%d %H:%i:%s')as 'agora'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = statement.getResultSet();
		try {
			while (rs.next()) {
				agora = rs.getString("agora");
				System.out.println("Hora Atual: " + rs.getString("agora"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// conexao.close();
		return agora;
	}

	public static java.sql.Date dateJavaUtiltoSql(java.util.Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		System.out.println("*******dateJavaUtiltoSql*********");
		System.out.println("utilDate:" + date);
		System.out.println("sqlDate:" + sqlDate);
		return sqlDate;
	}

	public static java.util.Date dateSqltoJavaUtil(java.sql.Date sqlDate) {
		java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
		System.out.println("*******dateSqltoJavaUtil**********");
		System.out.println("utilDate:" + utilDate);
		System.out.println("sqlDate:" + sqlDate);
		return utilDate;
	}
	/*
	 * public static String dataAtualSistema() { LocalDate hoje = LocalDate.now();
	 * DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyy");
	 * String hojeFormatado = hoje.format(formatador);
	 * System.out.println("LocalDateTime depois de formatar: " + hojeFormatado);
	 * return hojeFormatado; }
	 * 
	 * public static String dataEhoraAtualSistema() { // Obtém LocalDateTime de
	 * agora LocalDateTime agora = LocalDateTime.now(); SimpleDateFormat formato =
	 * new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); DateTimeFormatter formatador =
	 * DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"); java.util.Date date =
	 * null; try { date = formato.parse(agora.format(formatador).toString()); }
	 * catch (ParseException e) { e.printStackTrace(); } System.out.println("====>>"
	 * + date); return date.toString(); }
	 * 
	 * public static void dataFormato() { // criação de datas com a nova classe
	 * LocalDate e LocalDate localDate = LocalDate.now();
	 * System.out.println(localDate); System.out.println("Dia da semana: " +
	 * localDate.getDayOfWeek().name()); System.out.println("Dia da semana: " +
	 * localDate.getDayOfWeek().ordinal()); System.out.println("Mes: " +
	 * localDate.getMonthValue()); System.out.println("Mes: " +
	 * localDate.getMonth().name()); System.out.println("Ano: " +
	 * localDate.getYear()); }
	 * 
	 * public static long comparaInstantesDiferentes() { // comparação entre
	 * diferentes instantes de tempo Instant iInicial = Instant.now(); try {
	 * Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
	 * Instant iFinal = Instant.now();
	 * 
	 * Duration duracao = Duration.between(iInicial, iFinal);
	 * System.out.println("Duração em nanos segundos: " + duracao.toNanos());
	 * System.out.println("Duração em minutos: " + duracao.toMinutes());
	 * System.out.println("Duração em horas: " + duracao.toHours());
	 * System.out.println("Duração em milisegundos: " + duracao.toMillis());
	 * System.out.println("Duração em dias: " + duracao.toDays());
	 * 
	 * return duracao.toMillis(); }
	 * 
	 * public static void comparaDatas() { // comparação de datas (antes, depois,
	 * período entre duas datas) LocalDate localDateAntigo = LocalDate.of(2010, 3,
	 * 7); LocalDate localDateNovo = LocalDate.of(2015, 3, 5);
	 * 
	 * System.out.println(localDateAntigo.isAfter(localDateNovo));
	 * System.out.println(localDateAntigo.isBefore(localDateNovo));
	 * System.out.println(localDateAntigo.isEqual(localDateNovo));
	 * 
	 * Period periodo = Period.between(localDateAntigo, localDateNovo); System.out
	 * .println(periodo.getYears() + " Anos " + periodo.getMonths() + " Meses " +
	 * periodo.getDays() + " Dias"); System.out.println("Apenas meses: " +
	 * periodo.toTotalMonths()); }
	 * 
	 * public static void operacaoComData() { // operações em datas como adição e
	 * subtração de dias, meses e anos LocalDate dataManipulacao = LocalDate.now();
	 * System.out.println("Data Original: " + dataManipulacao);
	 * 
	 * System.out.println("Mais 5 dias: " + dataManipulacao.plusDays(5));
	 * System.out.println("Mais 5 semanas: " + dataManipulacao.plusWeeks(5));
	 * System.out.println("Mais 5 anos: " + dataManipulacao.plusYears(5));
	 * System.out.println("Mais 2 meses: " + dataManipulacao.plusMonths(2));
	 * System.out.println("Menos 1 ano: " + dataManipulacao.minusYears(1));
	 * System.out.println("Menos 1 mês: " + dataManipulacao.minusMonths(1));
	 * System.out.println("Menos 3 dia: " + dataManipulacao.minusDays(3));
	 * 
	 * // classe LocalDate é imutável System.out.println("Data Original: " +
	 * dataManipulacao); }
	 * 
	 * public static void formataDataNovaAPI() { // formatação de datas com a nova
	 * API LocalDate hoje = LocalDate.now(); DateTimeFormatter formatadorBarra =
	 * DateTimeFormatter.ofPattern("dd/MM/yyyy"); DateTimeFormatter formatadorTraco
	 * = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	 * 
	 * System.out.println("Data com /: " + hoje.format(formatadorBarra));
	 * System.out.println("Data com -: " + hoje.format(formatadorTraco)); }
	 * 
	 * public static void utils() { LocalDate data = LocalDate.now();
	 * System.out.println("Ano bissexto: " + data.isLeapYear());
	 * System.out.println("Número de dias do mês: " + data.lengthOfMonth());
	 * System.out.println("Número de dias do ano: " + data.lengthOfYear());
	 * System.out.println("Menor data: " + LocalDate.MIN);
	 * System.out.println("Maior data: " + LocalDate.MAX); }
	 * 
	 * public static void obtemLocalDate() { // Obtém LocalDate de hoje LocalDate
	 * hoje2 = LocalDate.now(); System.out.println("LocalDate antes de formatar: " +
	 * hoje2); DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern("dd/MM/yyyy"); String hojeFormatado =
	 * hoje2.format(formatter); System.out.println("LocalDate depois de formatar: "
	 * + hojeFormatado); }
	 * 
	 * public static void obtemLocalDateTime() { // Obtém LocalDate de hoje
	 * LocalDateTime agora = LocalDateTime.now();
	 * System.out.println("LocalDateTime antes de formatar: " + agora);
	 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 * formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); String
	 * agoraFormatado = agora.format(formatter);
	 * System.out.println("LocalDateTime depois de formatar: " + agoraFormatado); }
	 */
	// TESTES

}
