package br.com.zup.estrelas.trilhas.nivel1.desafio3.service;

import java.util.List;

import br.com.zup.estrelas.trilhas.nivel1.desafio3.entity.Cliente;


public interface IClienteService {
public String insereCliente(Cliente cliente) ;
	
	public String alteraCliente(Cliente clienteAlterado) ;
	
	public Cliente consultaCliente(String cpf) ;
	
	public List<Cliente> listaClientes();
	
	public String excluiCadastro(String cpf) ;

}
