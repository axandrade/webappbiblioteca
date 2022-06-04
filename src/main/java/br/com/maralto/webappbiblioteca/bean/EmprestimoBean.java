package br.com.maralto.webappbiblioteca.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.maralto.webappbiblioteca.model.Autor;
import br.com.maralto.webappbiblioteca.model.ControleEmprestimo;
import br.com.maralto.webappbiblioteca.model.Emprestimo;
import br.com.maralto.webappbiblioteca.model.Livro;
import br.com.maralto.webappbiblioteca.model.Pessoa;
import br.com.maralto.webappbiblioteca.model.Usuario;
import br.com.maralto.webappbiblioteca.service.AutorService;
import br.com.maralto.webappbiblioteca.service.ControleEmprestimoService;
import br.com.maralto.webappbiblioteca.service.EmprestimoService;
import br.com.maralto.webappbiblioteca.service.LivroService;
import br.com.maralto.webappbiblioteca.service.PessoaService;
import br.com.maralto.webappbiblioteca.service.UsuarioService;
import br.com.maralto.webappbiblioteca.util.ConvertDate;
import br.com.maralto.webappbiblioteca.util.jsf.FacesMessageUtils;
import br.com.maralto.webappbiblioteca.util.security.AuthenticationFacade;

@Controller
@Scope("session")
public class EmprestimoBean {

	private Livro livro;
	private Usuario usuario;
	private String filtroCpf;
	private String filtroNome;
	private String filtroAutor;
	private String filtroTitulo;
	private Emprestimo emprestimo;
	private List<Livro> livrosList;
	private List<Pessoa> pessoasList;
	private List<Emprestimo> emprestimosList;
	private ControleEmprestimo controleEmprestimo;
	private List<ControleEmprestimo> controleEmprestimoList;
	
	@Autowired
	private LivroService livroService;

	@Autowired
	private AutorService autorService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private UsuarioService usuarioService;
		
	@Autowired
	private EmprestimoService emprestimoService;
	
	@Autowired
	private FacesMessageUtils facesMessageUtils;
	
	@Autowired
	private AuthenticationFacade authenticationFacade;
	
	@Autowired
	private ControleEmprestimoService controleEmprestimoService;
	
	@PostConstruct
	private void init() {
		this.filtroCpf = "";
		this.filtroNome = "";
		this.emprestimo = new Emprestimo();
		this.livrosList = new ArrayList<Livro>();
		this.emprestimosList = new ArrayList<Emprestimo>();
		this.controleEmprestimoList = new ArrayList<ControleEmprestimo>();
		this.usuario = usuarioService.findByLogin(authenticationFacade.getAuthentication().getName());
		
	}

	public void findAll() {
		
		emprestimosList = emprestimoService.findAll();
	}

	public void buscaPessoa() {

		if (!this.filtroCpf.equals("") && !this.filtroCpf.equals("")) {
			pessoasList = pessoaService.findByCpf(this.filtroCpf);
		} else {
			pessoasList = pessoaService.findByNome(this.filtroNome);
		}
		this.filtroCpf = "";
		this.filtroNome = "";
	}

	public void buscaLivros() {

		if (!this.filtroTitulo.equals("") || !this.filtroAutor.equals("")) {

			if (!this.filtroTitulo.equals("")) {
				this.livrosList = livroService.findLivrosByTituloAndStatus(this.filtroTitulo);
			} else {
				List<Autor> autores = autorService.findByNomeContains(this.filtroAutor);
				for (Autor a : autores) {

//					for (Livro l : a.getLivrosList()) {
//							this.livrosList.add(l);
//					}
				}
			}

		}
		
		this.filtroTitulo = "";
		this.filtroAutor = "";
	}

	public void addPessoa(Pessoa pessoa) {

		this.emprestimo.setPessoa(pessoa);

	}
	
	public void prepareControleEmprestimo() {
		
		this.filtroCpf = "";
		this.filtroNome = "";
		this.emprestimo= new Emprestimo();
		this.emprestimo.setDataEmprestimo(new Date());
		this.emprestimo.setDataDevolucao(ConvertDate.addMesAData(new Date()));
		this.livrosList = new ArrayList<Livro>();
		this.pessoasList = new ArrayList<Pessoa>();
		this.controleEmprestimo = new ControleEmprestimo();
		this.controleEmprestimo.setSituacao("DISPONIVEL");
	}

