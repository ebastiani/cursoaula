package com.nome.aula.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nome.aula.entity.ServidorEntity;

public class ServidorNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;
	
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty
	private String senha;
	
	public ServidorNewDTO() {}
	
	public ServidorNewDTO(ServidorEntity obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
	}

	//getters e setters
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	
	
}
