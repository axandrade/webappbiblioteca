package br.com.maralto.webappbiblioteca.enums;

public enum Situacao {

	DISPONIVEL("Disponivel"), 	
	EMPRESTADO("Emprestado"),
	PERDIDO("Perdido"),
	AVARIADO("Avariado");
	
	String descricao = null;

	Situacao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
