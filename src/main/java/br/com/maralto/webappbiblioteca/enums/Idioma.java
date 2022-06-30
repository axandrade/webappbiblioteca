package br.com.maralto.webappbiblioteca.enums;

public enum Idioma {

	PORTUGUES("PORTUGUÊS"), 	
	INGLES("INGLÊS"),
	ESPANHOL("ESPANHOL"),
	ALEMAO("ALEMÃO");
	
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
