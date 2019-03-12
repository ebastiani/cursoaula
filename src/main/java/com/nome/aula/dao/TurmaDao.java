package com.nome.aula.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nome.aula.entity.TurmaEntity;

@Repository
public interface TurmaDao extends JpaRepository<TurmaEntity, Integer> {
	
}
