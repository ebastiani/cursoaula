package com.nome.aula.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nome.aula.entity.CursoEntity;

@RestController
@RequestMapping(value="/cursos")
public class CursoResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<CursoEntity> listar() {
		CursoEntity c1 = new CursoEntity(1, "Sistemas para Internet", "Superior", "noturno");
		CursoEntity c2 = new CursoEntity(2, "Processos Químicos", "Superior", "diurno");
		CursoEntity c3 = new CursoEntity(3, "Biologia", "Superior", "diurno");
		
		List<CursoEntity> listaCursos = new ArrayList<>();
		listaCursos.add(c1);
		listaCursos.add(c2);
		listaCursos.add(c3);
		
		return listaCursos;		
		
	}	
}
