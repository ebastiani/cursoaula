package com.nome.aula.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nome.aula.entity.CursoEntity;
import com.nome.aula.entity.TurmaEntity;
import com.nome.aula.service.CursoService;
import com.nome.aula.service.TurmaService;

@RestController
@RequestMapping(value="/turmas")
public class TurmaResource {
	
	@Autowired
	TurmaService service;

	@RequestMapping(method=RequestMethod.GET)
	public List<TurmaEntity> listar() {
	
		List<TurmaEntity> lista = service.buscar();
		return lista;		
		
	}	
	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<TurmaEntity> buscar(@PathVariable Integer id){
		TurmaEntity objeto = service.buscar(id);
		return ResponseEntity.ok(objeto);
	}
	
}
