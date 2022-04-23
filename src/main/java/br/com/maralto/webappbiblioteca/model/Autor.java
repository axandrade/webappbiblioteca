package br.com.maralto.webappbiblioteca.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.maralto.webappbiblioteca.util.model.PersistentEntity;
@Entity
@Table(name = "AUTORES")
@AttributeOverride(name = "id", column = @Column(name = "AUT_ID"))
public class Autor extends PersistentEntity {

	private static final long serialVersionUID = 1L;
	@Column(name="AUT_NOME")
	private String nome;

	
	
	public Autor() {
	}

	public Autor(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
}
