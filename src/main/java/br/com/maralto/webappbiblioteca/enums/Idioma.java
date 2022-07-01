package br.com.maralto.webappbiblioteca.enums;

public enum Idioma {

	PORTUGUES("Português"), 	
	INGLES("Inglês"),
	ESPANHOL("Espanhol"),
	ALEMAO("Alemão"),
	FRANCES("Francês"),
	ITALIANO("Italiano"),
	RUSSO("Russo"),	
	JAPONES("Japonês");
	
	String descricao = null;

	Idioma(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
