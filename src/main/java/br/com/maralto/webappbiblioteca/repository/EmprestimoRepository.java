package br.com.maralto.webappbiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.maralto.webappbiblioteca.model.Emprestimo;


public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
	
	
	@Query(value = "select * "
			+ "from public.emprestimos e "			
			+ "where e.emp_status = true and e.emp_pes_id =:idPessoa",nativeQuery = true)
	List<Emprestimo>  findEmprestimoByUsuario(@Param("idPessoa")Long idPessoa);
	

}
