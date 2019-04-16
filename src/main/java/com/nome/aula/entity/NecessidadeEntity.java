package com.nome.aula.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import org.apache.tomcat.util.security.MD5Encoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class NecessidadeEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String descricao;
		
	
	@ManyToMany(mappedBy = "necessidades")
	private List<AlunoEntity> alunos = new ArrayList<>();
		
	public NecessidadeEntity() {
		super();
	}

	public NecessidadeEntity(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;		
	}
	
	

	public NecessidadeEntity(Integer id, String descricao, String observacao) {
		super();
		this.id = id;
		this.descricao = descricao;		
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
	
	

	public List<AlunoEntity> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<AlunoEntity> alunos) {
		this.alunos = alunos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NecessidadeEntity other = (NecessidadeEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	

}