package com.nome.aula.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ParecerEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Lob
	private String observacao;
	
	private String anexo;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="servidor_id")
	private ServidorEntity servidor;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="aluno_id")
	private AlunoEntity aluno;
	

	
	public ParecerEntity() {
		super();
	}

			
	
	public ParecerEntity(Integer id, String observacao, String anexo, Date data, ServidorEntity servidor,
			AlunoEntity aluno) {
		super();
		this.id = id;
		this.observacao = observacao;
		this.anexo = anexo;
		this.data = data;
		this.servidor = servidor;
		this.aluno = aluno;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
	public String getObservacao() {
		return observacao;
	}



	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}



	public String getAnexo() {
		return anexo;
	}



	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}



	public Date getData() {
		return data;
	}



	public void setData(Date data) {
		this.data = data;
	}



	public ServidorEntity getServidor() {
		return servidor;
	}



	public void setServidor(ServidorEntity servidor) {
		this.servidor = servidor;
	}



	public AlunoEntity getAluno() {
		return aluno;
	}



	public void setAluno(AlunoEntity aluno) {
		this.aluno = aluno;
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
		ParecerEntity other = (ParecerEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
	
	
}
