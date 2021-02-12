package br.com.zup.estrelas.trilhas.desafio4.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String DATA_BASE_URL = "jdbc : mysql://localhost : 3306/clientes";

	public static Connection createdConnection() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(DATA_BASE_URL, USERNAME, PASSWORD);

		return connection;
	}
}
