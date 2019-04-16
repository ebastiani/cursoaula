package com.nome.aula.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nome.aula.entity.ParecerEntity;

@Repository
public interface ParecerDAO extends JpaRepository<ParecerEntity, Integer> {	
	
}
