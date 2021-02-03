package br.com.zup.estrelas.trilhas.nivel1.desafio2.service;

import java.util.List;

import br.com.zup.estrelas.trilhas.nivel1.desafio2.entity.Cliente;
import br.com.zup.estrelas.trilhas.nivel1.desafio2.exceptions.ResourceNotFoundException;


public interface IClienteService {
public String insereCliente(Cliente cliente) throws ResourceNotFoundException;
	
	public String alteraCliente(Cliente clienteAlterado) throws ResourceNotFoundException;
	
	public Cliente consultaCliente(String cpf) throws ResourceNotFoundException;
	
	public List<Cliente> listaClientes();
	
	public String excluiCadastro(String cpf) throws ResourceNotFoundException;

}
