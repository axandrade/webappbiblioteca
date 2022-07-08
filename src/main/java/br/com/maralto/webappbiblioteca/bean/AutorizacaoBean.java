package br.com.maralto.webappbiblioteca.bean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.maralto.webappbiblioteca.model.Usuario;
import br.com.maralto.webappbiblioteca.service.UsuarioService;
import br.com.maralto.webappbiblioteca.util.security.AuthenticationFacade;

@Controller
@Scope("session")
public class AutorizacaoBean {

	private Usuario usuario;
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AuthenticationFacade authenticationFacade;
	
	@PostConstruct
	private void init() {
		
		this.usuario = usuarioService.findByLogin(authenticationFacade.getAuthentication().getName());
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	

}
