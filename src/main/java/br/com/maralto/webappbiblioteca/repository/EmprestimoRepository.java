package br.com.maralto.webappbiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.maralto.webappbiblioteca.model.Emprestimo;


public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
	
	
	@Query(value = "select e "
			+ "from Emprestimo e "			
			+ "where e.status = true and e.id =:idPessoa")
	List<Emprestimo>  findEmprestimoByUsuario(@Param("idPessoa")Long idPessoa);
	
	List<Emprestimo> findByStatus(Boolean status);
	

}
