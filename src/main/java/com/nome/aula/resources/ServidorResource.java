package com.nome.aula.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nome.aula.DTO.ServidorDTO;
import com.nome.aula.entity.ServidorEntity;
import com.nome.aula.service.ServidorService;

@RestController
@RequestMapping(value="/servidores")
public class ServidorResource {
	
	@Autowired
	ServidorService service;

	@RequestMapping(method=RequestMethod.GET)
	public List<ServidorEntity> listar() {	
		List<ServidorEntity> listaServidors = service.buscar();
		return listaServidors;				
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/dto")
	public List<ServidorDTO> listardto() {	
		List<ServidorEntity> listaServidors = service.buscar();
		List<ServidorDTO> listaDTO = listaServidors.stream().map(
				obj -> new ServidorDTO(obj)).collect(Collectors.toList()
				);
		return listaDTO;				
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<ServidorEntity> buscar(@PathVariable Integer id){
		ServidorEntity curso = service.buscar(id);		
		return ResponseEntity.ok(curso);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody ServidorEntity obj){			
		obj = service.salvar(obj);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	
}
