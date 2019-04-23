package com.nome.aula.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AlunoEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String telefone;
	private String matricula;
	private String email;

	@ManyToMany
	@JoinTable(name = "matriculas", 
		joinColumns = @JoinColumn(name = "aluno_id"),
		inverseJoinColumns = @JoinColumn (name = "turma_id")
	)
	private List<TurmaEntity> turmas = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "necessidades_alunos", 
		joinColumns = @JoinColumn(name = "aluno_id"),
		inverseJoinColumns = @JoinColumn (name = "necessidade_id")
	)
	private List<NecessidadeEntity> necessidades = new ArrayList<>();
	
	
	@JsonBackReference
	@OneToMany(mappedBy="aluno")
	private List<ParecerEntity> pareceres = new ArrayList<>();

	public AlunoEntity() {
		super();
	}

	public AlunoEntity(Integer id, String nome, String telefone, String matricula, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.matricula = matricula;
		this.email = email;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<TurmaEntity> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<TurmaEntity> turmas) {
		this.turmas = turmas;
	}
	
	

	
	public List<NecessidadeEntity> getNecessidades() {
		return necessidades;
	}

	public void setNecessidades(List<NecessidadeEntity> necessidades) {
		this.necessidades = necessidades;
	}
	
	
	

	public List<ParecerEntity> getPareceres() {
		return pareceres;
	}

	public void setPareceres(List<ParecerEntity> pareceres) {
		this.pareceres = pareceres;
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
		AlunoEntity other = (AlunoEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
	
	
}
