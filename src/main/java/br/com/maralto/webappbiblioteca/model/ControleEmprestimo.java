package br.com.maralto.webappbiblioteca.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.maralto.webappbiblioteca.util.ConvertDate;
import br.com.maralto.webappbiblioteca.util.model.PersistentEntity;
@Entity
@Table(name = "CONTROLE_EMPRESTIMOS")
@AttributeOverride(name = "id", column = @Column(name = "CONEMP_ID"))
public class ControleEmprestimo extends PersistentEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "CONEMP_DATA_EMPRESTIMO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmprestimo;
	
	
	@Column(name = "CONEMP_DATA_ENTREGA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrega;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CONEMP_EMP_ID", referencedColumnName = "EMP_ID", nullable = false)
	private Emprestimo emprestimo;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CONEMP_LIV_ID", referencedColumnName = "LIV_ID", nullable = false)
	private Livro livro = new Livro();
	
	@Column(name = "CONEMP_SITUACAO")
	private String situacao;
	
	@JoinColumn(name = "CONEMP_USU_ID", referencedColumnName = "USU_ID", nullable = false)
	@ManyToOne(optional = false)
	private Usuario usuario; 
	
	@Transient
	private boolean isItemDevolucaoList;

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	


	public String getDataEmprestimoFormatado() {
		return ConvertDate.DateToStringTimeStamp(this.dataEmprestimo);
	}
	
	
	
	public String getDataEntregaFormatado() {
		return ConvertDate.DateToStringTimeStamp(this.dataEntrega);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public boolean isItemDevolucaoList() {
		return isItemDevolucaoList;
	}

	public void setItemDevolucaoList(boolean isItemDevolucaoList) {
		this.isItemDevolucaoList = isItemDevolucaoList;
	}

}
