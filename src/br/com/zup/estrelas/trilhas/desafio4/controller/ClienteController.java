package br.com.zup.estrelas.trilhas.desafio4.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.zup.estrelas.trilhas.desafio4.dao.ClienteDao;
import br.com.zup.estrelas.trilhas.desafio4.pojo.Cliente;

@Path("/cliente")
public class ClienteController {

	ClienteDao clienteDao;

	@PostConstruct
	private void init() {
		clienteDao = new ClienteDao();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void insereCliente(Cliente novoCliente) {
		try {
			clienteDao.adicionaCliente(novoCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> listaClientes() {
		List<Cliente> lista = null;

		try {
			lista = clienteDao.listaClientes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente buscaCliente(String cpf) {
		Cliente cliente = new Cliente();
		
		try {
			cliente = clienteDao.buscaCliente(cpf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cliente;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente alteraCadastro (Cliente clienteAlterado) {
		try {
			clienteDao.alteraCadastro(clienteAlterado.getCpf(), clienteAlterado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clienteAlterado;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void deletaCadastro (String cpf) {
		try {
			clienteDao.excluirCadastro(cpf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
