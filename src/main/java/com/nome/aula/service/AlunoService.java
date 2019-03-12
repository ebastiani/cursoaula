package com.nome.aula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nome.aula.dao.AlunoDAO;
import com.nome.aula.dao.CursoDAO;
import com.nome.aula.entity.AlunoEntity;
import com.nome.aula.entity.CursoEntity;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoDAO dao;
	
	public AlunoEntity buscar(Integer id) {
		Optional<AlunoEntity> aluno = dao.findById(id);
		return aluno.orElse(null);
	}
	
	public List<AlunoEntity> buscar(){
		 return dao.findAll();
	}
	
}
