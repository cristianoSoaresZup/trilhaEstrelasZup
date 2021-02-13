package br.com.zup.estrelas.trilhas.desafio4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.zup.estrelas.trilhas.desafio4.exception.ClienteException;
import br.com.zup.estrelas.trilhas.desafio4.factory.ConnectionFactory;
import br.com.zup.estrelas.trilhas.desafio4.pojo.Cliente;

public class ClienteDao {

	private Connection connection;

	public ClienteDao() throws Exception {
		new ConnectionFactory();
		this.connection = ConnectionFactory.conexao();
	}

	private static void whilePadraoBusca(ResultSet rs, List<Cliente> clientes) throws SQLException {

		while (rs.next()) {

			Cliente clientePesquisado = new Cliente();

			clientePesquisado.setCpf(rs.getString("cpf"));
			clientePesquisado.setEmail(rs.getString("email"));
			clientePesquisado.setEndereco(rs.getString("endereco"));
			clientePesquisado.setIdade(rs.getInt("idade"));
			clientePesquisado.setNome(rs.getString("nome"));
			clientePesquisado.setTelefone(rs.getString("telefone"));

			clientes.add(clientePesquisado);
		}
	}

	public boolean adicionaCliente(Cliente novoCliente) throws SQLException {

		String sql = "INSERT INTO cliente (cpf, email, nome, idade, telefone, endereco) VALUES (?, ?, ?, ?, ?, ?) ";

		PreparedStatement pstm = connection.prepareStatement(sql);

		pstm.setString(1, novoCliente.getCpf());
		pstm.setString(2, novoCliente.getEmail());
		pstm.setString(3, novoCliente.getNome());
		pstm.setInt(4, novoCliente.getIdade());
		pstm.setString(5, novoCliente.getTelefone());
		pstm.setString(6, novoCliente.getEndereco());
		pstm.execute();
		pstm.close();
		connection.close();

		return true;
	}

	public Cliente buscaCliente(String cpf) throws ClienteException {

		Cliente cliente = new Cliente();

		String sql = "SELECT c.* FROM trilhas_estrelas.cliente c WHERE ?";

		try {
			PreparedStatement pesquisa = connection.prepareStatement(sql);
			pesquisa.setString(1, cpf);
			ResultSet rs = pesquisa.executeQuery();

			while (rs.next()) {
				Cliente clientePesquisado = new Cliente();

				clientePesquisado.setCpf(rs.getString("cpf"));
				clientePesquisado.setEmail(rs.getString("email"));
				clientePesquisado.setEndereco(rs.getString("endereco"));
				clientePesquisado.setIdade(rs.getInt("idade"));
				clientePesquisado.setNome(rs.getString("nome"));
				clientePesquisado.setTelefone(rs.getString("telefone"));

				cliente = clientePesquisado;
			}

		} catch (Exception e) {
			System.err.println("Erro ao consultar cliente pelo cpf.");
			System.err.println(e.getMessage());
		}
		return cliente;
	}

	public List<Cliente> listaClientes() throws ClienteException {

		List<Cliente> clientes = new ArrayList<Cliente>();

		String sql = "SELECT * FROM trilhas_estrelas ";

		try {
			PreparedStatement consulta = connection.prepareStatement(sql);
			ResultSet rs = consulta.executeQuery();

			whilePadraoBusca(rs, clientes);
			consulta.close();

		} catch (Exception e) {
			e.getMessage();
		}
		return clientes;
	}

	public void alteraCadastro(Cliente cliente) throws ClienteException {

		Cliente clienteAlterado = new Cliente();


				clienteAlterado.setEmail(cliente.getEmail());
				clienteAlterado.setNome(cliente.getNome());
				clienteAlterado.setIdade(cliente.getIdade());
				clienteAlterado.setTelefone(cliente.getTelefone());
				clienteAlterado.setEndereco(cliente.getEndereco());
				clienteAlterado.setCpf(cliente.getCpf());

		}
	

	public String excluirCadastro(String cpf) throws ClienteException {

				return "Cliente excluído com sucesso.";
	}

	public boolean clienteExistente(String cpf) throws ClienteException {

				return true;

	}
}
