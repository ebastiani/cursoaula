package com.nome.aula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nome.aula.dao.ServidorDAO;
import com.nome.aula.entity.CursoEntity;
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
	
	public Page<ServidorEntity> buscar(
			String nome,
			Integer pagina, 
			Integer qtdLinhas, 
			String orderBy, 
			String dir){		
		PageRequest pageRequest = PageRequest.of(
				pagina, 
				qtdLinhas, 
				Direction.valueOf(dir), 
				orderBy);
		
		return dao.search(nome, pageRequest);
	}

	public void apagar(Integer id) {
		dao.deleteById(id);		
	}
	
}