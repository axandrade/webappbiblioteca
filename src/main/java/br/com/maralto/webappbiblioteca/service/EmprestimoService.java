package br.com.maralto.webappbiblioteca.service;

import java.util.List;

import br.com.maralto.webappbiblioteca.exception.GenericException;
import br.com.maralto.webappbiblioteca.model.Emprestimo;



public interface EmprestimoService {	

	void save(Emprestimo emprestimo) throws GenericException;

	void finalizaEmprestimo(Emprestimo emprestimo);
	
	List<Emprestimo> findEmprestimoByStatus(Boolean status);


}
