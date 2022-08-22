package br.com.maralto.webappbiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.maralto.webappbiblioteca.model.ControleEmprestimo;


public interface ControleEmprestimoRepository extends JpaRepository<ControleEmprestimo, Long>{
	
	@Query(value = "SELECT * from controle_emprestimos ce where ce.conemp_emp_id =:id", nativeQuery = true) 
	List<ControleEmprestimo> findByEmprestimoAtivo(@Param("id")Long id);
	
	
	@Query(value = "SELECT * from controle_emprestimos ce where ce.conemp_liv_id =:id and ce.conemp_data_entrega is null", nativeQuery = true) 
	ControleEmprestimo findControleEmprestimoByLivro(@Param("id")Long id);
}
