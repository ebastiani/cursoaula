package com.nome.aula.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nome.aula.DTO.CursoDTO;
import com.nome.aula.DTO.NecessidadeDTO;
import com.nome.aula.DTO.NecessidadeDetalheDTO;
import com.nome.aula.entity.CursoEntity;
import com.nome.aula.entity.NecessidadeEntity;
import com.nome.aula.service.NecessidadeService;

@RestController
@RequestMapping(value="/necessidades")
public class NecessidadeResource {
	
	@Autowired
	NecessidadeService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<NecessidadeDTO> buscar() {	
		List<NecessidadeEntity> listaNecessidades = service.buscar();
		List<NecessidadeDTO> listaDTO = listaNecessidades.stream().map(
				obj -> new NecessidadeDTO(obj)).collect(Collectors.toList()
				);
		return listaDTO;				
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<NecessidadeDetalheDTO> buscar(@PathVariable Integer id){
		NecessidadeEntity obj = service.buscar(id);		
		NecessidadeDetalheDTO objDTO = new NecessidadeDetalheDTO(obj);		
		return ResponseEntity.ok(objDTO);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody NecessidadeDTO objDTO){
		
		NecessidadeEntity obj = new NecessidadeEntity(
				null, 
				objDTO.getDescricao()
		);
		
		obj = service.salvar(obj);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		service.apagar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/paginacao",method=RequestMethod.GET)
	public ResponseEntity<Page<NecessidadeDTO>> listarPaginas(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="pagina", defaultValue="0") Integer pagina, 
			@RequestParam(value="qtd", defaultValue="15") Integer qtdLinhas, 
			@RequestParam(value="ordem", defaultValue="nome") String orderBy, 
			@RequestParam(value="dir", defaultValue="DESC") String dir
		) 
	{	
		Page<NecessidadeEntity> listaEntity = service.buscar(nome, pagina, qtdLinhas, orderBy, dir);
		
		Page<NecessidadeDTO> listaDTO = listaEntity.map(obj -> new NecessidadeDTO(obj));			
		
		return ResponseEntity.ok().body(listaDTO);				
	}
	
	
}
