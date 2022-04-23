package br.com.maralto.webappbiblioteca.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.model.Livro;
import br.com.maralto.webappbiblioteca.repository.LivroRepository;


@Service
public class LivroServiceImpl implements LivroService {
	
	@Autowired
	LivroRepository livroRepository;

	@Override
	public Optional<Livro> findById(Long id) {
		return livroRepository.findById(id);
	}

	@Override
	public List<Livro> findAll() {
		return livroRepository.findAll();
	}

	@Override
	public Livro save(Livro livro) {
		livro.setDataCadastro(new Date());
		return livroRepository.saveAndFlush(livro);
	}

	@Override
	public void delete(Livro livro) {
		livroRepository.delete(livro);
		
	}
	
	@Override
	public List<Livro> findLivrosByTituloAndStatus(String titulo) {
		
		return livroRepository.findLivrosByTituloAndStatus(titulo);
	}

}
