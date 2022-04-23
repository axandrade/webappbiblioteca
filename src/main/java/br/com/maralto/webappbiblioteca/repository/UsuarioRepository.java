package br.com.maralto.webappbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maralto.webappbiblioteca.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByLogin(String login);
}
