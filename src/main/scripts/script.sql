--

DROP TABLE public.autores CASCADE;

DROP TABLE public.autores_livros CASCADE;

DROP TABLE public.autorizacao_usuario CASCADE;

DROP TABLE public.autorizacoes CASCADE;

DROP TABLE public.controle_emprestimos CASCADE;

DROP TABLE public.emprestimos CASCADE;

DROP TABLE public.idiomas CASCADE;

DROP TABLE public.livros CASCADE;

DROP TABLE public.pessoas CASCADE;

DROP TABLE public.usuarios CASCADE;



CREATE TABLE USUARIOS (
	USU_ID serial PRIMARY KEY,
	USU_NOME VARCHAR ( 50 ),
	USU_LOGIN VARCHAR ( 50 ),
	USU_SENHA VARCHAR ( 90 ),
	USU_ATIVO VARCHAR ( 10 )
		 
);

CREATE TABLE AUTORIZACOES (
	AUT_ID serial PRIMARY KEY,
	AUT_DESCRICAO VARCHAR ( 50 ) 
		 
);

CREATE TABLE AUTORIZACAO_USUARIO (
	AUTORIZACAO_USUARIO_ID serial PRIMARY KEY,
	USU_ID bigint,
	AUT_ID bigint
		 
);

alter table AUTORIZACAO_USUARIO 
add constraint fk_usuarios
foreign key (USU_ID)
references USUARIOS(USU_ID);

alter table AUTORIZACAO_USUARIO 
add constraint fk_autorizacoes
foreign key (AUT_ID)
references AUTORIZACOES(AUT_ID);

INSERT INTO public.usuarios(usu_nome, usu_login, usu_senha, usu_ativo)
    VALUES ('admin', 'admin','$2a$10$x1VZujdOdnWOlGq.mTcGmO4VKb6suEIDnqWydGAfIXbZ7WQCXykuC', 'true');
	--senha 123

INSERT INTO public.autorizacoes(aut_id, aut_descricao)
    VALUES (1, 'ROLE_ADMIN');

INSERT INTO public.autorizacao_usuario(autorizacao_usuario_id, usu_id, aut_id)
    VALUES (1, 1, 1);

   
--==================== FIM CRIACAO USUARIO ==============================================================
      
   CREATE TABLE PESSOAS (
	PES_ID serial PRIMARY KEY,
	PES_NOME varchar(100),
	PES_CPF varchar(11),
	PES_EMAIL varchar(50),
	PES_DATA_NASCIMENTO DATE,
	PES_DATA_CADASTRO DATE,
	PES_CONTATO1 varchar(50),
	PES_CONTATO2 varchar(50),
	PES_LOGRADOURO varchar(100),
	PES_NUMERO varchar(11),
	PES_COMPLEMENTO varchar(50),
	PES_BAIRRO varchar(50),
	PES_CEP varchar(50),
	PES_CIDADE varchar(50),
	PES_UF varchar(50)
);

CREATE TABLE AUTORES (
	AUT_ID serial PRIMARY KEY,
	AUT_NOME VARCHAR ( 80 )
);

CREATE TABLE IDIOMAS (
	IDIOMA_ID serial PRIMARY KEY,
	IDIOMA_ATIVO bool,
	IDIOMA_DESCRICAO varchar(100) NOT NULL
	
);



CREATE TABLE LIVROS (
	LIV_ID serial PRIMARY KEY,
	LIV_TITULO VARCHAR ( 50 ),
	LIV_ISBN VARCHAR ( 50 ),	
	LIV_DATA_CADASTRO TIMESTAMP,
	LIV_DATA_PUBLICACAO TIMESTAMP,
	LIV_IDIOMA_ID bigint,
	LIV_CONTROLE_EMPRESTIMO_ID bigint
);

CREATE TABLE AUTORES_LIVROS (
	AUTORES_LIVROS_ID serial PRIMARY KEY,
	AUT_ID bigint,
	LIV_ID bigint 
			 
);

CREATE TABLE EMPRESTIMOS (
	EMP_ID serial PRIMARY KEY,
	EMP_DATA_EMPRESTIMO TIMESTAMP,
	EMP_DATA_DEVOLUCAO DATE,
	EMP_PES_ID bigint,
	EMP_OBSERVACAO varchar(600),
	EMP_STATUS boolean,
	EMP_VALOR_TOTAL_MULTA numeric(4,2)
);

CREATE TABLE CONTROLE_EMPRESTIMOS (
	CONEMP_ID serial PRIMARY KEY,
	CONEMP_DATA_EMPRESTIMO TIMESTAMP,
	CONEMP_DATA_ENTREGA TIMESTAMP,
	CONEMP_SITUACAO varchar(12),
	CONEMP_EMP_ID bigint,
	CONEMP_LIV_ID bigint,
	CONEMP_USU_ID bigint
);

-- chaves estrangeiras de livros

alter table LIVROS 
add constraint fk_idioma
foreign key (LIV_IDIOMA_ID)
references IDIOMAS(IDIOMA_ID);

alter table LIVROS 
add constraint fk_controle_emprestimos
foreign key (LIV_CONTROLE_EMPRESTIMO_ID)
references CONTROLE_EMPRESTIMOS(CONEMP_ID);



-- chaves estrangeiras de autores_livros

alter table AUTORES_LIVROS 
add constraint fk_autores
foreign key (AUT_ID)
references AUTORES(AUT_ID);

