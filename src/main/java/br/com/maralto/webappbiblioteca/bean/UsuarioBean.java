package br.com.maralto.webappbiblioteca.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.maralto.webappbiblioteca.model.Autorizacao;
import br.com.maralto.webappbiblioteca.model.Usuario;
import br.com.maralto.webappbiblioteca.service.AutorizacaoService;
import br.com.maralto.webappbiblioteca.service.UsuarioService;

@Controller
@Scope("session")
public class UsuarioBean {

	private Usuario usuario;
	private String confirmaSenha;
	private List<Usuario> usuarioList;
	private Autorizacao autorizacaoSelected;
	private List<Autorizacao> autorizacaoList;
	@Autowired
	private UsuarioService usuarioService;	
	@Autowired
	private AutorizacaoService autorizacaoService;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuariosList() {
		return usuarioList;
	}

	public void setUsuariosList(List<Usuario> usuariosList) {
		this.usuarioList = usuariosList;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public List<Autorizacao> getAutorizacaoList() {
		return autorizacaoList;
	}

	public void setAutorizacaoList(List<Autorizacao> autorizacaoList) {
		this.autorizacaoList = autorizacaoList;
	}

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		autorizacaoSelected = new Autorizacao();
		findAll();
		autorizacaoList = autorizacaoService.findAll();
		
	}

	public void reset() {
		confirmaSenha = "";
		this.usuario = new Usuario();
	}
	
	public void findAll() {
		usuarioList = usuarioService.findAll();

	}
	
	public void prepareSave() {
		
		this.usuario = new Usuario();
		this.usuario.setDesejaResetarSenha(true);
		this.autorizacaoSelected = new Autorizacao();
	}
	
	public void prepareUpdate(Usuario usuario) {
		
		this.usuario = usuario;
		this.usuario.setDesejaResetarSenha(false);
		List<Autorizacao> autorizacaoListConvert = new ArrayList<>(usuario.getAutorizacaoList());
		
		setAutorizacaoSelected(autorizacaoListConvert.get(0));
		
	}

	public void save() {

		Set<Autorizacao> autorizacoesSelectedList = new HashSet<>();

		autorizacoesSelectedList.add(this.autorizacaoSelected);
		this.usuario.setAutorizacaoList(autorizacoesSelectedList);

		usuarioService.save(usuario, this.confirmaSenha);

		findAll();
		reset();

	}

	public void removerUsuario() {
		usuarioService.remove(usuario);
	}

	public void resetarSenha(Usuario usuario) {
		usuario.setSenha("hope");
	}

	public Autorizacao getAutorizacaoSelected() {
		return autorizacaoSelected;
	}

	public void setAutorizacaoSelected(Autorizacao autorizacaoSelected) {
		this.autorizacaoSelected = autorizacaoSelected;
	}

	

}
