package br.com.maralto.webappbiblioteca.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.maralto.webappbiblioteca.util.ConvertDate;
import br.com.maralto.webappbiblioteca.util.model.PersistentEntity;


@Entity
@Table(name = "PESSOAS")
@AttributeOverride(name = "id", column = @Column(name = "PES_ID"))
public class Pessoa  extends PersistentEntity{
	
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "PES_NOME")
	private String nome;
	@Column(name = "PES_CPF")
	private String cpf;
	@Column(name = "PES_EMAIL")
	private String email;
	@Column(name = "PES_DATA_NASCIMENTO")
	@Temporal(TemporalType.DATE )
	private Date dataNascimento;
	@Column(name = "PES_DATA_CADASTRO")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	@Column(name = "PES_CONTATO1")
	private String contato1;
	@Column(name = "PES_CONTATO2")
	private String contato2;
	@Column(name = "PES_LOGRADOURO")
	private String logradouro;
	@Column(name = "PES_NUMERO")
	private String numero;
	@Column(name = "PES_COMPLEMENTO")
	private String complemento;
	@Column(name = "PES_BAIRRO")
	private String bairro;
	@Column(name = "PES_CEP")
	private String cep;
	@Column(name = "PES_CIDADE")
	private String cidade;
	@Column(name = "PES_UF")
	private String uf;

		
	public Pessoa() {
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public String getDataNascimentoFormatado() {
		return ConvertDate.DateToString(this.dataNascimento);
	}

	public void setDataNascimento(Date dataNascimento) {
		
		this.dataNascimento = dataNascimento;
	}

	public String getContato1() {
		return contato1;
	}

	public void setContato1(String contato1) {
		this.contato1 = contato1;
	}

	public String getContato2() {
		return contato2;
	}

	public void setContato2(String contato2) {
		this.contato2 = contato2;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	

}