alter table AUTORES_LIVROS 
add constraint fk_livros
foreign key (LIV_ID)
references LIVROS(LIV_ID);

-- chaves estrangeiras de emprestimos

alter table EMPRESTIMOS 
add constraint fk_pessoa
foreign key (EMP_PES_ID)
references PESSOAS(PES_ID);

-- chaves estrangeiras de controle_emprestimos



alter table CONTROLE_EMPRESTIMOS 
add constraint fk_usuario
foreign key (CONEMP_USU_ID)
references USUARIOS(USU_ID);


alter table CONTROLE_EMPRESTIMOS 
add constraint fk_emprestimos
foreign key (CONEMP_EMP_ID)
references EMPRESTIMOS(EMP_ID);

alter table CONTROLE_EMPRESTIMOS 
add constraint fk_livros
foreign key (CONEMP_LIV_ID)
references LIVROS(LIV_ID);




--inserts de idiomas
INSERT INTO public.idiomas(idioma_ativo, idioma_descricao)
    VALUES (true, 'PORTUGUÊS');
    
INSERT INTO public.idiomas(idioma_ativo, idioma_descricao)
    VALUES (true, 'ESPANHOL');
    
INSERT INTO public.idiomas(idioma_ativo, idioma_descricao)
    VALUES (true, 'ALEMÃO');
   
INSERT INTO public.idiomas(idioma_ativo, idioma_descricao)
    VALUES (true, 'INGLÊS');

--insert pessoas
   
INSERT INTO public.pessoas
(pes_nome, pes_cpf, pes_email, pes_data_nascimento, pes_data_cadastro, pes_contato1, pes_contato2, pes_logradouro, pes_numero, pes_complemento, pes_bairro, pes_cep, pes_cidade, pes_uf)
VALUES('Alexsandro Andrade', '01786978342', 'alexsandro.mpe@gmail.com', '1988-02-03', NULL, '85985552048', '', 'Rua Expedito Pereira Gomes', '151', 'casa a', 'Parque SÃƒÂ£o JoÃƒÂ£o', '61946-040', 'Maranguape', 'CE');

INSERT INTO public.pessoas
(pes_nome, pes_cpf, pes_email, pes_data_nascimento, pes_data_cadastro, pes_contato1, pes_contato2, pes_logradouro, pes_numero, pes_complemento, pes_bairro, pes_cep, pes_cidade, pes_uf)
VALUES('Lilia nogueira', '61597387315', 'liliadesousanogueira@gmail.com', '1986-06-12', NULL, '85988455050', '', 'Rua Artista Plástico Joaquim de Souza', '101', 'ap 801 torre c', 'Papicu', '60176-106', 'Fortaleza', 'CE');

--insert autores

INSERT INTO public.autores(aut_nome) VALUES('J. R. R. Tolkien');
INSERT INTO public.autores(aut_nome) VALUES('J. K. Rowling');
INSERT INTO public.autores(aut_nome) VALUES('Karl Marx');
INSERT INTO public.autores(aut_nome) VALUES('Friedrich Engels');
INSERT INTO public.autores(aut_nome) VALUES('Bernard Cornwell');

--insert livros

INSERT INTO public.livros
(liv_titulo, liv_isbn, liv_data_cadastro, liv_data_publicacao, liv_idioma_id)
VALUES(' O Senhor dos Anéis: A Sociedade do Anel', 'eretrg', '2022-01-08 00:00:00.000', '1876-04-01 00:00:00.000', 1);
INSERT INTO public.livros
(liv_titulo, liv_isbn, liv_data_cadastro, liv_data_publicacao, liv_idioma_id)
VALUES('Manifesto Comunista', '123456789', '2022-01-08 00:00:00.000', '1872-04-01 00:00:00.000', 1);
INSERT INTO public.livros
(liv_titulo, liv_isbn, liv_data_cadastro, liv_data_publicacao, liv_idioma_id)
VALUES('War Lord', '123456789', '2022-01-08 00:00:00.000', '1872-04-01 00:00:00.000', 2);
INSERT INTO public.livros
(liv_titulo, liv_isbn, liv_data_cadastro, liv_data_publicacao, liv_idioma_id)
VALUES('Filhos de Hurin', '123456789', '2022-01-08 00:00:00.000', '1872-04-01 00:00:00.000', 3);


--insert autores_livros

INSERT INTO public.autores_livros(autores_livros_id, aut_id, liv_id) VALUES(1, 1, 1);
INSERT INTO public.autores_livros(autores_livros_id, aut_id, liv_id) VALUES(2, 3, 2);
INSERT INTO public.autores_livros(autores_livros_id, aut_id, liv_id) VALUES(3, 4, 2);
INSERT INTO public.autores_livros(autores_livros_id, aut_id, liv_id) VALUES(4, 5, 3);
INSERT INTO public.autores_livros(autores_livros_id, aut_id, liv_id) VALUES(5, 5, 4);

select * from usuarios u ;

select * from pessoas p;

select * from autores a; 

select * from livros l;

select * from idiomas i;


select * from emprestimos e;

select * from controle_emprestimos ce;

select distinct l.liv_id, l.liv_titulo, l.liv_data_cadastro, l.liv_data_publicacao, l.liv_idioma_id, l.liv_isbn
from public.livros l
where l.liv_id not in (select ce.conemp_liv_id  from controle_emprestimos ce where ce.conemp_situacao = 'EMPRESTADO')



