package br.com.zup.estrelas.trilhas.desafio4.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public static Connection conexao() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trilhas_estrelas?user=root&password=root"
				+ "&useTimezone=true&serverTimezone=UTC");
		
		return connection;
		
	}
	
	public static void main(String[] args) throws Exception {
		Connection con = conexao();
		if (con != null) {
			System.out.println("conexão ok");
		} else {
			
		System.out.println("conexão nula");
		}
	}
}
