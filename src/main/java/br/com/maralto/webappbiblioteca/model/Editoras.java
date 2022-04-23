package br.com.maralto.webappbiblioteca.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.maralto.webappbiblioteca.util.model.PersistentEntity;
@Entity
@Table(name = "EDITORAS")
@AttributeOverride(name = "id", column = @Column(name = "EDIT_ID"))
public class Editoras extends PersistentEntity {

	private static final long serialVersionUID = 1L;
	@Column(name="EDIT_NOME")
	
	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	


}
