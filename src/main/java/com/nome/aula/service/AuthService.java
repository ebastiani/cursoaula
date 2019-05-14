package com.nome.aula.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nome.aula.dao.ServidorDAO;
import com.nome.aula.entity.ServidorEntity;
import com.nome.aula.exceptions.ObjNaoEncontradoException;

@Service
public class AuthService {

	@Autowired
	ServidorDAO dao;

	@Autowired
	BCryptPasswordEncoder bpe;
	
	@Autowired
	EmailService emailService;

	private Random random = new Random();

	public void enviarSenha(String email) {
		ServidorEntity entity = dao.findByEmail(email);
		if (entity == null) 
			throw new ObjNaoEncontradoException("Email não encontrado");

			String novaSenha = novaSenha();

			entity.setSenha(bpe.encode(novaSenha));

			dao.save(entity);
			
			emailService.enviarSenha(entity, novaSenha);
			

	}

	private String novaSenha() {
		char[] vet = new char[10];
		
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		// https://unicode-table.com/pt/
		int opt = random.nextInt(3);
		if (opt == 0) {
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) {
			return (char) (random.nextInt(26) + 65);
		} else{
			return (char) (random.nextInt(26) + 97);
		}
		
	}

}