package com.nome.aula.exceptions;

import java.io.Serializable;

public class MensagemErroCampoException implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String campo;
	private String msg;
	
	public MensagemErroCampoException() {
		super();
	}

	public MensagemErroCampoException(String campo, String msg) {
		super();
		this.campo = campo;
		this.msg = msg;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
