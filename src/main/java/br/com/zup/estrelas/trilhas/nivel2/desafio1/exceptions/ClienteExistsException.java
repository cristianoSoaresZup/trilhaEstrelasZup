package br.com.zup.estrelas.trilhas.nivel2.desafio1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ClienteExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public  ClienteExistsException (String message) {
		super(message);
	}

}