package com.nome.aula.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nome.aula.DTO.CursoDTO;
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
	
	@RequestMapping(method=RequestMethod.GET, value="/dto")
	public List<CursoDTO> listardto() {	
		List<CursoEntity> listaCursos = service.buscar();
		List<CursoDTO> listaDTO = listaCursos.stream().map(obj -> new CursoDTO(obj)).collect(Collectors.toList());
		return listaDTO;				
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<CursoEntity> buscar(@PathVariable Integer id){
		CursoEntity curso = service.buscar(id);		
		return ResponseEntity.ok(curso);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody CursoEntity obj){		
		obj = service.salvar(obj);
		//Boa prática -> Salvar e fornecer a uri com o novo objeto como resposta.
		// Restorna a URL com o novo ID.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
