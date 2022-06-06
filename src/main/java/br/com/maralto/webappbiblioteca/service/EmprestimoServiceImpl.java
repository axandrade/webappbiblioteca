package br.com.maralto.webappbiblioteca.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.model.ControleEmprestimo;
import br.com.maralto.webappbiblioteca.model.Emprestimo;
import br.com.maralto.webappbiblioteca.model.Livro;
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
	public List<Emprestimo> findAll() {
		return emprestimoRepository.findAll();
	}

	@Override
	public void save(Emprestimo emprestimo) {

		if (validaDadosEmprestimo(emprestimo) && verificaHitoricoEmprestimos(emprestimo)) {

			emprestimoRepository.save(emprestimo);
			// sendEmail(emprestimo);
		}

	}
	
	private boolean verificaHitoricoEmprestimos(Emprestimo emprestimo) {

		boolean isLivroDevolvido = false;
		Integer qtdControleEmprestimosDisponiveis;

		List<Emprestimo> emprestimosBusca = emprestimoRepository.findEmprestimoByUsuario(emprestimo.getPessoa().getId());

		if (!emprestimosBusca.isEmpty()) {

			qtdControleEmprestimosDisponiveis = verificaQtdControleEmprestimosAtivos(emprestimosBusca.get(0).getControleEmprestimoList());

			if (emprestimosBusca.size() == 1 && emprestimo.getControleEmprestimoList().size() > qtdControleEmprestimosDisponiveis) {

				facesMessageUtils.addErrorMessage("Esse usuário ja possui o limite máximo de empréstimos ativos, faça a devolução de um dos livros para poder realizar um novo empréstimo");
				return false;

			} else {
				for (ControleEmprestimo ce : emprestimosBusca.get(0).getControleEmprestimoList()) {
					if (ce.getDataEntrega() != null) {
						isLivroDevolvido = true;
					}
				}

				if (isLivroDevolvido) {
					return true;
				} else {
					facesMessageUtils.addErrorMessage("Esse usuário ja possui o limite máximo de empréstimos ativos, faça a devolução de um dos livros para poder realizar um novo empréstimo");
					return false;
				}

			}

		}
		
		else {
			
			if (!emprestimo.getControleEmprestimoList().isEmpty()) {
				return true;
			} else {
				facesMessageUtils.addErrorMessage("Nenhum livro foi selecionado para o empréstimo");
				return false;
			}	
		}

	}
	
	private Integer verificaQtdControleEmprestimosAtivos(List<ControleEmprestimo> controleEmprestimoList) {
		
		for(ControleEmprestimo ce: controleEmprestimoList) {
			if(ce.getDataEntrega() != null) {
				return 1;
			}else {
				return 2;
			}
		}
		
		return null;
	}

	public boolean validaDadosEmprestimo(Emprestimo emprestimo) {

		if (emprestimo.getPessoa() != null && emprestimo.getPessoa().getCpf() != null && !emprestimo.getPessoa().getCpf().equals("")) {
			
			if (!emprestimo.getControleEmprestimoList().isEmpty()) {
				return true;
			} else {
				facesMessageUtils.addErrorMessage("Nenhum livro foi selecionado para o empréstimo");
				return false;
			}	
		} else {
			facesMessageUtils.addErrorMessage("O Campo CPF é Obrigatório");
			return false;
		}

	}



	private void atualizaDadosControleEmprestimo(Emprestimo emprestimo) {
		for (ControleEmprestimo controleEmprestimo : emprestimo.getControleEmprestimoList()) {
			
				if (controleEmprestimo.isItemDevolucaoList()) {
					controleEmprestimo.setDataEntrega(new Date());
					controleEmprestimo.setSituacao("DISPONIVEL");
				}
				
				System.out.println(controleEmprestimo.getLivro().getTitulo() + "-" + controleEmprestimo.getSituacao());

			controleEmprestimoRepository.save(controleEmprestimo);
		}
	}
	
	public void finalizaEmprestimo(Emprestimo emprestimo) {
		
		boolean statusEmprestimo = false;
		
		atualizaDadosControleEmprestimo(emprestimo);
		
		for (ControleEmprestimo controleEmprestimo : emprestimo.getControleEmprestimoList()) {

			if (controleEmprestimo.getSituacao().equals("EMPRESTADO")) {

				statusEmprestimo = true;
			}
		}
		
		emprestimo.setStatus(statusEmprestimo);
		
		if(validaDadosfinalizacaoEmprestimo(emprestimo)) {
			emprestimoRepository.save(emprestimo);
			
			if(statusEmprestimo) {
				System.out.println("O emprestimo ainda esta ativo");
			}else {
				System.out.println("Emprestimo encerrado");
			}
			
		}
		
	}
	
	private boolean validaDadosfinalizacaoEmprestimo(Emprestimo emprestimo) {
		
		boolean isListaDesmarcada = true;
		
		for (ControleEmprestimo controleEmprestimo : emprestimo.getControleEmprestimoList()) {
			if(controleEmprestimo.isItemDevolucaoList()) {
				isListaDesmarcada = false;
			}
		}
		
		if(isListaDesmarcada) {
			facesMessageUtils.addErrorMessage("Para Finalizar um empréstimo você deve selecionar no mínimo um livro");
			return false;
		}
		
		return true;
	}

	private void sendEmail(Emprestimo emprestimo) {
		
		String from = "maralto.biblioteca@gmail.com";
		String senha = "maralto2022";
		String to = emprestimo.getPessoa().getEmail();
		
		
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(from, senha));
		email.setSSLOnConnect(true);
		
		try {
			email.setFrom(from);
			email.setSubject("Emprestimo de Livro - Maralto");
			email.setMsg(getMsgEmail(emprestimo));
			email.addTo(to);
			email.send();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private String getMsgEmail(Emprestimo emprestimo) {
		
		StringBuilder email = new StringBuilder();
		
		email.append("Prezado(a), " + emprestimo.getPessoa().getNome() + "\n");
		email.append("Segue as informações do seu empréstimo: \n\n");
		
		email.append("Livro(s):\n\n");
		
		for(ControleEmprestimo ce : emprestimo.getControleEmprestimoList()) {
			email.append("Titulo: " + ce.getLivro().getTitulo() + "\n");
		}
		
		if(emprestimo.getObservacao() != null && !emprestimo.getObservacao().equals(""))
			email.append("Observação: " + emprestimo.getObservacao() + "\n\n");
		
		email.append("Data do Empréstimo: " + emprestimo.getDataEmprestimoFormatado() + "\n");
		email.append("Data para Devolução: " + emprestimo.getDataDevolucaoFormatado() + "\n");		
		email.append("Endereço: Rua Antônio Lima, nº 150, 200, Bairro Meireles, em Fortaleza-CE, 60115-270 \n");
		email.append("O atraso na devolução acarreta multa de 0,50 ( cinquenta centavos) por dia. \n\n");
		email.append("Centro Cultural Mar Alto");


		return email.toString();
	}

	
	


}
