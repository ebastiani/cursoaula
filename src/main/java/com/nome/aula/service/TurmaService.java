package com.nome.aula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nome.aula.dao.CursoDAO;
import com.nome.aula.dao.TurmaDao;
import com.nome.aula.entity.CursoEntity;
import com.nome.aula.entity.TurmaEntity;

@Service
public class TurmaService {
	
	@Autowired
	private TurmaDao dao;
	
	public TurmaEntity buscar(Integer id) {
		Optional<TurmaEntity> objeto = dao.findById(id);
		return objeto.orElse(null);
	}
	
	public List<TurmaEntity> buscar(){
		 return dao.findAll();
	}
	
}
