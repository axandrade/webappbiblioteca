package br.com.maralto.webappbiblioteca.util.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ProtheusEntity implements Serializable, SampleEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "R_E_C_N_O_", nullable = false, columnDefinition = "BIGINT UNSIGNED")
	protected Long id;
	@Column(name = "D_E_L_E_T_")
	private String deleted;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id){
		this.id = id;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted){
		this.deleted = deleted;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.getId() != null ? this.getId().hashCode() : 0);

		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;

		ProtheusEntity other = (ProtheusEntity) object;
		if (this.getId() != other.getId() && (this.getId() == null || !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	


}