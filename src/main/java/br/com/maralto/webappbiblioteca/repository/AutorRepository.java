package br.com.maralto.webappbiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maralto.webappbiblioteca.model.Autor;


public interface AutorRepository extends JpaRepository<Autor, Long>{
	
	
	
	List<Autor> findByNomeContainsIgnoreCase(String nome);


}
