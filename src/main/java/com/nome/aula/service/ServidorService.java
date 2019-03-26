package com.nome.aula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nome.aula.dao.ServidorDAO;
import com.nome.aula.entity.ServidorEntity;
import com.nome.aula.exceptions.ObjNaoEncontradoException;

@Service
public class ServidorService {
	
	@Autowired
	private ServidorDAO dao;
	
	public ServidorEntity buscar(Integer id) {
		Optional<ServidorEntity> curso = dao.findById(id);
		return curso.orElseThrow(()-> new ObjNaoEncontradoException("Objeto não encontrado"));
	}
	
	public List<ServidorEntity> buscar(){
		 return dao.findAll();
	}

	public ServidorEntity salvar(ServidorEntity obj) {
		obj.setId(null);
		return dao.save(obj);
	}
	
}
