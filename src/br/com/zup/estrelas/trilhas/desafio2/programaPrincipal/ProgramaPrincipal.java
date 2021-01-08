package br.com.zup.estrelas.trilhas.desafio2.programaPrincipal;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.com.zup.estrelas.trilhas.desafio2.pojo.Cliente;

public class ProgramaPrincipal {

	private static final String MENU_PRINCIPAL = "\n\t||============ CADASTRO DE CLIENTES ============||\n"
			+ "\t||                                                             ||\n"
			+ "\t||= = = = = = = = = = = = = = = = = = = = = = = = = = =||\n"
			+ "\t||                                                             ||\n"
			+ "\t||=========  1- CADASTRAR NOVO CLIENTE =========||\n"
			+ "\t||=========  2- CONSULTAR CLIENTE PELO CPF ======||\n"
			+ "\t||=========  3- LISTAR CLIENTES  =================||\n"
			+ "\t||=========  4- ALTERAR CLIENTE CADASTRADO =====||\n"
			+ "\t||=========  5- EXCLUIR CLIENTE  =================||\n"
			+ "\t||=========  0- ENCERRAR PROGRAMA ==============||\n\n";

	public static void novoCadastro() {
		System.out.println("Digite o nome do Cliente:");
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		Cliente cliente = new Cliente();

		Map<String, Cliente> Clientes = new HashMap<>();

		String option = null;

		do {
			System.out.println(MENU_PRINCIPAL);
			System.out.println("Digite uma das opções do menu");
			option = scan.next();

			switch (option) {
			case "1":
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

				Clientes.put(cliente.getCpf(), cliente);
				System.out.println("Cliente cadastrado com sucesso");

				break;

			case "2":
				System.out.println("Digite o CPF do cliente:");
				String cpf = scan.next();
				
				Cliente clientePesquisado = new Cliente();
				clientePesquisado = Clientes.get(cpf);
				System.out.print("\n nome: " + clientePesquisado.getNome() + "\n idade: " + clientePesquisado.getIdade() 
				+ "\n email: "+clientePesquisado.getEmail() + "\n telefone: " + clientePesquisado.getTelefone()
				+ "\n endereço: " + clientePesquisado.getEndereco() + "\n\n");

				break;

			case "3":

				break;

			case "4":

				break;
				
			case "5":

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
