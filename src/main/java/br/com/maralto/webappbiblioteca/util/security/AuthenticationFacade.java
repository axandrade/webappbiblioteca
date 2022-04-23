package br.com.maralto.webappbiblioteca.util.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
	Authentication getAuthentication();
}
