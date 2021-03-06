package br.com.maralto.webappbiblioteca.service;

import java.util.List;

import br.com.maralto.webappbiblioteca.model.Usuario;

public interface UsuarioService {
	
	Usuario findByLogin(String login);

	void save(Usuario usuario, String confirmaSenha);
	
	void remove(Usuario usuario);
	
	List<Usuario> findAll();
	
}
