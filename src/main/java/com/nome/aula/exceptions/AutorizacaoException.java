package com.nome.aula.exceptions;

public class AutorizacaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AutorizacaoException(String message) {
		super(message);		
	}

}
