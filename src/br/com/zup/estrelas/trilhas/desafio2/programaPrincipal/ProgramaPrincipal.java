package br.com.zup.estrelas.trilhas.desafio2.programaPrincipal;

import java.util.List;
import java.util.Scanner;

import br.com.zup.estrelas.trilhas.desafio2.dao.ClienteDao;
import br.com.zup.estrelas.trilhas.desafio2.exception.ClienteException;
import br.com.zup.estrelas.trilhas.desafio2.pojo.Cliente;

public class ProgramaPrincipal {

	private static final String MENU_PRINCIPAL = "\n\t||============ CADASTRO DE CLIENTES ============||\n"
			+ "\t||                                                                                                ||\n"
			+ "\t||= = = = = = = = = = = = = = = = = = = = = = = =  = = = = = = = =||\n"
			+ "\t||                                                                                                ||\n"
			+ "\t||=========  1- CADASTRAR NOVO CLIENTE =========||\n"
			+ "\t||=========  2- CONSULTAR CLIENTE PELO CPF ======||\n"
			+ "\t||=========  3- LISTAR CLIENTES  ==================||\n"
			+ "\t||=========  4- ALTERAR CLIENTE CADASTRADO =====||\n"
			+ "\t||=========  5- EXCLUIR CLIENTE  ==================||\n"
			+ "\t||=========  0- ENCERRAR PROGRAMA ===============||\n";

	public static void insereCliente(Scanner scan, ClienteDao clienteDao) {

		Cliente cliente = new Cliente();

		System.out.println("Digite o nome do cliente:");
		cliente.setNome(scan.next());
		System.out.println("Digite a idade do cliente:");
		cliente.setIdade(scan.nextInt());
		System.out.println("Digite o CPF do cliente:");
		cliente.setCpf(scan.next());
		System.out.println("Digite o email do cliente:");
		cliente.setEmail(scan.next());
		System.out.println("Digite o telefone do cliente:");
		cliente.setTelefone(scan.next());
		System.out.println("Digite o endereço do cliente:");
		cliente.setEndereco(scan.next());

		try {
			clienteDao.adicionaCliente(cliente);
			System.out.println("Cliente cadastrado com sucesso.");
		} catch (Exception e) {
			System.err.println("Erro ao cadastrar novo cliente\n" + e.getMessage());
		}
	}

	public static void consultaCliente(Scanner scan, ClienteDao clienteDao) {
		Cliente clienteConsultado = new Cliente();

		System.out.println("Digite o CPF do cliente:");
		String cpf = scan.next();

		try {
			clienteConsultado = clienteDao.buscaCliente(cpf);

		} catch (ClienteException e) {
			System.err.println(e.getMensagemDeErro());
		}
		System.out.println("nome: " + clienteConsultado.getNome() +  "\n"
				+ "idade: " + clienteConsultado.getIdade() + "\n"
						+ "CPF: " + clienteConsultado.getCpf() + "\n"
								+ "email: " + clienteConsultado.getEmail() + "\n"
										+ "telefone: " + clienteConsultado.getTelefone() + "\n"
												+ "endereço: " + clienteConsultado.getEndereco());
	}

	public static void listaClientes(ClienteDao clienteDao) {
		try {
			List<Cliente> clientes = clienteDao.listaClientes();
			for (Cliente cliente : clientes) {
				System.out.println("nome: " + cliente.getNome() +  "\n"
						+ "idade: " + cliente.getIdade() + "\n"
								+ "CPF: " + cliente.getCpf() + "\n"
										+ "email: " + cliente.getEmail() + "\n"
												+ "telefone: " + cliente.getTelefone() + "\n"
														+ "endereço: " + cliente.getEndereco() + "\n\n"
																+ "============================================");
			}
		} catch (ClienteException e) {
			System.err.println(e.getMensagemDeErro());
		}
		
	}
	
	public static void alteraCadastro(Scanner scan, ClienteDao clienteDao) {
		
		Cliente cliente = new Cliente();
		
		System.out.println("Digite o cpf do cliente à ser alterado:");
		String cpf = scan.next();
		
		try {
			if(clienteDao.clienteExistente(cpf)) {
				
				cliente = clienteDao.buscaCliente(cpf);
				
				System.out.println("Digite agora os dados para alteração do cadastro");
				System.out.println("Digite o nome do cliente:");
				cliente.setNome(scan.next());
				System.out.println("Digite a idade do cliente:");
				cliente.setIdade(scan.nextInt());
				System.out.println("Digite o email do cliente:");
				cliente.setEmail(scan.next());
				System.out.println("Digite o telefone do cliente:");
				cliente.setTelefone(scan.next());
				System.out.println("Digite o endereço do cliente:");
				cliente.setEndereco(scan.next());
				
				clienteDao.alteraCadastro(cpf, cliente);
				
			}
		} catch (ClienteException e) {
			System.err.println(e.getMensagemDeErro());
		}
		
		
	}
	
	public static void excluiCadastro(Scanner scan, ClienteDao clienteDao) {
		
		System.out.println("Digite o cpf do cliente à ser deletado do cadastro:");
		String cpf = scan.next();
		
		try {
			if (clienteDao.clienteExistente(cpf)) {
				clienteDao.excluirCadastro(cpf);
			}
		} catch (ClienteException e) {
			System.err.println(e.getMensagemDeErro());
		}
		
	}
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		ClienteDao clienteDao = new ClienteDao();

		String option = null;

		do {
			System.out.println(MENU_PRINCIPAL);
			System.out.println("\tDigite uma das opções do menu");
			option = scan.next();

			switch (option) {
			case "1":
				insereCliente(scan, clienteDao);
				break;

			case "2":
				consultaCliente(scan, clienteDao);
				break;

			case "3":
				listaClientes(clienteDao);
				break;

			case "4":
				alteraCadastro(scan, clienteDao);
				break;

			case "5":
				excluiCadastro(scan, clienteDao);
				break;

			case "0":

				System.out.println("Thanks!");

				break;

			default:
				System.out.println("Digite uma opção entre 1 e 5 ou 0 para terminar");

				break;
			}

		} while (!option.equals("0"));

		scan.close();
	}

}
