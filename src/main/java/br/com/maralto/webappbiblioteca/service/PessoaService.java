package br.com.maralto.webappbiblioteca.service;

import java.util.List;
import java.util.Optional;

import br.com.maralto.webappbiblioteca.model.Pessoa;

public interface PessoaService {
	
	List<Pessoa> findAll();
	
	void save(Pessoa pessoa);
	
	List<Pessoa> findByNome(String nome);
	
	List<Pessoa> findByCpf(String cpf);
	
	Optional<Pessoa> findById(Long id);


}
