package br.com.maralto.webappbiblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.model.Pessoa;
import br.com.maralto.webappbiblioteca.repository.PessoaRepository;


@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	@Override
	public void save(Pessoa pessoa) {
		
		

		pessoaRepository.save(pessoa);

	}

	@Override
	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
	}

	@Override
	public List<Pessoa> findByNome(String nome) {
		return pessoaRepository.findByNomeContainsIgnoreCase(nome);
	}

	@Override
	public List<Pessoa> findByCpf(String cpf) {
		return pessoaRepository.findByCpf(cpf);
	}


}
