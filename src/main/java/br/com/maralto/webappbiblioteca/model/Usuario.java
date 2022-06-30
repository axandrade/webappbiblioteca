package br.com.maralto.webappbiblioteca.model;

import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.maralto.webappbiblioteca.util.model.PersistentEntity;

@Entity
@Table(name = "USUARIOS")
@AttributeOverride(name = "id", column = @Column(name = "USU_ID"))
public class Usuario extends PersistentEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "USU_NOME")
	private String nome;
	@Column(name = "USU_LOGIN", unique = true)
	private String login;
	@Column(name = "USU_SENHA")
	private String senha;
	@Column(name = "USU_ATIVO")
	private String ativo;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "AUTORIZACAO_USUARIO", joinColumns = {	@JoinColumn(name = "USU_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUT_ID") })
	private Set<Autorizacao> autorizacaoList;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public Set<Autorizacao> getAutorizacaoList() {
		return autorizacaoList;
	}

	public void setAutorizacaoList(Set<Autorizacao> autorizacaoList) {
		this.autorizacaoList = autorizacaoList;
	}

	

}
