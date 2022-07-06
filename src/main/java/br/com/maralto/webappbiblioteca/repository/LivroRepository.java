package br.com.maralto.webappbiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.maralto.webappbiblioteca.model.Livro;


public interface LivroRepository extends JpaRepository<Livro, Long>{
	
	
	@Query(value = "select distinct l.liv_id, l.liv_titulo, l.liv_data_cadastro, l.liv_data_publicacao, l.liv_idioma, l.liv_isbn, l.liv_situacao "
			+ "from public.livros l "			
			+ "where l.liv_titulo ilike %?%  and l.liv_id not in (select ce.conemp_liv_id from controle_emprestimos ce where ce.conemp_data_entrega is null)", nativeQuery = true)
	List<Livro> findLivrosByTituloAndStatus(String titulo);

}
