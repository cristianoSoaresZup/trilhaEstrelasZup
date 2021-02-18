package br.com.zup.estrelas.trilhas.nivel1.desafio5.controller;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import br.com.zup.estrelas.trilhas.nivel1.desafio5.entity.Cliente;
import br.com.zup.estrelas.trilhas.nivel1.desafio5.exceptions.ClienteExistsException;
import br.com.zup.estrelas.trilhas.nivel1.desafio5.exceptions.InsuficientParametersExceptions;
import br.com.zup.estrelas.trilhas.nivel1.desafio5.exceptions.ResourceNotFoundException;
import br.com.zup.estrelas.trilhas.nivel1.desafio5.repository.ClienteRepository;
import br.com.zup.estrelas.trilhas.nivel1.desafio5.serviceImpl.ClienteService;

@RunWith(MockitoJUnitRunner.class)
public class ClienteControllerTest {

	public static final String CPF_INVALIDO = "O CPF informado não é válido.";
	public static final String CPF_NULO = "O campo Cpf não pode ser nulo.";
	private static final String CPF_JA_CADASTRADO = "Cpf já existe no cadastro do banco de dados.";
	private static final String CLIENTE_NAO_EXISTE = "Cpf não existe no banco de dados.";
	private static final String CLIENTE_EXCLUIDO_COM_SUCESSO = "Cliente excluído do cadastro com sucesso.";

	@Mock
	ClienteRepository clienteRepository;

	@InjectMocks
	ClienteService clienteService;

	@Test
	public void postDeveSalvarNovoClienteComSucesso() {

		Cliente cliente = this.clienteFactory();

		Cliente clienteEsperado = new Cliente();

		Mockito.when(clienteRepository.existsById("781.498.210-31")).thenReturn(false);

		Cliente clienteRetornado = clienteService.insereCliente(cliente);

		BeanUtils.copyProperties(cliente, clienteEsperado);

		Assert.assertEquals(clienteEsperado, clienteRetornado);

	}

	@Test
	public void postNaoDeveSalvarClienteCpfNulo() {
		Cliente cliente = this.clienteFactory();
		cliente.setCpf(null);

		String returnMessage = null;
		String expectedMessage = CPF_NULO;

		try {
			clienteService.insereCliente(cliente);
		} catch (InsuficientParametersExceptions e) {
			System.out.println(e.getMessage());
			returnMessage = e.getMessage();
		}
		Assert.assertEquals(returnMessage, expectedMessage);
	}

	@Test
	public void postNaoDeveSalvarClienteCpfJaCadastrado() {
		Cliente cliente = this.clienteFactory();
		cliente.setCpf("781.498.210-31");

		String returnMessage = null;
		String expectedMessage = CPF_JA_CADASTRADO;

		clienteService.insereCliente(cliente);

		try {
			Mockito.when(clienteRepository.existsById("781.498.210-31")).thenReturn(true);
			clienteService.insereCliente(cliente);
		} catch (ClienteExistsException e) {
			System.out.println(e.getMessage());
			returnMessage = e.getMessage();
		}
		Assert.assertEquals(returnMessage, expectedMessage);
	}

	@Test
	public void getDeveRetornarClienteComSucesso() {
		Optional<Cliente> cliente = Optional.of(this.clienteFactory());

		Mockito.when(clienteRepository.existsById("781.498.210-31")).thenReturn(true);
		Mockito.when(clienteRepository.findById("781.498.210-31")).thenReturn(cliente);

		Optional<Cliente> clienteRetornado = Optional.of(clienteService.consultaCliente("781.498.210-31"));

		Assert.assertEquals(clienteRetornado.get(), cliente.get());

	}

	@Test
	public void getNaoDeveRetornarClienteComSucesso() {

		Cliente cliente = this.clienteFactory();
		Mockito.when(clienteRepository.existsById(cliente.getCpf())).thenReturn(false);

		String mensagemRetornada = null;
		String mensagemEsperada = CLIENTE_NAO_EXISTE;
		try {
			clienteService.consultaCliente(cliente.getCpf());
		} catch (ResourceNotFoundException e) {
			mensagemRetornada = e.getMessage();
		}
		Assert.assertEquals(mensagemEsperada, mensagemRetornada);

	}

	@Test
	public void putDeveModificarCadastro() {
		
		Cliente clienteModificado = this.clienteBuid();
		Cliente clienteEsperado = new Cliente();
		
		Mockito.when(clienteRepository.existsById("781.498.210-31")).thenReturn(true);
		Mockito.when(clienteRepository.save(clienteModificado)).thenReturn(clienteModificado);
		
		clienteEsperado = clienteService.alteraCliente(clienteModificado);
		
		Assert.assertEquals(clienteEsperado, clienteModificado);
		
	}

	@Test
	public void putNaoDeveModificarCadastro() {
		Cliente clienteModificado = this.clienteBuid();

		String mensagemEsperada = CLIENTE_NAO_EXISTE;
		String mensagemRetornada = null;
		Mockito.when(clienteRepository.existsById("781.498.210-31")).thenReturn(false);

		try {
			clienteService.alteraCliente(clienteModificado);
		} catch (ResourceNotFoundException e) {
			mensagemRetornada = e.getMessage();
		}
		
		
		Assert.assertEquals(mensagemRetornada, mensagemEsperada);
	}
	
	@Test
	public void deleteDeveExcluirCadastro() {
		Cliente cliente = this.clienteFactory();
		
		String mensagemEsperada = CLIENTE_EXCLUIDO_COM_SUCESSO;
		String mensagemRetornada = null;
		
		Mockito.when(clienteRepository.existsById(cliente.getCpf())).thenReturn(true);
		
		mensagemRetornada = clienteService.excluiCadastro(cliente.getCpf());
		
		Assert.assertEquals(mensagemEsperada, mensagemRetornada);
		
	}
	
	@Test
	public void deleteNaoDeveExcluirCadastro() {
		Cliente cliente = this.clienteFactory();
		
		String mensagemEsperada = CLIENTE_NAO_EXISTE;
		String mensagemRetornada = null;
		
		Mockito.when(clienteRepository.existsById(cliente.getCpf())).thenReturn(false);
		
		try {
			clienteService.excluiCadastro(cliente.getCpf());
		} catch (ResourceNotFoundException e) {
			mensagemRetornada = e.getMessage();
		}
		
		Assert.assertEquals(mensagemEsperada, mensagemRetornada);
		
	}
	
	private Cliente clienteFactory() {
		Cliente cliente = new Cliente();

		cliente.setCpf("781.498.210-31");
		cliente.setEmail("cris@cris.cr");
		cliente.setEndereco("qwertyui");
		cliente.setIdade(42);
		cliente.setNome("cris");
		cliente.setTelefone("1234567890");

		return cliente;
	}
	
	private Cliente clienteBuid() {
		Cliente cliente = new Cliente();
		
		cliente.setCpf("781.498.210-31");
		cliente.setEmail("erro@erro.er");
		cliente.setEndereco("poiuytrewasdfghjk");
		cliente.setIdade(205);
		cliente.setNome("Erro");
		cliente.setTelefone("3434343434343");
		
		return cliente;
	}
	
}

