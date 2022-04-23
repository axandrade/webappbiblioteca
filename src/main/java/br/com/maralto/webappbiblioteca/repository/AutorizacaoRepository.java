package br.com.maralto.webappbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maralto.webappbiblioteca.model.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {

}
