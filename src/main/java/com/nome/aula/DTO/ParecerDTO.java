package com.nome.aula.DTO;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nome.aula.entity.ParecerEntity;

public class ParecerDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data;
	
		
	public ParecerDTO() {}
	
	public ParecerDTO(ParecerEntity obj) {
		this.id = obj.getId();
		this.data = obj.getData();
		
	}

	//getters e setters
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	
		
	
}
