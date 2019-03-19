package com.nome.aula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nome.aula.dao.CursoDAO;
import com.nome.aula.entity.CursoEntity;
import com.nome.aula.exceptions.NaoEncontradoException;

import javassist.NotFoundException;

@Service
public class CursoService {
	
	@Autowired
	private CursoDAO dao;
	
	public CursoEntity buscar(Integer id) {
		Optional<CursoEntity> curso = dao.findById(id);
		return curso.orElseThrow(()-> new NaoEncontradoException("Objeto não encontrado"));
	}
	
	public List<CursoEntity> buscar(){
		 return dao.findAll();
	}
	
}
