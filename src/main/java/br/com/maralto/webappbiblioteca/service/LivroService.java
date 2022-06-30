package br.com.maralto.webappbiblioteca.service;

import java.util.List;
import java.util.Optional;

import br.com.maralto.webappbiblioteca.model.Livro;


public interface LivroService {

	Optional<Livro> findById(Long id);

	List<Livro> findAll();

	void save(Livro livro);

	void delete(Livro livro);
	
	List<Livro> findLivrosByTituloAndStatus(String titulo);


}
