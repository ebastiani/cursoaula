package com.nome.aula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nome.aula.dao.AlunoDAO;
import com.nome.aula.dao.ParecerDAO;
import com.nome.aula.dao.ServidorDAO;
import com.nome.aula.entity.AlunoEntity;
import com.nome.aula.entity.ParecerEntity;
import com.nome.aula.entity.ServidorEntity;
import com.nome.aula.exceptions.ObjNaoEncontradoException;

@Service
public class ParecerService {
	
	@Autowired
	private ParecerDAO dao;
	
	@Autowired
	private ServidorService servidorService;
	
	@Autowired
	private AlunoService alunoService;
	
	public ParecerEntity buscar(Integer id) {
		Optional<ParecerEntity> curso = dao.findById(id);
		return curso.orElseThrow(()-> new ObjNaoEncontradoException("Objeto não encontrado"));
	}
	
	public List<ParecerEntity> buscar(){
		 return dao.findAll();
	}

	public ParecerEntity salvar(ParecerEntity obj) {
		obj.setId(null);
		
		ServidorEntity servidor = servidorService.buscar(obj.getServidor().getId()); 
		
		AlunoEntity aluno = alunoService.buscar(obj.getAluno().getId()); 
		
		return dao.save(obj);
	}
	
	public Page<ParecerEntity> buscar(
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
		
		return dao.findAll(pageRequest);
	}

	public void apagar(Integer id) {
		dao.deleteById(id);		
	}
	
}
