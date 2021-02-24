package br.com.zup.estrelas.trilhas.nivel1.desafio5.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.trilhas.nivel1.desafio5.entity.Cliente;
import br.com.zup.estrelas.trilhas.nivel1.desafio5.exceptions.ClienteExistsException;
import br.com.zup.estrelas.trilhas.nivel1.desafio5.exceptions.InsuficientParametersExceptions;
import br.com.zup.estrelas.trilhas.nivel1.desafio5.exceptions.ResourceNotFoundException;
import br.com.zup.estrelas.trilhas.nivel1.desafio5.repository.ClienteRepository;
import br.com.zup.estrelas.trilhas.nivel1.desafio5.service.IClienteService;

@Service
public class ClienteService implements IClienteService {

	private static final String CPF_JA_CADASTRADO = "Cpf já existe no cadastro do banco de dados.";

	private static final String CLIENTE_NAO_EXISTE = "Cpf não existe no banco de dados.";

	private static final String CLIENTE_EXCLUIDO_COM_SUCESSO = "Cliente excluído do cadastro com sucesso.";

	private static final String ERRO_AO_ALTERAR_CADASTRO = "Erro ao tentar alterar cadastro.";

	private static final String NAO_EXISTEM_CADASTROS = "Não existem Cpf´s cadastrados no Banco de dados";
	
	private static final String CAMPO_CPF_NULO = "O campo Cpf não pode ser nulo.";
	
	@Autowired
	ClienteRepository clienteRepository;
	
	private static final Logger LOGGER = Logger.getLogger(ClienteService.class);

	@Override
	public Cliente insereCliente(Cliente cliente) {
		if (clienteRepository.existsById(cliente.getCpf())) {
			LOGGER.info(CPF_JA_CADASTRADO);
			throw new ClienteExistsException(CPF_JA_CADASTRADO);
		}
		
		if (cliente.getCpf() == null) {
			LOGGER.info(CAMPO_CPF_NULO);
			throw new InsuficientParametersExceptions(CAMPO_CPF_NULO);
		}
		LOGGER.info("Cliente de cpf: " + cliente.getCpf() +" cadstrado com sucesso");
		return this.adicionaCliente(cliente);
	}

	@Override
	public Cliente alteraCliente(Cliente clienteAlterado) {
		if (!clienteRepository.existsById(clienteAlterado.getCpf())) {
			LOGGER.info(CLIENTE_NAO_EXISTE);
			throw new ResourceNotFoundException(CLIENTE_NAO_EXISTE);
		}
		return this.modificaCadastroCliente(clienteAlterado);
	}

	@Override
	public Cliente consultaCliente(String cpf) {
		
		if (!clienteRepository.existsById(cpf)) {
			LOGGER.info(CLIENTE_NAO_EXISTE);
			throw new ResourceNotFoundException(CLIENTE_NAO_EXISTE);
		}
		return clienteRepository.findById(cpf).get();
	}

	@Override
	public List<Cliente> listaClientes() {

		return this.consultaListaDeClientes();
	}

	@Override
	public String excluiCadastro(String cpf) {

		if (!clienteRepository.existsById(cpf)) {
			LOGGER.info(CLIENTE_NAO_EXISTE);
			throw new ResourceNotFoundException(CLIENTE_NAO_EXISTE);
		}
		return this.deletaCadastro(cpf);
	}

	private Cliente adicionaCliente(Cliente cliente) throws ResourceNotFoundException {

		clienteRepository.save(cliente);

		return cliente;
	}

	private Cliente modificaCadastroCliente(Cliente cliente) {
		try {
			clienteRepository.save(cliente);
		} catch (Exception e) {
			LOGGER.info(ERRO_AO_ALTERAR_CADASTRO);
			e.getMessage();
		}
		return cliente;
	}

	private List<Cliente> consultaListaDeClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();

		clientes = (List<Cliente>) clienteRepository.findAll();

		if (clientes.isEmpty()) {
			throw new ResourceNotFoundException(NAO_EXISTEM_CADASTROS);
		}
		return clientes;
	}

	private String deletaCadastro(String cpf) {

		clienteRepository.deleteById(cpf);
		LOGGER.info(CLIENTE_EXCLUIDO_COM_SUCESSO);
		return CLIENTE_EXCLUIDO_COM_SUCESSO;
	}

}
