package com.nome.aula.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nome.aula.entity.AlunoEntity;
import com.nome.aula.entity.NecessidadeEntity;

public class NecessidadeDetalheDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	//private List<AlunoDTO> listaAlunos = new ArrayList();
	
	private List<AlunoMap> listaMapeados = new ArrayList();
	
	public NecessidadeDetalheDTO() {}
	
	public NecessidadeDetalheDTO(NecessidadeEntity obj) {
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		//this.listaAlunos = converteLista(obj.getAlunos());
		this.listaMapeados = converte(obj.getAlunos());
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


	/*
	public List<AlunoDTO> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<AlunoDTO> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}*/
	

	public List<AlunoDTO> converteLista(List<AlunoEntity> listaAlunos) {
		List<AlunoDTO> listaDTO = listaAlunos.stream().map(
				obj -> new AlunoDTO(obj)).collect(Collectors.toList()
				);
		return listaDTO;	
	}
	
	public List<AlunoMap> converte(List<AlunoEntity> listaAlunos) {
		List<AlunoMap> listaDTO = listaAlunos.stream().map(
				obj -> new AlunoMap(obj)).collect(Collectors.toList()
				);
		return listaDTO;	
	}

	public List<AlunoMap> getListaMapeados() {
		return listaMapeados;
	}

	public void setListaMapeados(List<AlunoMap> listaMapeados) {
		this.listaMapeados = listaMapeados;
	}
	
	
	
	
}

class AlunoMap{
	Integer id;
	String nome;	
	
	
	public AlunoMap(AlunoEntity obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
