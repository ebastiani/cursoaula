package com.nome.aula.exceptions;

public class NaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NaoEncontradoException(String message) {
		super(message);
		
	}

	public NaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}


}
