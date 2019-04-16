package com.nome.aula;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nome.aula.dao.AlunoDAO;
import com.nome.aula.dao.CursoDAO;
import com.nome.aula.dao.NecessidadeDAO;
import com.nome.aula.dao.ServidorDAO;
import com.nome.aula.dao.TurmaDao;
import com.nome.aula.entity.AlunoEntity;
import com.nome.aula.entity.CursoEntity;
import com.nome.aula.entity.NecessidadeEntity;
import com.nome.aula.entity.ServidorEntity;
import com.nome.aula.entity.TurmaEntity;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
	private CursoDAO cursoDAO;
	
	@Autowired
	private TurmaDao turmaDAO;
	
	@Autowired
	private ServidorDAO servidorDAO;
	
	
	@Autowired
	private NecessidadeDAO necessidadeDAO;
	
	
	@Autowired
	private AlunoDAO alunoDAO;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CursoEntity curso1 = new CursoEntity(null, "Sistemas para Internet", "Superior", "noturno");
		CursoEntity curso2 = new CursoEntity(null, "Processos Químicos", "Superior", "diurno");
		
		TurmaEntity turma1 = new TurmaEntity(null, "Turma formandos", 5, curso1);
		TurmaEntity turma2 = new TurmaEntity(null, "Turma dois", 3, curso1);
		TurmaEntity turma3 = new TurmaEntity(null, "Turma inaugural", 1, curso2);
		
		curso1.getTurmas().addAll(Arrays.asList(turma1, turma2));
		curso2.getTurmas().addAll(Arrays.asList(turma3));
		
		ServidorEntity servidor = new ServidorEntity(null,  "Joao", "joao@gmail.com", "123");
		
		
		//16/04/2019
		NecessidadeEntity necessidadeEntity = new NecessidadeEntity(null, "Deficiência intelectual");
		
		
		AlunoEntity alunoEntity = new AlunoEntity(null, "Aluno", "5596596568", "20190001", "aluno@gmail.com");
		
		alunoEntity.getTurmas().add(turma1);
		
		alunoEntity.getNecessidades().add(necessidadeEntity);
		
		//16/04/2019
		cursoDAO.saveAll(Arrays.asList(curso1, curso2));	
		turmaDAO.saveAll(Arrays.asList(turma1, turma2, turma3));	
		
		necessidadeDAO.save(necessidadeEntity);
		alunoDAO.save(alunoEntity);
		
		servidorDAO.save(servidor);
		
		
		
		
		
	}

}
