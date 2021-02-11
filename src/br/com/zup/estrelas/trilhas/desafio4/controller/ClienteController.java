package br.com.zup.estrelas.trilhas.desafio4.controller;

import java.io.BufferedReader;
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
import br.com.zup.estrelas.trilhas.desafio4.jsonSerializer.ClienteSerializer;
import br.com.zup.estrelas.trilhas.desafio4.pojo.Cliente;

@WebServlet("/clientes")
public class ClienteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ClienteDao clienteDao = new ClienteDao();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader reader = request.getReader();
		ClienteSerializer clienteSerializer = new ClienteSerializer();
		PrintWriter pw = response.getWriter();
		Cliente cliente = clienteSerializer.jsonToCliente(reader);

		try {
			clienteDao.adicionaCliente(cliente);
			pw.println("Cliente cadastrado com sucesso.");
		} catch (ClienteException e) {
			e.printStackTrace();
			pw.println(e.getMensagemDeErro());
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		String cpf = request.getParameter("cpf");
		
		if (cpf != null) {
			try {
				Cliente cliente = clienteDao.buscaCliente(cpf);
				pw.println(cliente);
			} catch (ClienteException e) {
				e.printStackTrace();
				pw.println(e.getMensagemDeErro());
			}

		} else if (cpf == null) {

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

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BufferedReader reader = request.getReader();
		ClienteSerializer clienteSerializer = new ClienteSerializer();
		PrintWriter pw = response.getWriter();
		Cliente cliente = clienteSerializer.jsonToCliente(reader);

		try {
			if (clienteDao.clienteExistente(cliente.getCpf())) {
				clienteDao.alteraCadastro(cliente);
			}
		} catch (ClienteException e) {
			pw.println(e.getMensagemDeErro());
		}
		pw.println("Cliente de cpf: " + cliente.getCpf() +" alterado com sucesso.");
	}

	@Override
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
