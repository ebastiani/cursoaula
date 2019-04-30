package com.nome.aula.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroResource> validation(
			MethodArgumentNotValidException e, HttpServletRequest request)
	{
		ErroValidacao erros = new ErroValidacao(HttpStatus.BAD_REQUEST.value(), 
				"Erro de validação");
		
		for(FieldError x: e.getBindingResult().getFieldErrors()) {
			erros.addErro(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
	}
	
	@ExceptionHandler(IntegridadeException.class)
	public ResponseEntity<ErroResource> erroIntegridade(
			IntegridadeException e, HttpServletRequest request)
	{
		ErroResource erro = new ErroResource(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	
	@ExceptionHandler(AutorizacaoException.class)
	public ResponseEntity<ErroResource> authorization(
			AutorizacaoException e, HttpServletRequest request)
	{
		ErroResource erro = new ErroResource(HttpStatus.FORBIDDEN.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}
	

}
