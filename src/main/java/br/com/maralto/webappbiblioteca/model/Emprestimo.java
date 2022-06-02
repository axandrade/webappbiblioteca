package br.com.maralto.webappbiblioteca.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.maralto.webappbiblioteca.util.ConvertDate;
import br.com.maralto.webappbiblioteca.util.model.PersistentEntity;
@Entity
@Table(name = "EMPRESTIMOS")
@AttributeOverride(name = "id", column = @Column(name = "EMP_ID"))
public class Emprestimo extends PersistentEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "EMP_DATA_EMPRESTIMO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmprestimo;
	
	@Column(name = "EMP_DATA_DEVOLUCAO")
	@Temporal(TemporalType.DATE)
	private Date dataDevolucao;
	
	@JoinColumn(name = "EMP_PES_ID", referencedColumnName = "PES_ID", nullable = false)
	@ManyToOne(optional = false)
	
	private Pessoa pessoa;
	
	@Column(name = "EMP_OBSERVACAO")
	private String observacao;
	
	@Column(name = "EMP_STATUS")
	private Boolean status;
	
	@OneToMany(mappedBy = "emprestimo",fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<ControleEmprestimo> controleEmprestimoList = new ArrayList<ControleEmprestimo>();

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public String getDataEmprestimoFormatado() {
		return ConvertDate.DateToStringTimeStamp(this.dataEmprestimo);
	}
	
	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public String getDataDevolucaoFormatado() {
		return ConvertDate.DateToString(this.dataDevolucao);
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<ControleEmprestimo> getControleEmprestimoList() {
		return controleEmprestimoList;
	}

	public void setControleEmprestimoList(List<ControleEmprestimo> controleEmprestimoList) {
		this.controleEmprestimoList = controleEmprestimoList;
	}
	
}
