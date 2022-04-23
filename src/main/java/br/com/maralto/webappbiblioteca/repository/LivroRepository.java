package br.com.maralto.webappbiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.maralto.webappbiblioteca.model.Livro;


public interface LivroRepository extends JpaRepository<Livro, Long>{
	
	
	@Query(value = "SELECT * FROM PUBLIC.LIVROS l WHERE l.LIV_TITULO ILIKE %?% and LIV_CONTROLE_EMPRESTIMO_ID ISNULL ",nativeQuery = true)
	List<Livro> findLivrosByTituloAndStatus(String titulo);

}
