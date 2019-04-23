package com.nome.aula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nome.aula.dao.AlunoDAO;
import com.nome.aula.dao.ServidorDAO;
import com.nome.aula.entity.CursoEntity;
import com.nome.aula.entity.AlunoEntity;
import com.nome.aula.exceptions.IntegridadeException;
import com.nome.aula.exceptions.ObjNaoEncontradoException;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoDAO dao;
	
	public AlunoEntity buscar(Integer id) {
		Optional<AlunoEntity> curso = dao.findById(id);
		return curso.orElseThrow(()-> new ObjNaoEncontradoException("Objeto não encontrado"));
	}
	
	public List<AlunoEntity> buscar(){
		 return dao.findAll();
	}

	public AlunoEntity salvar(AlunoEntity obj) {
		obj.setId(null);
		return dao.save(obj);
	}
	
	public Page<AlunoEntity> buscar(
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
		try {		
			dao.deleteById(id);	
		}
		catch (DataIntegrityViolationException e) {
			 throw new IntegridadeException("Não é possível excluir");
		}
	}
	
}
