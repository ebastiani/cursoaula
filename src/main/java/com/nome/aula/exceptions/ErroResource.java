package com.nome.aula.exceptions;

import java.io.Serializable;

public class ErroResource implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer statusHttp;
	private String mensagem;
	
	public ErroResource() {
		super();
	}

	public ErroResource(Integer statusHttp, String mensagem) {
		super();
		this.statusHttp = statusHttp;
		this.mensagem = mensagem;
	}
	


	public Integer getStatusHttp() {
		return statusHttp;
	}

	public void setStatusHttp(Integer statusHttp) {
		this.statusHttp = statusHttp;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	
	
	
}


