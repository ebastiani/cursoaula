package com.nome.aula.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nome.aula.entity.AlunoEntity;

@Repository
public interface AlunoDAO extends JpaRepository<AlunoEntity, Integer> {
	
	@Query("SELECT obj FROM AlunoEntity obj WHERE obj.nome LIKE %:nome%")
	Page<AlunoEntity> search(@Param("nome") String nome, Pageable pageable);
}
