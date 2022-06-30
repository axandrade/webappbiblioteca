package br.com.maralto.webappbiblioteca.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.maralto.webappbiblioteca.enums.Idioma;
import br.com.maralto.webappbiblioteca.model.Autor;
import br.com.maralto.webappbiblioteca.model.Livro;
import br.com.maralto.webappbiblioteca.service.AutorService;
import br.com.maralto.webappbiblioteca.service.LivroService;
import br.com.maralto.webappbiblioteca.util.ConvertDate;
import br.com.maralto.webappbiblioteca.util.jsf.FacesMessageUtils;

@Controller
@Scope("session")
public class LivroBean {

	private Livro livro;
	private Livro livroSelected;
	private String dataPublicacao;
	private List<Livro> livrosList;
	private List<Autor> autoresList;
	private String idiomaSelected;
	private String autorNomeFiltro;
	List<Idioma> idiomasList;

	@Autowired
	LivroService livroService;
	@Autowired
	AutorService autorService;
	

	@Autowired
	private FacesMessageUtils facesMessageUtils;

	@PostConstruct
	private void init() {

		livro = new Livro();
		dataPublicacao = "";		
		autorNomeFiltro = "";		
		
		findAllLivros();
		
		idiomasList = Arrays.asList(Idioma.values());
	}

	public void save() {

		if (dataPublicacao != null && !dataPublicacao.equals("")) {
			this.livro.setDataPublicacao(ConvertDate.formataData(dataPublicacao));
		}
		
		
		try {
			
			livroService.save(livro);
			
			findAllLivros();

			reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prepareSave() {
		this.dataPublicacao = "";		
		this.livro = new Livro();
	}
	
	public void prepareUpdate(Livro livro) {
		this.setDataPublicacao(ConvertDate.DateToString(livro.getDataPublicacao()));
		
		this.livro = livro;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Livro> getLivrosList() {
		return livrosList;
	}

	public void setLivrosList(List<Livro> livrosList) {
		this.livrosList = livrosList;
	}

	public Livro getLivroSelected() {
		return livroSelected;
	}

	public void setLivroSelected(Livro livroSelected) {
		this.livroSelected = livroSelected;
	}

	public void findAllLivros() {

		livrosList = livroService.findAll();
		
		
	}

	public void findAutoresByName() {
		autoresList = autorService.findByNomeContains(this.autorNomeFiltro);
		
	}

	public void update() {

		livroService.save(livroSelected);
		facesMessageUtils.addWarningMessage("Alterado com Sucesso");
		findAllLivros();

	}

	private void reset() {
		this.livro = new Livro();
		this.livroSelected = new Livro();
		this.dataPublicacao = "";
		this.autoresList = new ArrayList<Autor>();
		

	}
	
	public void addAutor(Autor autor) {

		boolean encontrado = false;

		for (Autor a : this.livro.getAutoresList()) {
			if (a.getId() == autor.getId()) {
				encontrado = true;
			}
		}

		if (!encontrado) {
			this.livro.getAutoresList().add(autor);
		}

		autor = new Autor();
		this.autorNomeFiltro = "";
		this.autoresList = new ArrayList<Autor>();
	}
	
	public void removeAutor(Autor autor) {
		
		this.livro.getAutoresList().remove(autor);
	}

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	

	public List<Autor> getAutoresList() {
		return autoresList;
	}

	public void setAutoresList(List<Autor> autoresList) {
		this.autoresList = autoresList;
	}


	public String getAutorNomeFiltro() {
		return autorNomeFiltro;
	}

	public void setAutorNomeFiltro(String autorNomeFiltro) {
		this.autorNomeFiltro = autorNomeFiltro;
	}

	public List<Idioma> getIdiomasList() {
		return idiomasList;
	}

	public void setIdiomasList(List<Idioma> idiomasList) {
		this.idiomasList = idiomasList;
	}

	public String getIdiomaSelected() {
		return idiomaSelected;
	}

	public void setIdiomaSelected(String idiomaSelected) {
		this.idiomaSelected = idiomaSelected;
	}

	

}
