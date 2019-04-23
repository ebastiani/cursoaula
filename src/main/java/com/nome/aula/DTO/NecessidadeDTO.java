package com.nome.aula.DTO;

import java.io.Serializable;

import com.nome.aula.entity.NecessidadeEntity;

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
