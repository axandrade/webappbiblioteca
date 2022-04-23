package br.com.maralto.webappbiblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.model.Autor;
import br.com.maralto.webappbiblioteca.repository.AutorRepository;



@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	AutorRepository autorRepository;
	
	@Override
	public Optional<Autor> findById(Long id) {
		return autorRepository.findById(id);
	}

	@Override
	public List<Autor> findAll() {
		return autorRepository.findAll();
	}

	@Override
	public Autor save(Autor autor) {
		return autorRepository.save(autor);
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
