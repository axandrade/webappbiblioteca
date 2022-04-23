package br.com.maralto.webappbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maralto.webappbiblioteca.model.Emprestimo;


public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
	

}
