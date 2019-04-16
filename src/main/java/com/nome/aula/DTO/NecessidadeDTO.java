package com.nome.aula.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nome.aula.entity.AlunoEntity;
import com.nome.aula.entity.NecessidadeEntity;
import com.nome.aula.entity.ServidorEntity;

public class NecessidadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;	
			
	public NecessidadeDTO() {}
	
	public NecessidadeDTO(NecessidadeEntity obj) {
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		
	}
	
	public NecessidadeDTO(NecessidadeEntity obj, boolean detalhe) {
		this.id = obj.getId();
		this.descricao = obj.getDescricao();		
	
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

	
}
