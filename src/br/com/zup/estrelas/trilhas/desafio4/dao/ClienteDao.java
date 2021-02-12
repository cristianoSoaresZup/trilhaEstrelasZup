package br.com.zup.estrelas.trilhas.desafio4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.zup.estrelas.trilhas.desafio4.exception.ClienteException;
import br.com.zup.estrelas.trilhas.desafio4.factory.ConnectionFactory;
import br.com.zup.estrelas.trilhas.desafio4.pojo.Cliente;

public class ClienteDao {
	
	public ClienteDao () {
		super();
	}
	
	private List<Cliente> clientes = new ArrayList<Cliente>();

	public void adicionaCliente(Cliente novoCliente) throws ClienteException {
		
		for (Cliente cliente : clientes) {
			if (cliente != null && this.clienteExistente(novoCliente.getCpf())) {
				throw new ClienteException("Cliente já cadastrado no banco de dados.");
			}
		}

		String sql = "INSERT INTO cliente (cpf, email, nome, idade, telefone, endereco) VALUES (?, ?, ?, ?, ?, ?) " ;
		
		try {
			Connection connection = ConnectionFactory.conexao();

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
			
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
		
//		clientes.add(novoCliente);
	}

	public Cliente buscaCliente(String cpf) throws ClienteException {

		if (clientes.size() == 0) {
			throw new ClienteException("Não existem clientes cadastrados.");
		}

		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCpf().equals(cpf)) {
				return clientes.get(i);
			}
		}
		throw new ClienteException("Cliente não enconrado");
	}

	public List<Cliente> listaClientes() throws ClienteException {

		if (clientes.size() == 0) {
			throw new ClienteException("Não há clientes cadastrados");
		}
		return clientes;

	}

	public void alteraCadastro(Cliente cliente) throws ClienteException {

		Cliente clienteAlterado = new Cliente();

		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCpf().equals(cliente.getCpf())) {
				clienteAlterado = clientes.get(i);

				clienteAlterado.setEmail(cliente.getEmail());
				clienteAlterado.setNome(cliente.getNome());
				clienteAlterado.setIdade(cliente.getIdade());
				clienteAlterado.setTelefone(cliente.getTelefone());
				clienteAlterado.setEndereco(cliente.getEndereco());
				clienteAlterado.setCpf(cliente.getCpf());

				clientes.remove(clientes.get(i));
				clientes.add(clienteAlterado);

			} else {
				throw new ClienteException("Cadastro não encontrado.");
			}
		}
	}

	public String excluirCadastro(String cpf) throws ClienteException {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCpf().equals(cpf)) {
				clientes.remove(i);

				return "Cliente excluído com sucesso.";
			}
		}
		throw new ClienteException("O cpf: " + cpf + "não está cadastrado.");
	}

	public boolean clienteExistente(String cpf) throws ClienteException {

		if (clientes.size() == 0) {
			throw new ClienteException("Nenhum cliente cadastrado");
		}

		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCpf().equals(cpf)) {
				return true;
			}
		}
		return false;

	}
}
