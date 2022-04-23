package br.com.maralto.webappbiblioteca.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import br.com.maralto.webappbiblioteca.model.Autorizacao;
import br.com.maralto.webappbiblioteca.model.Usuario;
import br.com.maralto.webappbiblioteca.service.AutorizacaoService;
import br.com.maralto.webappbiblioteca.service.UsuarioService;
import br.com.maralto.webappbiblioteca.util.jsf.FacesMessageUtils;

@Controller
@Scope("session")
public class UsuarioBean {

	private Usuario usuario;
	private String confirmaSenha;
	private List<Usuario> usuarioList;
	private Autorizacao autorizacaoSelected;
	private List<Autorizacao> autorizacaoList;
	private Boolean desejaResetarSenha;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private FacesMessageUtils facesMessageUtils;
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
		desejaResetarSenha = false;
	}

	public void reset() {
		confirmaSenha = "";
		this.usuario = new Usuario();
	}
	
	public void findAll() {
		usuarioList = usuarioService.findAll();

	}
	
	public void prepareSave() {
		desejaResetarSenha = true;
		this.usuario = new Usuario();
	}
	
	public void prepareUpdate(Usuario usuario) {
		desejaResetarSenha = false;
		this.autorizacaoSelected = usuario.getAutorizacaoList().get(0);
		this.usuario = usuario;
		
	}

	public void save() {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		List<Autorizacao> autorizacoesSelectedList = new ArrayList<Autorizacao>();

		if (validaUsuario(this.usuario)) {

			if(confirmaSenha != null && !confirmaSenha.equals(""))
			usuario.setSenha(passwordEncoder.encode(confirmaSenha));

			autorizacoesSelectedList.add(this.autorizacaoSelected);
			this.usuario.setAutorizacaoList(autorizacoesSelectedList);

			usuarioService.save(usuario);

			findAll();
			reset();
			facesMessageUtils.addInfoMessage("Salvo com Sucesso!");

		} 

		reset();
	}
	
	private boolean validaUsuario(Usuario usuario) {
		
		if (usuarioService.findByLogin(usuario.getLogin()) == null) {
			
			if (usuario.getId() == null) {
				
				if (!this.confirmaSenha.equals("") && !usuario.getSenha().equals("")) {
					
					if (this.usuario.getSenha().equals(confirmaSenha)) {
						
						return true;
					}else {
						facesMessageUtils.addErrorMessage("Voce digitou uma senha diferente na confirmação");
						return false;
					}
					
				}else {
					facesMessageUtils.addErrorMessage("O campo Senha e a confirmação da senha são obrigatórios");
					return false;
				}
			} else {
				if (desejaResetarSenha) {

					if (this.usuario.getSenha().equals(confirmaSenha)) {

						return true;
					} else {
						facesMessageUtils.addErrorMessage("Voce digitou uma senha diferente na confirmação");
						return false;
					}

				}else {
					return true;
				}
				
			}

		} else {
			facesMessageUtils.addErrorMessage("Login " + usuario.getLogin() + " ja Existe");
			return false;
		}

	}

	public void editar() {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (confirmaSenha.equals("")) {

			usuario.setSenha(passwordEncoder.encode(""));
		//	autorizacaoList.add(autorizacao);
			usuario.setAutorizacaoList(autorizacaoList);

			usuarioService.save(usuario);
			facesMessageUtils.addWarningMessage("Alterado com Sucesso!");
		} else {
			facesMessageUtils.addWarningMessage("As senhas digitadas não são iguais!");
		}

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

	public Boolean getDesejaResetarSenha() {
		return desejaResetarSenha;
	}

	public void setDesejaResetarSenha(Boolean desejaResetarSenha) {
		this.desejaResetarSenha = desejaResetarSenha;
	}

}
