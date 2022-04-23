package br.com.maralto.webappbiblioteca.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.model.ControleEmprestimo;
import br.com.maralto.webappbiblioteca.model.Emprestimo;
import br.com.maralto.webappbiblioteca.repository.ControleEmprestimoRepository;
import br.com.maralto.webappbiblioteca.repository.EmprestimoRepository;
import br.com.maralto.webappbiblioteca.repository.LivroRepository;



@Service
public class EmprestimoServiceImpl implements EmprestimoService {

	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	@Autowired
	ControleEmprestimoRepository controleEmprestimoRepository;
	

	@Override
	public List<Emprestimo> findAll() {
		return emprestimoRepository.findAll();
	}

	@Override
	public void save(Emprestimo emprestimo) {
		

		if (emprestimo.getId() == null) {
			emprestimo.setStatus(true);

		}else {
			
			boolean finalizaEmprestimo = false;
			
			atualizaDadosControleEmprestimo(emprestimo);
			
			for (ControleEmprestimo controleEmprestimo : emprestimo.getControleEmprestimoList()) {

				if (!controleEmprestimo.isItemDevolucaoList()) {

					finalizaEmprestimo = true;
				}
			}
			
			emprestimo.setStatus(finalizaEmprestimo);
		}
		
		
		
		emprestimoRepository.save(emprestimo);
		
		// sendEmail(emprestimo);
	}

	private void atualizaDadosControleEmprestimo(Emprestimo emprestimo) {
		for (ControleEmprestimo controleEmprestimo : emprestimo.getControleEmprestimoList()) {
			
				if (controleEmprestimo.isItemDevolucaoList()) {
					controleEmprestimo.setDataEntrega(new Date());
					controleEmprestimo.setSituacao("DISPONIVEL");
				}

			controleEmprestimoRepository.save(controleEmprestimo);
		}
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
		
//		for(Livro l : emprestimo.getLivrosList()) {
//			email.append("Titulo: " + l.getTitulo() + "\n");
//		}
		
		if(emprestimo.getObservacao() != null && !emprestimo.getObservacao().equals(""))
			email.append("Observação: " + emprestimo.getObservacao() + "\n\n");
		
		email.append("Data do Empréstimo: " + emprestimo.getDataEmprestimoFormatado() + "\n");
		//email.append("Data para Devolução: " + emprestimo.getDataDevolucaoFormatado() + "\n");
		//email.append("Administrador: " + emprestimo.getUsuario().getNome() + "\n");
		email.append("Endereço: Rua Antônio Lima, nº 150, 200, Bairro Meireles, em Fortaleza-CE, 60115-270 \n");
		email.append("O atraso na devolução acarreta multa de 0,50 ( cinquenta centavos) por dia. \n\n");
		email.append("Centro Cultural Mar Alto");


		return email.toString();
	}

	
	


}
