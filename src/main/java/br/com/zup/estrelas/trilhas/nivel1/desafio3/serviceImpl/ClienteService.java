package br.com.zup.estrelas.trilhas.nivel1.desafio3.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.trilhas.nivel1.desafio3.entity.Cliente;
import br.com.zup.estrelas.trilhas.nivel1.desafio3.exceptions.ClienteExistsException;
import br.com.zup.estrelas.trilhas.nivel1.desafio3.exceptions.ResourceNotFoundException;
import br.com.zup.estrelas.trilhas.nivel1.desafio3.repository.ClienteRepository;
import br.com.zup.estrelas.trilhas.nivel1.desafio3.service.IClienteService;

@Service
public class ClienteService implements IClienteService {

	private static final String CPF_JA_CADASTRADO = "Cpf já existe no cadastro do banco de dados.";

	private static final String CLIENTE_NAO_EXISTE = "Cpf não existe no banco de dados.";

	private static final String CLIENTE_CADASTRADO_COM_SUCESSO = "Cliente cadastrado com sucesso.";

	private static final String CADASTRO_ALTERADO_COM_SUCESSO = "Cadastro alterado com sucesso.";

	private static final String CLIENTE_EXCLUIDO_COM_SUCESSO = "Cliente excluído do cadastro com sucesso.";

	private static final String ERRO_AO_ALTERAR_CADASTRO = "Erro ao tentar alterar cadastro.";

	private static final String NAO_EXISTEM_CADASTROS = "Não existem Cpf´s cadastrados no Banco de dados";

	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public String insereCliente(Cliente cliente) {
		if (clienteRepository.existsById(cliente.getCpf())) {
			throw new ClienteExistsException(CPF_JA_CADASTRADO);
		}
		return this.adicionaCliente(cliente);
	}

	@Override
	public String alteraCliente(Cliente clienteAlterado) {
		if (!clienteRepository.existsById(clienteAlterado.getCpf())) {
			throw new ResourceNotFoundException(CLIENTE_NAO_EXISTE);
		}
		return this.modificaCadastroCliente(clienteAlterado);
	}

	@Override
	public Cliente consultaCliente(String cpf) {
		return clienteRepository.findById(cpf).orElseThrow(() -> new ResourceNotFoundException(CLIENTE_NAO_EXISTE));
	}

	@Override
	public List<Cliente> listaClientes() {

		return this.consultaListaDeClientes();
	}

	@Override
	public String excluiCadastro(String cpf) {

		if (!clienteRepository.existsById(cpf)) {
			throw new ResourceNotFoundException(CLIENTE_NAO_EXISTE);
		}
		return this.deletaCadastro(cpf);
	}

	private String adicionaCliente(Cliente cliente) throws ResourceNotFoundException {

		clienteRepository.save(cliente);

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

		return CLIENTE_EXCLUIDO_COM_SUCESSO;
	}

}
