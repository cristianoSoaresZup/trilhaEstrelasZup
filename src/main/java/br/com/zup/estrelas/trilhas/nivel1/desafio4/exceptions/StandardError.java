package br.com.zup.estrelas.trilhas.nivel1.desafio4.exceptions;

import java.io.Serializable;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Instant timestamp;
	private Integer status;
	private String message;
	private String path;
	private String exception;
	private String error;
	
	public StandardError () {
	}

}