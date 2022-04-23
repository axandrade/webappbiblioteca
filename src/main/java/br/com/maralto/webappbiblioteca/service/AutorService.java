package br.com.maralto.webappbiblioteca.service;

import java.util.List;
import java.util.Optional;

import br.com.maralto.webappbiblioteca.model.Autor;



public interface AutorService {

	Optional<Autor> findById(Long id);

	List<Autor> findAll();

	Autor save(Autor autor);

	void delete(Autor autor);
	
	List<Autor> findByNomeContains(String nome);


}
