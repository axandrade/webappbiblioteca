package br.com.maralto.webappbiblioteca.bean;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.maralto.webappbiblioteca.model.Pessoa;
import br.com.maralto.webappbiblioteca.service.PessoaService;
import br.com.maralto.webappbiblioteca.util.ConvertDate;
import br.com.maralto.webappbiblioteca.util.jsf.FacesMessageUtils;
import br.com.maralto.webappbiblioteca.util.model.EnderecoUtil;

@Controller
@Scope("session")
public class PessoaBean {

	private Pessoa pessoa;
	private String dataNascimento;

	private List<Pessoa> pessoasList;
	
	@Autowired
	private FacesMessageUtils facesMessageUtils;

	@Autowired
	PessoaService pessoaService;
	@Autowired
	EnderecoUtil enderecoUtil;
	
	private String cepDigitado;

	@PostConstruct
	private void init() {

		pessoa = new Pessoa();
		
		findAll();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoasList() {
		return pessoasList;
	}

	public void setPessoasList(List<Pessoa> pessoasList) {
		this.pessoasList = pessoasList;
	}

	public void findAll() {

		pessoasList = pessoaService.findAll();

	}
	
	public void prepareSave() {
		pessoa = new Pessoa();
		this.dataNascimento = "";
	}
	
	public void prepareUpdate(Pessoa pessoa) {
		
		setDataNascimento(ConvertDate.DateToString(pessoa.getDataNascimento()));
		this.pessoa = pessoa;
	}

	public void save() {
		
		
		if (dataNascimento != null && !dataNascimento.equals("")) {
			pessoa.setDataNascimento(ConvertDate.formataData(dataNascimento));
		}
		
		
		pessoaService.save(pessoa);

		findAll();

		reset();

	}
	
	public static boolean validate(String emailStr) {
		final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
}
	
	public void buscaCep() {
		
		Pessoa pessoaBusca = new Pessoa();

		try {
			pessoaBusca = enderecoUtil.buscarCep(cepDigitado);
			
			this.pessoa.setBairro(pessoaBusca.getBairro());
			this.pessoa.setCep(pessoaBusca.getCep());
			this.pessoa.setCidade(pessoaBusca.getCidade());
			this.pessoa.setLogradouro(pessoaBusca.getLogradouro());
			this.pessoa.setUf(pessoaBusca.getUf());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void reset() {
		pessoa = new Pessoa();
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCepDigitado() {
		return cepDigitado;
	}

	public void setCepDigitado(String cepDigitado) {
		this.cepDigitado = cepDigitado;
	}


}
