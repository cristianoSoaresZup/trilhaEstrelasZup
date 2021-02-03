package br.com.zup.estrelas.trilhas.nivel1.desafio2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String mensagemDeErro;

	public ResourceNotFoundException(String mensagemDeErro) {
		super(mensagemDeErro);
		this.mensagemDeErro = mensagemDeErro;
	}

	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}

}
