package br.com.maralto.webappbiblioteca.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.exception.GenericException;
import br.com.maralto.webappbiblioteca.model.ControleEmprestimo;
import br.com.maralto.webappbiblioteca.model.Emprestimo;
import br.com.maralto.webappbiblioteca.repository.ControleEmprestimoRepository;
import br.com.maralto.webappbiblioteca.repository.EmprestimoRepository;
import br.com.maralto.webappbiblioteca.repository.LivroRepository;
import br.com.maralto.webappbiblioteca.util.jsf.FacesMessageUtils;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {

	@Autowired
	LivroRepository livroRepository;

	@Autowired
	EmprestimoRepository emprestimoRepository;

	@Autowired
	private FacesMessageUtils facesMessageUtils;

	@Autowired
	ControleEmprestimoRepository controleEmprestimoRepository;

	@Override
	public List<Emprestimo> findEmprestimoByStatus(Boolean status) {

		if (status == null) {
			return emprestimoRepository.findAll();
		} else {
			return emprestimoRepository.findByStatus(status);
		}
	}
	
	@Override
	public void save(Emprestimo emprestimo) throws GenericException {

		try {
			
			validaDadosEmprestimo(emprestimo);

			verificaHistoricoEmprestimos(emprestimo);

			emprestimoRepository.save(emprestimo);

			sendEmail(emprestimo);
			
		} catch (EmailException e) {
			e.getMessage();
		}		
		catch (Exception e) {
			e.getMessage();
		}
		
		

	}

	private void verificaHistoricoEmprestimos(Emprestimo emprestimo) throws GenericException {

		List<Emprestimo> emprestimosBusca = emprestimoRepository
				.findEmprestimoByUsuario(emprestimo.getPessoa().getId());

		if (!emprestimosBusca.isEmpty()) {

			if (emprestimosBusca.size() == 2
					|| emprestimo.getControleEmprestimoList().size() > verificaQtdControleEmprestimosAtivos(
							emprestimosBusca.get(0).getControleEmprestimoList())) {
				throw new GenericException(
						"Esse usuário ja possui o limite máximo de empréstimos ativos, faça a devolução de um dos livros para poder realizar um novo empréstimo");
			}

		}
	}

	private Integer verificaQtdControleEmprestimosAtivos(List<ControleEmprestimo> controleEmprestimoList) {

		Integer qtdControleEmprestimosDisponiveis = 0;

		if (controleEmprestimoList.size() == 1) {
			qtdControleEmprestimosDisponiveis = 1;
		} else {
			for (ControleEmprestimo ce : controleEmprestimoList) {
				if (ce.getDataEntrega() != null) {
					qtdControleEmprestimosDisponiveis++;
				}
			}
		}

		return qtdControleEmprestimosDisponiveis;
	}

	public void validaDadosEmprestimo(Emprestimo emprestimo) throws GenericException {

		if (emprestimo.getPessoa() == null && emprestimo.getPessoa().getCpf() == null
				&& emprestimo.getPessoa().getCpf().equals("")) {
			throw new GenericException("Voce deve selecionar uma pessoa que irá receber o empréstimo");
		}

		if (emprestimo.getControleEmprestimoList().isEmpty()) {
			throw new GenericException("Nenhum livro foi selecionado para o empréstimo");
		}

		for (ControleEmprestimo controleEmprestimo : emprestimo.getControleEmprestimoList()) {

			if (controleEmprestimo.getLivro().getAutoresList().isEmpty())
				throw new GenericException(
						"O livro não possui Autores, va ate a tela de cadastro de livros e atualize as informações");
		}

	}

	private void atualizaDadosControleEmprestimo(Emprestimo emprestimo) {
		for (ControleEmprestimo controleEmprestimo : emprestimo.getControleEmprestimoList()) {

			if (controleEmprestimo.isItemDevolucaoList()) {
				controleEmprestimo.setDataEntrega(new Date());
			}

			controleEmprestimoRepository.save(controleEmprestimo);
		}
	}

	public void finalizaEmprestimo(Emprestimo emprestimo) {

		boolean statusEmprestimo = false;

		atualizaDadosControleEmprestimo(emprestimo);

		for (ControleEmprestimo controleEmprestimo : emprestimo.getControleEmprestimoList()) {

			if (controleEmprestimo.getDataEntrega() == null) {

				statusEmprestimo = true;
			}
		}

		emprestimo.setStatus(statusEmprestimo);

		if (validaDadosfinalizacaoEmprestimo(emprestimo)) {
			emprestimoRepository.save(emprestimo);
		}

	}

	private boolean validaDadosfinalizacaoEmprestimo(Emprestimo emprestimo) {

		boolean isListaDesmarcada = true;

		for (ControleEmprestimo controleEmprestimo : emprestimo.getControleEmprestimoList()) {
			if (controleEmprestimo.isItemDevolucaoList()) {
				isListaDesmarcada = false;
			}
		}

		if (isListaDesmarcada) {
			facesMessageUtils.addErrorMessage("Para Finalizar um empréstimo você deve selecionar no mínimo um livro");
			return false;
		}

		return true;
	}

	private void sendEmail(Emprestimo emprestimo) throws EmailException {
		// ldrpafuvviwufzdg
		// maralto2022
		String from = "maralto.biblioteca@gmail.com";
		String senha = "ldrpafuvviwufzdg";
		String to = emprestimo.getPessoa().getEmail();

		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(from, senha));
		email.setSSLOnConnect(true);

		email.setFrom(from);
		email.setSubject("Emprestimo de Livro - Maralto");
		email.setMsg(getMsgEmail(emprestimo));
		email.addTo(to);
		email.send();

	}

	private String getMsgEmail(Emprestimo emprestimo) {

		StringBuilder email = new StringBuilder();

		email.append("Prezado(a), " + emprestimo.getPessoa().getNome() + "\n");
		email.append("Segue as informações do seu empréstimo: \n\n");

		email.append("Livro(s):\n\n");

		for (ControleEmprestimo ce : emprestimo.getControleEmprestimoList()) {
			email.append("Titulo: " + ce.getLivro().getTitulo() + "\n");
		}

		if (emprestimo.getObservacao() != null && !emprestimo.getObservacao().equals(""))
			email.append("Observação: " + emprestimo.getObservacao() + "\n\n");

		email.append("Data do Empréstimo: " + emprestimo.getDataEmprestimoFormatado() + "\n");
		email.append("Data para Devolução: " + emprestimo.getDataDevolucaoFormatado() + "\n");
		email.append("Endereço: Rua Antônio Lima, nº 150, 200, Bairro Meireles, em Fortaleza-CE, 60115-270 \n");
		email.append("O atraso na devolução acarreta multa de 0,50 ( cinquenta centavos) por dia. \n\n");
		email.append("Centro Cultural Mar Alto");

		return email.toString();
	}

}
