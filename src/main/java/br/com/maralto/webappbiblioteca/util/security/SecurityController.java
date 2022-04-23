package br.com.maralto.webappbiblioteca.util.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import br.com.maralto.webappbiblioteca.model.Usuario;
import br.com.maralto.webappbiblioteca.service.UsuarioService;
import br.com.maralto.webappbiblioteca.util.jsf.FacesMessageUtils;

@Controller
@Scope("session")
public class SecurityController {

	private Usuario usuario;
	private String senha;
	private String confirmaSenha;
	@Autowired
	private AuthenticationFacade authenticationFacade;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private FacesMessageUtils facesMessageUtils;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		buscaUsuario();
	}

	public void buscaUsuario() {
		usuario = usuarioService.findByLogin(authenticationFacade.getAuthentication().getName());
	}

	public void salvarNovaSenha() {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (confirmaSenha.equals(senha)) {

			usuario.setSenha(passwordEncoder.encode(senha));
			usuarioService.save(usuario);
			facesMessageUtils.addWarningMessage("Nova senha salva com sucesso!");

		}else {
			facesMessageUtils.addWarningMessage("As senhas digitadas não são iguais!");

		}
		senha = "";
		confirmaSenha = "";
	}

}
