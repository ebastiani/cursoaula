package com.nome.aula.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nome.aula.entity.AlunoEntity;
import com.nome.aula.entity.CursoEntity;

@Repository
public interface AlunoDAO extends JpaRepository<AlunoEntity, Integer> {
	
}
