package com.nome.aula.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/cursos")
public class CursoResource {

	@RequestMapping(method=RequestMethod.GET)
	public String listar() {
		return "lista de cursos";
	}	
}
