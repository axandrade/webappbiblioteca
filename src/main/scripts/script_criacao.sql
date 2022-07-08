--DROP TABLES

DROP TABLE public.autorizacao_usuario CASCADE;

DROP TABLE public.usuarios CASCADE;

DROP TABLE public.autorizacoes CASCADE;

DROP TABLE public.autores CASCADE;

DROP TABLE public.autores_livros CASCADE;

DROP TABLE public.controle_emprestimos CASCADE;

DROP TABLE public.emprestimos CASCADE;

DROP TABLE public.livros CASCADE;

DROP TABLE public.pessoas CASCADE;

CREATE TABLE USUARIOS (
	USU_ID serial PRIMARY KEY,
	USU_NOME VARCHAR ( 50 ),
	USU_LOGIN VARCHAR ( 50 ) unique,
	USU_SENHA VARCHAR ( 90 ),
	USU_ATIVO VARCHAR ( 10 )		 
);

CREATE TABLE AUTORIZACOES (
	AUT_ID serial PRIMARY KEY,
	AUT_AUTORIZACAO VARCHAR ( 50 ),
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


INSERT INTO public.usuarios
(usu_id, usu_nome, usu_login, usu_senha, usu_ativo)
VALUES(1, 'admin', 'admin', '$2a$10$rsZ9qUMxtaMiXQBjI35UaOmuhHi2UnLM.RBgd9CCpoXci.oJvoyCq', 'true');
--senha: @dmin123

INSERT INTO public.usuarios
(usu_id, usu_nome, usu_login, usu_senha, usu_ativo)
VALUES(2, 'Lilia Andrade', 'lilia.andrade', '$2a$10$CvFE/4cjJD3YqrKmiRXatOI8DbhGzxgO9vapuJVVw9FZ5E9KuzxqW', 'true');
--senha: 123456

INSERT INTO public.autorizacoes(aut_id, aut_autorizacao, aut_descricao)
    VALUES (1, 'ROLE_ADMIN', 'Administrador');
   
   INSERT INTO public.autorizacoes(aut_id, aut_autorizacao, aut_descricao)
    VALUES (2, 'ROLE_USER', 'Usuário');

INSERT INTO public.autorizacao_usuario(autorizacao_usuario_id, usu_id, aut_id)
    VALUES (1, 1, 1);

 INSERT INTO public.autorizacao_usuario(autorizacao_usuario_id, usu_id, aut_id)
    VALUES (2, 2, 2);  
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
	AUT_NOME VARCHAR ( 80 ) unique 
);


CREATE TABLE LIVROS (
	LIV_ID serial PRIMARY KEY,
	LIV_TITULO VARCHAR ( 100 ),	
	LIV_ISBN VARCHAR ( 50 ),
	LIV_SITUACAO VARCHAR ( 15 ),
	LIV_DATA_CADASTRO TIMESTAMP,
	LIV_DATA_PUBLICACAO TIMESTAMP,
	LIV_IDIOMA VARCHAR ( 50 ),
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
	CONEMP_EMP_ID bigint,
	CONEMP_LIV_ID bigint,
	CONEMP_USU_ID bigint
);

-- chaves estrangeiras de livros

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


--insert pessoas
   
INSERT INTO public.pessoas
(pes_nome, pes_cpf, pes_email, pes_data_nascimento, pes_data_cadastro, pes_contato1, pes_contato2, pes_logradouro, pes_numero, pes_complemento, pes_bairro, pes_cep, pes_cidade, pes_uf)
VALUES('Alexsandro Andrade', '01786978342', 'alexsandro.mpe@gmail.com', '1988-02-03', NULL, '85985552048', '', 'Rua Expedito Pereira Gomes', '151', 'casa a', 'Parque São João', '61946-040', 'Maranguape', 'CE');

INSERT INTO public.pessoas
(pes_nome, pes_cpf, pes_email, pes_data_nascimento, pes_data_cadastro, pes_contato1, pes_contato2, pes_logradouro, pes_numero, pes_complemento, pes_bairro, pes_cep, pes_cidade, pes_uf)
VALUES('Lilia nogueira', '61597387315', 'liliadesousanogueira@gmail.com', '1986-06-12', NULL, '85988455050', '', 'Rua Artista Plástico Joaquim de Souza', '101', 'ap 801 torre c', 'Papicu', '60176-106', 'Fortaleza', 'CE');



select * from usuarios u ;

select * from autorizacoes a; 

select * from autorizacao_usuario au; 

select * from pessoas p;

select * from autores a; 

select *  from livros l;

select * from autores_livros al; 

select * from emprestimos e;

select * from controle_emprestimos ce;




