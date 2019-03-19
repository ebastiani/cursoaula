package com.nome.aula.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;


@ControllerAdvice
public class ErroHandler {
	
	@ExceptionHandler(ObjNaoEncontradoException.class)
	public ResponseEntity<ErroResource> objectNotFound(
			ObjNaoEncontradoException e, HttpServletRequest request)
	{
		ErroResource erro = new ErroResource(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
