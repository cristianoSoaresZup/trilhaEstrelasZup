package br.com.zup.estrelas.trilhas.desafio2.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.estrelas.trilhas.desafio2.exception.ClienteException;
import br.com.zup.estrelas.trilhas.desafio2.pojo.Cliente;

public class ClienteDao {

	private List<Cliente> clientes = new ArrayList<Cliente>();

	public ClienteDao() {

	}

	public void adicionaCliente(Cliente novoCliente) throws ClienteException {

		for (Cliente cliente : clientes) {
			if (cliente != null && cliente.getCpf().equals(novoCliente.getCpf())) {
				throw new ClienteException("Cliente já cadastrado no banco de dados.");
			}
		}

		clientes.add(novoCliente);
	}
	
	public  Cliente  buscaCliente(String cpf) throws ClienteException {
		
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
	
	public void alteraCadastro (String cpf, Cliente cliente) throws ClienteException {

		Cliente clienteAlterado = new Cliente();

		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCpf().equals(cpf)) {
				clienteAlterado = clientes.get(i);

				clienteAlterado.setEmail(cliente.getEmail());
				clienteAlterado.setNome(cliente.getNome());
				clienteAlterado.setIdade(cliente.getIdade());
				clienteAlterado.setTelefone(cliente.getTelefone());
				clienteAlterado.setEndereco(cliente.getEndereco());
				clienteAlterado.setCpf(cpf);

				clientes.remove(clientes.get(i));
				clientes.add(clienteAlterado);
			}
		}
		
		throw new ClienteException("Cadastro não encontrado.");
		
	}
	
	public void excluirCadastro (String cpf) throws ClienteException {
		for (int i=0; i < clientes.size(); i ++) {
			if (clientes.get(i).getCpf().equals(cpf)) {
				clientes.remove(i);
				
				System.out.println("Cliente excluído com sucesso.");
			}
		}
		throw new ClienteException("Cadastro não encontrado.");
	}
	
	public boolean clienteExistente (String cpf) throws ClienteException {
		
		if (clientes.size() == 0) {
			throw new ClienteException("Nenhum cliente cadastrado");
		}
		
		for (int i = 0; i < clientes.size(); i ++)  {
			if(clientes.get(i).getCpf().equals(cpf)) {
				return true;
			}
		}
		
		return false;
		
	}
}
