package br.com.zup.estrelas.trilhas.nivel1.desafio1.service;

import java.util.List;

import br.com.zup.estrelas.trilhas.nivel1.desafio1.entity.Cliente;
import br.com.zup.estrelas.trilhas.nivel1.desafio1.exceptions.ClienteException;


public interface IClienteService {
public String insereCliente(Cliente cliente) throws ClienteException;
	
	public String alteraCliente(Cliente clienteAlterado) throws ClienteException;
	
	public Cliente consultaCliente(String cpf) throws ClienteException;
	
	public List<Cliente> listaClientes();
	
	public String excluiCadastro(String cpf) throws ClienteException;

}
