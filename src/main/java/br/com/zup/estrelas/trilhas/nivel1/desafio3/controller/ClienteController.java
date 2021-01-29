package br.com.zup.estrelas.trilhas.nivel1.desafio3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.trilhas.nivel1.desafio3.entity.Cliente;
import br.com.zup.estrelas.trilhas.nivel1.desafio3.exceptions.ClienteException;
import br.com.zup.estrelas.trilhas.nivel1.desafio3.service.IClienteService;

@RequestMapping("/clientes")
@CrossOrigin
@RestController
public class ClienteController {

	@Autowired
	IClienteService clienteService;

	@GetMapping(path = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Cliente consultaCliente(@PathVariable String cpf) throws ClienteException {
		return clienteService.consultaCliente(cpf);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Cliente> listaClientes() {
		return clienteService.listaClientes();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public String insereCliente(@RequestBody Cliente novoCliente) throws ClienteException {
		return clienteService.insereCliente(novoCliente);
	}

	@PutMapping(path = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String alteraCadastro(Cliente clienteAlterado) throws ClienteException {
		return clienteService.alteraCliente(clienteAlterado);
	}

	@DeleteMapping(path = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String deletaCadastro(@PathVariable String cpf) throws ClienteException {
		return clienteService.excluiCadastro(cpf);
	}

}
