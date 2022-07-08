package br.com.maralto.webappbiblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.model.Autorizacao;
import br.com.maralto.webappbiblioteca.repository.AutorizacaoRepository;

@Service
public class AutorizacaoServiceImpl implements AutorizacaoService {
	
	@Autowired
	private AutorizacaoRepository repository;

	@Override
	public List<Autorizacao> findAll() {
		return repository.findAll();
	}
	

}
