package br.com.maralto.webappbiblioteca.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.maralto.webappbiblioteca.model.Autor;
import br.com.maralto.webappbiblioteca.service.AutorService;
import br.com.maralto.webappbiblioteca.service.LivroService;

@Controller
@Scope("session")
public class AutorBean {

	private Autor autor;
	private Autor autorSelected;
	
	private List<Autor> autoresList;

	@Autowired
	AutorService autorService;
	
	@Autowired
	LivroService livroService;
	

	@PostConstruct
	private void init() {

		autor = new Autor();
		findAll();
		
	}

	public void findAll() {		

		autoresList = autorService.findAll();

	}
	
	public void prepareSave() {
		this.autor = new Autor();
	}

	public void prepareUpdate(Autor autor) {
		this.autor = autor;
	}
	
	public void save() {

		autorService.save(autor);

		findAll();

		reset();

	}	

	private void reset() {
		autor = new Autor();
		autorSelected = new Autor();

	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Autor getAutorSelected() {
		return autorSelected;
	}

	public void setAutorSelected(Autor autorSelected) {
		this.autorSelected = autorSelected;
	}

	public List<Autor> getAutoresList() {
		return autoresList;
	}

	public void setAutoresList(List<Autor> autoresList) {
		this.autoresList = autoresList;
	}
	
//	public void export() {
//		
//		Livro livro = new Livro();
//		List<Livro> livros = new ArrayList<>();
//		Set<String> titulos = new HashSet<>();
//		
//		try {
//			
//			String fileName = "C:\\arquivos\\livros.txt";			
//			
//			BufferedReader buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));			
//			
//			String linha = "";
//			
//			while (true) {
//				if (linha != null) {
//					
//					String[] recorte = linha.split("\\|");
//					titulos.add(recorte[0]);
//					
//				} else
//					break;
//				linha = buffRead.readLine();
//			}
//			
//			for(String s : titulos) {
//				livro.setTitulo(s);
//				livro.setIdioma(Idioma.PORTUGUES.toString());
//				livro.setSituacao(Situacao.DISPONIVEL.toString());
//				livroService.save(livro);
//				
//				livro = new Livro();
//			}
//			
//			
//			
//			
//			
//			buffRead.close();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//
//	}

}
