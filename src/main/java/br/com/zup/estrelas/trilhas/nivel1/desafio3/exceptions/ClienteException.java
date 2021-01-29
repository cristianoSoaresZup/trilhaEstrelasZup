package br.com.zup.estrelas.trilhas.nivel1.desafio3.exceptions;

public class ClienteException extends Exception {
	
private static final long serialVersionUID = 1L;
	
	private String mensagemDeErro;
	
	public ClienteException(String mensagemDeErro) {
		this.mensagemDeErro  = mensagemDeErro;
	}
	
	public String getMensagemDeErro() {
		return mensagemDeErro;
	}
	
	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}

}
