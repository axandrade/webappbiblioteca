package br.com.maralto.webappbiblioteca.service;

import java.util.List;

import br.com.maralto.webappbiblioteca.model.ControleEmprestimo;
import br.com.maralto.webappbiblioteca.model.Emprestimo;
import br.com.maralto.webappbiblioteca.model.Livro;



public interface ControleEmprestimoService {

	List<ControleEmprestimo> findAll();

	void save(ControleEmprestimo controleEmprestimo);
	
	List<ControleEmprestimo> findByEmprestimoAtivo(Emprestimo emprestimo);
	
	ControleEmprestimo findControleEmprestimoByLivro(Livro livro);

}
