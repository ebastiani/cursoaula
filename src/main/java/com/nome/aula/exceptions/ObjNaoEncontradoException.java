package com.nome.aula.exceptions;

import net.bytebuddy.implementation.SuperMethodCall;

public class ObjNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ObjNaoEncontradoException(String message) {
		super(message);		
	}
	
	
}