	public void addLivro(Livro livro) {
		
		boolean encontrado = false;
		this.controleEmprestimo = new ControleEmprestimo();
		

		for (ControleEmprestimo c : this.emprestimo.getControleEmprestimoList()) {
			if (c.getLivro().getId() == livro.getId()) {
				encontrado = true;
			}
		}

		if (!encontrado) {
			this.controleEmprestimo.setLivro(livro);
			this.controleEmprestimo.setDataEmprestimo(new Date());
			this.controleEmprestimo.setEmprestimo(this.emprestimo);
			this.controleEmprestimo.setUsuario(this.usuario);
			this.controleEmprestimo.setSituacao("EMPRESTADO");
			this.emprestimo.getControleEmprestimoList().add(controleEmprestimo);
		}
		
		livro = new Livro();
		this.filtroTitulo = "";
		this.filtroAutor = "";
	}

	public void removeControleEmprestimo(ControleEmprestimo controleEmprestimo) {
		this.emprestimo.getControleEmprestimoList().remove(controleEmprestimo);
	}

	
	
	public void saveEmprestimo() {
		
		this.emprestimo.setStatus(true);
		emprestimoService.save(this.emprestimo);
		reset();

	}
	
	public void toogleIsItemDevolucaoList(ControleEmprestimo controleEmprestimo) {

		if (controleEmprestimo.isItemDevolucaoList()) {
			controleEmprestimo.setItemDevolucaoList(false);
		} else {
			controleEmprestimo.setItemDevolucaoList(true);
		}
	}
	
	
	
	public void finalizarEmprestimo() {
		
		
		emprestimoService.finalizaEmprestimo(this.emprestimo);
		
	}
	
	public void showEmprestimo(Emprestimo emprestimo) {
		
		List<ControleEmprestimo> controleEmprestimoBusca = new ArrayList<ControleEmprestimo>();
		
		try {
			controleEmprestimoBusca = controleEmprestimoService.findByEmprestimoAtivo(emprestimo);
			
			this.emprestimo.setId(emprestimo.getId());
			this.emprestimo.setControleEmprestimoList(controleEmprestimoBusca);
			this.emprestimo.setPessoa(emprestimo.getPessoa());
			this.emprestimo.setObservacao(emprestimo.getObservacao());
			this.emprestimo.setDataEmprestimo(emprestimo.getDataEmprestimo());
			this.emprestimo.setDataDevolucao(emprestimo.getDataDevolucao());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void reset() {
		this.emprestimo = new Emprestimo();
		this.controleEmprestimo = new ControleEmprestimo();
	}

	public List<Pessoa> getPessoasList() {
		return pessoasList;
	}

	public void setPessoasList(List<Pessoa> pessoasList) {
		this.pessoasList = pessoasList;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public String getFiltroCpf() {
		return filtroCpf;
	}

	public void setFiltroCpf(String filtroCpf) {
		this.filtroCpf = filtroCpf;
	}

	public String getFiltroTitulo() {
		return filtroTitulo;
	}

	public void setFiltroTitulo(String filtroTitulo) {
		this.filtroTitulo = filtroTitulo;
	}

	public String getFiltroAutor() {
		return filtroAutor;
	}

	public void setFiltroAutor(String filtroAutor) {
		this.filtroAutor = filtroAutor;
	}

	public List<Livro> getLivrosList() {
		return livrosList;
	}

	public void setLivrosList(List<Livro> livrosList) {
		this.livrosList = livrosList;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public List<Emprestimo> getEmprestimosList() {
		findAll();
		return emprestimosList;
	}

	public void setEmprestimosList(List<Emprestimo> emprestimosList) {
		this.emprestimosList = emprestimosList;
	}

	public ControleEmprestimo getControleEmprestimo() {
		return controleEmprestimo;
	}

	public void setControleEmprestimo(ControleEmprestimo controleEmprestimo) {
		this.controleEmprestimo = controleEmprestimo;
	}

	public List<ControleEmprestimo> getControleEmprestimoList() {
		return controleEmprestimoList;
	}

	public void setControleEmprestimoList(List<ControleEmprestimo> controleEmprestimoList) {
		this.controleEmprestimoList = controleEmprestimoList;
	}

}
