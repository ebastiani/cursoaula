package com.nome.aula.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.nome.aula.security.UsuarioSpringSecurity;

public class UserService {
	
	public static UsuarioSpringSecurity autenticado() {
		try {
			return (UsuarioSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			 return null;
		}
	}

}
