package com.nome.aula.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nome.aula.DTO.ParecerDTO;
import com.nome.aula.DTO.ServidorDTO;
import com.nome.aula.entity.AlunoEntity;
import com.nome.aula.entity.ParecerEntity;
import com.nome.aula.entity.ServidorEntity;
import com.nome.aula.service.AlunoService;
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
		ParecerEntity obj = service.buscar(id);		
		return ResponseEntity.ok(obj);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody ParecerDTO objDTO){			
		
		ServidorEntity servidor = new ServidorEntity();
		AlunoEntity aluno = new AlunoEntity();		
		servidor.setId(objDTO.getServidor());		
		aluno.setId(objDTO.getAluno());		
		
		ParecerEntity obj = new ParecerEntity(null, objDTO.getObservacao(), null, objDTO.getData(), servidor, aluno);
	
		obj = service.salvar(obj);	
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	};
}
