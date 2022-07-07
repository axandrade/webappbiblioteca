package br.com.maralto.webappbiblioteca.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.model.Usuario;
import br.com.maralto.webappbiblioteca.repository.UsuarioRepository;
import br.com.maralto.webappbiblioteca.util.jsf.FacesMessageUtils;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private FacesMessageUtils facesMessageUtils;

	@Override
	public Usuario findByLogin(String login) {
		return this.repository.findByLogin(login);
	}

	@Override
	public void save(Usuario usuario) {
	
		try {

			this.repository.save(usuario);
		}catch (JpaSystemException e) {
			facesMessageUtils.addErrorMessage("Nome do Autor já Existe");
			e.printStackTrace();
		}catch (Exception e) {

			e.printStackTrace();
		}
		
		
	}
	
	
	private void validaUsuario(Usuario usuario) {
		
		

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
