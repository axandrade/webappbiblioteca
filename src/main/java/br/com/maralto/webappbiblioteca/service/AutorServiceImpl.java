package br.com.maralto.webappbiblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.model.Autor;
import br.com.maralto.webappbiblioteca.repository.AutorRepository;
import br.com.maralto.webappbiblioteca.util.jsf.FacesMessageUtils;



@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	AutorRepository autorRepository;
	
	@Autowired
	private FacesMessageUtils facesMessageUtils;
	
	@Override
	public Optional<Autor> findById(Long id) {
		return autorRepository.findById(id);
	}

	@Override
	public List<Autor> findAll() {
		return autorRepository.findAll();
		
		
	}

	@Override
	public void save(Autor autor) {

		try {

			autorRepository.save(autor);
		} catch (JpaSystemException e) {
			facesMessageUtils.addErrorMessage("Nome do Autor '" + autor.getNome() + "' já Existe");
			e.printStackTrace();
		}		
		catch (Exception e) {			
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Autor autor) {
		autorRepository.delete(autor);
	}

	@Override
	public List<Autor> findByNomeContains(String nome) {
		return autorRepository.findByNomeContainsIgnoreCase(nome);
	}
	


}
