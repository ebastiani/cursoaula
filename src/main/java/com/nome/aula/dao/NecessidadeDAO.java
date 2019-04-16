package com.nome.aula.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nome.aula.entity.NecessidadeEntity;
import com.nome.aula.entity.ServidorEntity;

@Repository
public interface NecessidadeDAO extends JpaRepository<NecessidadeEntity, Integer> {
	
	@Query("SELECT obj FROM NecessidadeEntity obj WHERE obj.descricao LIKE %:descricao%")
	Page<NecessidadeEntity> search(@Param("descricao") String descricao, Pageable pageable);
}
