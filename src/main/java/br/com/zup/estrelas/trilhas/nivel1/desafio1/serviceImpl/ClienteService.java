package br.com.zup.estrelas.trilhas.nivel1.desafio1.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.trilhas.nivel1.desafio1.entity.Cliente;
import br.com.zup.estrelas.trilhas.nivel1.desafio1.exceptions.ClienteException;
import br.com.zup.estrelas.trilhas.nivel1.desafio1.repository.ClienteRepository;
import br.com.zup.estrelas.trilhas.nivel1.desafio1.service.IClienteService;

@Service
public class ClienteService implements IClienteService {

	private static final String CPF_JA_CADASTRADO = "Cpf já existe no cadastro do banco de dados.";

	private static final String CLIENTE_NAO_EXISTE = "Cpf não existe no banco de dados.";

	private static final String CLIENTE_CADASTRADO_COM_SUCESSO = "Cliente cadastrado com sucesso.";

	private static final String CADASTRO_ALTERADO_COM_SUCESSO = "Cadastro alterado com sucesso.";

	private static final String CLIENTE_EXCLUIDO_COM_SUCESSO = "Cliente excluído do cadastro com sucesso.";

	private static final String ERRO_AO_CADASTRAR_CLIENTE = "Erro ao cadastrar cliente.";

	private static final String ERRO_AO_ALTERAR_CADASTRO = "Erro ao tentar alterar cadastro.";
	
	private static final String ERRO_BUSCAR_CADASTRO ="Erro ao buscar cliente";
	
	private static final String ERRO_BUSCAR_CADASTROS ="Erro ao buscar clientes cadastrados";
	
	private static final String ERRO_AO_TENTAR_DELETAR = "Erro ao deletar cadastro.";
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public String insereCliente(Cliente cliente) throws ClienteException {
		if (clienteRepository.existsById(cliente.getCpf())) {
			throw new ClienteException(CPF_JA_CADASTRADO);
		}
		return this.adicionaCliente(cliente);
	}

	@Override
	public String alteraCliente(Cliente clienteAlterado) throws ClienteException {
		if (!clienteRepository.existsById(clienteAlterado.getCpf())) {
			throw new ClienteException(CLIENTE_NAO_EXISTE);
		}

		return this.modificaCadastroCliente(clienteAlterado);
	}

	@Override
	public Cliente consultaCliente(String cpf) throws ClienteException {
		if (!clienteRepository.existsById(cpf)) {
			throw new ClienteException(CLIENTE_NAO_EXISTE);
		}
		return this.buscaCliente(cpf);
	}

	@Override
	public List<Cliente> listaClientes() {
		
		return this.consultaListaDeClientes();
	}

	@Override
	public String excluiCadastro(String cpf) throws ClienteException {

		return this.deletaCadastro(cpf);
	}

	private String adicionaCliente(Cliente cliente) {

		try {
			clienteRepository.save(cliente);

		} catch (Exception e) {
			System.err.println(ERRO_AO_CADASTRAR_CLIENTE);
			e.getMessage();
		}
		return CLIENTE_CADASTRADO_COM_SUCESSO;
	}

	private String modificaCadastroCliente(Cliente cliente) {

		try {
			clienteRepository.save(cliente);

		} catch (Exception e) {
			System.err.println(ERRO_AO_ALTERAR_CADASTRO);
			e.getMessage();
		}
		return CADASTRO_ALTERADO_COM_SUCESSO;
	}

	private Cliente buscaCliente(String cpf) {
		Cliente cliente = new Cliente();
		try {
			cliente = clienteRepository.findById(cpf).get();
		} catch (Exception e) {
			System.err.println(ERRO_BUSCAR_CADASTRO);
			e.getMessage();
		}
		
		return cliente;
	}
	
	private List<Cliente> consultaListaDeClientes () {
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			clientes = (List<Cliente>) clienteRepository.findAll();
		} catch (Exception e) {
			System.err.println(ERRO_BUSCAR_CADASTROS);
		}
		return clientes;
	}
	
	private String deletaCadastro(String cpf) {
		if (!clienteRepository.existsById(cpf)) {
			return CLIENTE_NAO_EXISTE;
		}
		try {
			clienteRepository.deleteById(cpf);
		} catch (Exception e) {
			System.err.println(ERRO_AO_TENTAR_DELETAR);
			e.getMessage();
		}
		return CLIENTE_EXCLUIDO_COM_SUCESSO;
	}
	
}