package br.com.maralto.webappbiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maralto.webappbiblioteca.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	
		
	List<Pessoa> findByCpf(String cpf);
	
	List<Pessoa> findByNomeContainsIgnoreCase(String nome);
	
}
