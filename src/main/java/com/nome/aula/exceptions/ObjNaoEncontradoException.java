package com.nome.aula.exceptions;

public class ObjNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ObjNaoEncontradoException(String message) {
		super(message);		
	}

}
