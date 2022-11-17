package br.com.maralto.webappbiblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.maralto.webappbiblioteca.exception.GenericException;
import br.com.maralto.webappbiblioteca.model.Usuario;
import br.com.maralto.webappbiblioteca.repository.UsuarioRepository;
import br.com.maralto.webappbiblioteca.util.jsf.FacesMessageUtils;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private FacesMessageUtils facesMessageUtils;

	@Override
	public Usuario findByLogin(String login) {
		return this.repository.findByLogin(login);
	}

	@Override
	public void save(Usuario usuario, String confirmaSenha) {
	
		try {			
						
			validaUsuario(usuario, confirmaSenha);
			
			if(usuario.getDesejaResetarSenha())
				usuario.setSenha(gerarBCryptPasswordEncoder(confirmaSenha));

			this.repository.save(usuario);
		}catch (JpaSystemException e) {
			facesMessageUtils.addErrorMessage("Nome do Usuário já Existe");
			e.printStackTrace();
		} catch (GenericException e) {
			facesMessageUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String gerarBCryptPasswordEncoder(String senha) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		return passwordEncoder.encode(senha);
	}
	
	
	private void validaUsuario(Usuario usuario, String confirmaSenha) throws GenericException{
		
		if(usuario.getDesejaResetarSenha()) {
			if (confirmaSenha.equals("") && usuario.getSenha().equals("")) {
				throw new GenericException("O campo Senha e a confirmação da senha são obrigatórios");
			}
			
			if (!usuario.getSenha().equals(confirmaSenha)) {
				throw new GenericException("Voce digitou uma senha diferente na confirmação");
			}
		}
		
		
		

	}

	@Override
	public void remove(Usuario usuario) {
		this.repository.delete(usuario);
	}

	@Override
	public List<Usuario> findAll() {
		return repository.findAll();
	}



	
	

}
