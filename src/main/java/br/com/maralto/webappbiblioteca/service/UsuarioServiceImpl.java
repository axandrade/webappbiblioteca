package br.com.maralto.webappbiblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.model.Usuario;
import br.com.maralto.webappbiblioteca.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario findByLogin(String login) {
		return this.repository.findByLogin(login);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return this.repository.save(usuario);
	}

	@Override
	public void remove(Usuario usuario) {
		this.repository.delete(usuario);
	}

	@Override
	public List<Usuario> findAll() {
		return repository.findAll();
	}



	
	

}
