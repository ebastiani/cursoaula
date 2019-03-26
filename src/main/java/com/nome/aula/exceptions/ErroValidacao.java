package com.nome.aula.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroResource {
	private static final long serialVersionUID = 1L;
	
	private List<MensagemErroCampoException> listaErros = new ArrayList<>();
	
	public ErroValidacao(Integer statusHttp, String mensagem) {
		super(statusHttp, mensagem);		
	}

	public List<MensagemErroCampoException> getListaErros() {
		return listaErros;
	}

	public void addErro(String campo, String msg) {
		MensagemErroCampoException m = new MensagemErroCampoException(campo, msg);
		this.listaErros.add(m);
	}
	
}
