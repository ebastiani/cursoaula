package com.nome.aula.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	/*@RequestMapping(method=RequestMethod.GET)
	public List<CursoEntity> listar() {	
		List<CursoEntity> listaCursos = service.buscar();
		return listaCursos;				
	}*/
	
	@RequestMapping(method=RequestMethod.GET)
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
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody CursoEntity obj, @PathVariable Integer id){		
		obj.setId(id);
		obj = service.atualizar(obj);			
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/paginacao",method=RequestMethod.GET)
	public ResponseEntity<Page<CursoDTO>> listarPaginas(
			@RequestParam(value="pagina", defaultValue="0") Integer pagina, 
			@RequestParam(value="qtd", defaultValue="15") Integer qtdLinhas, 
			@RequestParam(value="ordem", defaultValue="nome") String orderBy, 
			@RequestParam(value="dir", defaultValue="DESC") String dir
		) 
	{	
		Page<CursoEntity> listaCursos = service.buscarPorPagina(pagina, qtdLinhas, orderBy, dir);
		
		Page<CursoDTO> listaDTO = listaCursos.map(obj -> new CursoDTO(obj));			
		
		return ResponseEntity.ok().body(listaDTO);				
	}	
	
	
}
