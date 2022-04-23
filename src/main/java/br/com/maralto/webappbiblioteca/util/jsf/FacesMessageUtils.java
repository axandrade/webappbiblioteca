package br.com.maralto.webappbiblioteca.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

@Component
public class FacesMessageUtils {
	
	private void addMessage(Severity severity, String sumary, String detail) {
		FacesMessage facesMessage = new FacesMessage(severity, sumary, detail);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
	}
	
	public void addWarningMessage(String sumary, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, sumary, detail);
	}
	
	public void addWarningMessage(String sumary) {
		addMessage(FacesMessage.SEVERITY_WARN, sumary, null);
	}
	
	public void addErrorMessage(String sumary, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, sumary, detail);
	}
	
	public void addErrorMessage(String sumary) {
		addMessage(FacesMessage.SEVERITY_ERROR, sumary, null);
	}
	
	public void addInfoMessage(String sumary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, sumary, detail);
	}
	
	public void addInfoMessage(String sumary) {
		addMessage(FacesMessage.SEVERITY_INFO, sumary, null);
	}
	
}
