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
	
	private String observacao; 
	
	private Integer servidor;
	
	private Integer aluno;
		
	public ParecerDTO() {}
	
	public ParecerDTO(ParecerEntity obj) {
		this.id = obj.getId();
		this.data = obj.getData();
		this.observacao = obj.getObservacao();
		this.servidor = obj.getServidor().getId();
		this.aluno = obj.getAluno().getId();
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getServidor() {
		return servidor;
	}

	public void setServidor(Integer servidor) {
		this.servidor = servidor;
	}

	public Integer getAluno() {
		return aluno;
	}

	public void setAluno(Integer aluno) {
		this.aluno = aluno;
	}

	
		
	
}
