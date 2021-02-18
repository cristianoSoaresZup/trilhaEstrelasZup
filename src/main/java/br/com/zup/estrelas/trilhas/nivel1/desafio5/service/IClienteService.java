package br.com.zup.estrelas.trilhas.nivel1.desafio5.service;

import java.util.List;

import br.com.zup.estrelas.trilhas.nivel1.desafio5.entity.Cliente;


public interface IClienteService {
public Cliente insereCliente(Cliente cliente) ;
	
	public Cliente alteraCliente(Cliente clienteAlterado) ;
	
	public Cliente consultaCliente(String cpf) ;
	
	public List<Cliente> listaClientes();
	
	public String excluiCadastro(String cpf) ;

}
