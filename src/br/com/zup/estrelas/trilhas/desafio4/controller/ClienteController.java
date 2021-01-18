package br.com.zup.estrelas.trilhas.desafio4.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.zup.estrelas.trilhas.desafio4.dao.ClienteDao;
import br.com.zup.estrelas.trilhas.desafio4.exception.ClienteException;
import br.com.zup.estrelas.trilhas.desafio4.pojo.Cliente;

@WebServlet("/clientes")
public class ClienteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ClienteDao clienteDao;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cliente cliente = new Cliente();
		PrintWriter pw = response.getWriter();

		cliente.setCpf(request.getParameter("cpf"));
		cliente.setEmail(request.getParameter("email"));
		cliente.setEndereco(request.getParameter("endereco"));
		cliente.setIdade(Integer.parseInt("idade"));
		cliente.setNome(request.getParameter("nome"));
		cliente.setTelefone(request.getParameter("telefone"));

		try {
			clienteDao.adicionaCliente(cliente);
			pw.println("Cliente cadastrado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println(e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		String cpf = request.getParameter("cpf");

		if (!cpf.equals(null)) {

			try {
				Cliente cliente = clienteDao.buscaCliente(cpf);
				pw.println(cliente);

			} catch (ClienteException e) {
				e.printStackTrace();
				pw.println(e.getMensagemDeErro());
			}

		} else if (cpf.equals(null)) {

			List<Cliente> clientes;

			try {
				clientes = clienteDao.listaClientes();

				for (Cliente cliente : clientes) {
					pw.println(cliente);
				}
			} catch (ClienteException e) {
				pw.println(e.getMensagemDeErro());
			}
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cliente cliente = new Cliente();
		PrintWriter pw = response.getWriter();
		String cpf = request.getParameter("cpf");

		cliente.setCpf(request.getParameter("cpf"));
		cliente.setEmail(request.getParameter("email"));
		cliente.setEndereco(request.getParameter("endereco"));
		cliente.setIdade(Integer.parseInt("idade"));
		cliente.setNome(request.getParameter("nome"));
		cliente.setTelefone(request.getParameter("telefone"));

		try {
			if (clienteDao.clienteExistente(cpf)) {
				clienteDao.alteraCadastro(cpf, cliente);
			}
		} catch (ClienteException e) {

			pw.println(e.getMensagemDeErro());
			
		}

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		String cpf = request.getParameter("cpf");
		
		try {
			clienteDao.excluirCadastro(cpf);
			pw.println("Cadastro deletado com sucesso.");
		} catch (ClienteException e) {
			
			pw.println(e.getMensagemDeErro());
			e.printStackTrace();
		}
	}
}
