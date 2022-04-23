package br.com.maralto.webappbiblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.model.ControleEmprestimo;
import br.com.maralto.webappbiblioteca.model.Emprestimo;
import br.com.maralto.webappbiblioteca.repository.ControleEmprestimoRepository;
import br.com.maralto.webappbiblioteca.repository.EmprestimoRepository;
import br.com.maralto.webappbiblioteca.repository.LivroRepository;



@Service
public class ControleEmprestimoServiceImpl implements ControleEmprestimoService {
	
	@Autowired
	LivroRepository livroRepository;
			
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	@Autowired
	ControleEmprestimoRepository controleEmprestimoRepository;

	@Override
	public List<ControleEmprestimo> findAll() {
		return null;
	}

	@Override
	public void save(ControleEmprestimo controleEmprestimo) {
		
	}

	@Override
	public List<ControleEmprestimo> findByEmprestimoAtivo(Emprestimo emprestimo) {
		return controleEmprestimoRepository.findByEmprestimoAtivo(emprestimo.getId());
	}

	

}
