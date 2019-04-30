package com.nome.aula.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nome.aula.entity.ServidorEntity;

@Repository
public interface ServidorDAO extends JpaRepository<ServidorEntity, Integer> {
	
	@Query("SELECT obj FROM ServidorEntity obj WHERE obj.nome LIKE %:nome%")
	Page<ServidorEntity> search(@Param("nome") String nome, Pageable pageable);
	
	ServidorEntity findByEmail(String email);
}
