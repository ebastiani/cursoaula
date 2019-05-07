package com.nome.aula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nome.aula.dao.ServidorDAO;
import com.nome.aula.entity.ServidorEntity;
import com.nome.aula.security.UsuarioSpringSecurity;

@Service
public class UsuarioSpringSecurityService implements UserDetailsService{
	
	//Service que implementa o user específico do Spring Security

	@Autowired
	private ServidorDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		ServidorEntity servidorEntity = dao.findByEmail(email);
		
		if(servidorEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		else {
			return new UsuarioSpringSecurity(
					servidorEntity.getId(), 
					servidorEntity.getEmail(), 
					servidorEntity.getSenha(), 
					servidorEntity.getPerfis()
					);
		}
	}
	
	

}
