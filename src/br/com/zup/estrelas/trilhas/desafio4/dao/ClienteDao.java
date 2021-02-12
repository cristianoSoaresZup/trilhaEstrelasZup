package br.com.zup.estrelas.trilhas.desafio4.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.estrelas.trilhas.desafio4.exception.ClienteException;
import br.com.zup.estrelas.trilhas.desafio4.pojo.Cliente;

public class ClienteDao {

	private List<Cliente> clientes = new ArrayList<Cliente>();

	public ClienteDao() {

	}

	public void adicionaCliente(Cliente novoCliente) throws ClienteException {

		String sql = "INSERT INTO clientes (nome, idade, cpf, email, telefone, endereco) VALUES (?, ?, ?, ?, ?, ?) " ;
		
		for (Cliente cliente : clientes) {
			if (cliente != null && this.clienteExistente(novoCliente.getCpf())) {
				throw new ClienteException("Cliente j� cadastrado no banco de dados.");
			}
		}

		clientes.add(novoCliente);
	}

	public Cliente buscaCliente(String cpf) throws ClienteException {

		if (clientes.size() == 0) {
			throw new ClienteException("N�o existem clientes cadastrados.");
		}

		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCpf().equals(cpf)) {
				return clientes.get(i);
			}
		}
		throw new ClienteException("Cliente n�o enconrado");
	}

	public List<Cliente> listaClientes() throws ClienteException {

		if (clientes.size() == 0) {
			throw new ClienteException("N�o h� clientes cadastrados");
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
				throw new ClienteException("Cadastro n�o encontrado.");
			}
		}
	}

	public String excluirCadastro(String cpf) throws ClienteException {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCpf().equals(cpf)) {
				clientes.remove(i);

				return "Cliente exclu�do com sucesso.";
			}
		}
		throw new ClienteException("O cpf: " + cpf + "n�o est� cadastrado.");
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
