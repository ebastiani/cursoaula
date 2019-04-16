package com.nome.aula.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nome.aula.DTO.ParecerDTO;
import com.nome.aula.entity.ParecerEntity;
import com.nome.aula.service.ParecerService;

@RestController
@RequestMapping(value="/pareceres")
public class ParecerResource {
	
	@Autowired
	ParecerService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<ParecerDTO> buscar() {	
		List<ParecerEntity> listaParecers = service.buscar();
		List<ParecerDTO> listaDTO = listaParecers.stream().map(
				obj -> new ParecerDTO(obj)).collect(Collectors.toList()
				);
		return listaDTO;				
	}	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<ParecerEntity> buscar(@PathVariable Integer id){
		ParecerEntity curso = service.buscar(id);		
		return ResponseEntity.ok(curso);
	}	
	
	
	
	
}
