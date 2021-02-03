package br.com.zup.estrelas.trilhas.nivel1.desafio2.controller;

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

import br.com.zup.estrelas.trilhas.nivel1.desafio2.entity.Cliente;
import br.com.zup.estrelas.trilhas.nivel1.desafio2.exceptions.ResourceNotFoundException;
import br.com.zup.estrelas.trilhas.nivel1.desafio2.repository.ClienteRepository;
import br.com.zup.estrelas.trilhas.nivel1.desafio2.service.IClienteService;

@RequestMapping("/clientes")
@CrossOrigin
@RestController
public class ClienteController {

	@Autowired
	IClienteService clienteService;
	
	ClienteRepository clienteRepository;

	@GetMapping(path = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Cliente consultaCliente(@PathVariable String cpf) {
		this.verifyIfClienteNotExists(cpf);
		return clienteService.consultaCliente(cpf);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Cliente> listaClientes() {
		List<Cliente> clientes = clienteService.listaClientes();
		if(clientes.isEmpty()) {
			throw new ResourceNotFoundException("Nenhum cliente cadastrado no banco de dados.");
		}
		return clientes;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public String insereCliente(@RequestBody Cliente novoCliente) {
		this.verifyIfClienteExists(novoCliente.getCpf());
		return clienteService.insereCliente(novoCliente);
	}

	@PutMapping(path = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String alteraCadastro(Cliente clienteAlterado) {
		this.verifyIfClienteNotExists(clienteAlterado.getCpf());
		return clienteService.alteraCliente(clienteAlterado);
	}

	@DeleteMapping(path = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String deletaCadastro(@PathVariable String cpf) {
		this.verifyIfClienteNotExists(cpf);
		return clienteService.excluiCadastro(cpf);
	}
	
	private void verifyIfClienteNotExists (String cpf) {
		if (!clienteRepository.existsById(cpf)) {
			throw new ResourceNotFoundException("Cadastro não encontrado pelo CPF: " + cpf);
		}
	}
	
	private void verifyIfClienteExists (String cpf) {
		if (clienteRepository.existsById(cpf)) {
			throw new ResourceNotFoundException("O Cpf: " + cpf + " já está cadatrado no banco de dados. " );
		}
	}
	
}
