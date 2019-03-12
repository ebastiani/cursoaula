package com.nome.aula.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TurmaEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	private	Integer serieSemestre;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="curso_id")
	private CursoEntity curso;
	
	@ManyToMany(mappedBy = "turmas")
	private List<AlunoEntity> alunos = new ArrayList<>();
		
	public TurmaEntity() {
		super();
	}

	public TurmaEntity(Integer id, String descricao, Integer serieSemestre, CursoEntity curso) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.serieSemestre = serieSemestre;
		this.curso = curso;
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

	public Integer getSerieSemestre() {
		return serieSemestre;
	}

	public void setSerieSemestre(Integer serieSemestre) {
		this.serieSemestre = serieSemestre;
	}

	public CursoEntity getCurso() {
		return curso;
	}

	public void setCurso(CursoEntity curso) {
		this.curso = curso;
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
		TurmaEntity other = (TurmaEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
