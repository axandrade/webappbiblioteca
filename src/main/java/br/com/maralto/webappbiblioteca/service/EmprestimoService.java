package br.com.maralto.webappbiblioteca.service;

import java.util.List;

import br.com.maralto.webappbiblioteca.model.Emprestimo;



public interface EmprestimoService {

	List<Emprestimo> findAll();

	void save(Emprestimo emprestimo);

	void finalizaEmprestimo(Emprestimo emprestimo);
	


}
