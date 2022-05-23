package br.com.maralto.webappbiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.maralto.webappbiblioteca.model.Livro;


public interface LivroRepository extends JpaRepository<Livro, Long>{
	
	
	@Query(value = "select * from public.livros l "
			+ "left join controle_emprestimos ce on ce.conemp_liv_id = l.liv_id "
			+ "where l.liv_titulo ilike %?%  and ce.conemp_situacao <> 'EMPRESTADO'",nativeQuery = true)
	List<Livro> findLivrosByTituloAndStatus(String titulo);

}
