package br.com.maralto.webappbiblioteca.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.maralto.webappbiblioteca.util.model.PersistentEntity;

@Entity
@Table(name = "LIVROS")
@AttributeOverride(name = "id", column = @Column(name = "LIV_ID"))
public class Livro extends PersistentEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "LIV_ISBN")
	private String isbn;
	@Column(name = "LIV_TITULO")
	private String titulo;
	@OneToOne
	@JoinColumn(name = "LIV_IDIOMA_ID", referencedColumnName = "IDIOMA_ID")
	private Idioma idioma = new Idioma();
	@Column(name = "LIV_DATA_CADASTRO")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	@Column(name = "LIV_DATA_PUBLICACAO")
	@Temporal(TemporalType.DATE)
	private Date dataPublicacao;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "AUTORES_LIVROS", joinColumns = { @JoinColumn(name = "LIV_ID") }, inverseJoinColumns = {@JoinColumn(name = "AUT_ID")})
	List<Autor> autoresList = new ArrayList<Autor>();
		
	public Livro() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public List<Autor> getAutoresList() {
		return autoresList;
	}

	public void setAutoresList(List<Autor> autoresList) {
		this.autoresList = autoresList;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public String getListAutores() {
		String delimitador = ", ";
		List<String> nomesAutores = new ArrayList<String>();
		
		for(Autor a : this.autoresList) {
			nomesAutores.add(a.getNome());
		}
		
		return String.join(delimitador, nomesAutores);
		
		
	}

	

}
