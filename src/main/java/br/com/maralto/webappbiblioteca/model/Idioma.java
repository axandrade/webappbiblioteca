package br.com.maralto.webappbiblioteca.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.maralto.webappbiblioteca.util.model.PersistentEntity;

@Entity
@Table(name = "IDIOMAS")
@AttributeOverride(name = "id", column = @Column(name = "IDIOMA_ID"))
public class Idioma extends PersistentEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "IDIOMA_DESCRICAO")
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
