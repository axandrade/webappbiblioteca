package br.com.maralto.webappbiblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.model.Idioma;
import br.com.maralto.webappbiblioteca.repository.IdiomaRepository;



@Service
public class IdiomaServiceImpl implements IdiomaService {

	@Autowired
	IdiomaRepository idiomaRepository;

	@Override
	public List<Idioma> findAll() {
		return idiomaRepository.findAll();
	}
	
	
	


}
