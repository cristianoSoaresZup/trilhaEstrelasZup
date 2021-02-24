package br.com.zup.estrelas.trilhas.nivel2.desafio1.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClienteExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> clienteNaoEncontrado(ResourceNotFoundException e, HttpServletRequest request) {
		StandardError erro = this.setStandardError(e, request);
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setError("Resource not found.");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

	}

	@ExceptionHandler(ClienteExistsException.class)
	public ResponseEntity<StandardError> cpfJaCadastrado(ClienteExistsException e, HttpServletRequest request) {
		StandardError erro = this.setStandardError(e, request);
		erro.setStatus(HttpStatus.CONFLICT.value());
		erro.setError("Duplicate Keys");

		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> campoInvalido(MethodArgumentNotValidException e, HttpServletRequest request) {
		StandardError erro = this.setStandardError(e, request);
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setError("Invalid fields.");
		erro.setMessage("O CPF informado não é válido.");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	private StandardError setStandardError(Exception e, HttpServletRequest request) {
		
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setPath(request.getRequestURI());
		erro.setMessage(e.getMessage());
		erro.setException(e.toString());
		return erro;
	}
}