package com.nome.aula.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nome.aula.entity.CursoEntity;
import com.nome.aula.service.CursoService;

@RestController
@RequestMapping(value="/cursos")
public class CursoResource {
	
	@Autowired
	CursoService service;

	@RequestMapping(method=RequestMethod.GET)
	public List<CursoEntity> listar() {	
		List<CursoEntity> listaCursos = service.buscar();
		return listaCursos;				
	}	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<CursoEntity> buscar(@PathVariable Integer id){
		CursoEntity curso = service.buscar(id);
		if(curso == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(curso);
	}	
}
