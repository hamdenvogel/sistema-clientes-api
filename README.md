# sistema-clientes-api

Sistema que implementa a API de Gestão de Clientes

# Descrição: 

API RESTful utilizando Java Spring Boot para a consulta e gerenciamento de clientes. A API fornece funcionalidades de autenticação JWT, controle de perfis de usuário, e operações CRUD completas para gestão de clientes.  Sistema com arquitetura em camadas, implementando segurança com Spring Security e autenticação baseada em tokens. 

# Configuração do Sonar:

1. Instalar o Docker (https://docs.docker.com/desktop/setup/install/windows-install/)
2. Rodar o comando docker:   docker run -d -p 9000:9000 --name=sonarqube sonarqube:9.9-community
3. Ir na página principal do SonarQube (http://localhost:9000) e configurar usuário e senha
4. Depois Ir na url: http://localhost:9000/projects/create e criar um projeto; criei como por ex: sistema-clientes-api
5. Gerar o token dele, por ex: 999999999999999999999999
6. Rodar o comando mvn:    mvn clean verify sonar:sonar "-Dsonar.projectKey=sistema-clientes-api" "-Dsonar.host. url=http://localhost:9000" "-Dsonar.login=SEU_TOKEN_GERADO_COLOCAR_AQUI"

# Requisitos técnicos:

- Java 21
- Spring Boot 3.2.5
- Spring Data JPA
- Spring Security
- Mapstruct 1.5.5.Final
- Lombok
- JasperReports 6.2.2
- Apache POI 5.2.2
- JWT (jjwt 0.9.1)
- JUnit 5
- Mockito
- Hibernate
- Postgres
- H2 Database (Test)
- SonarQube 9.9-community
- Jacoco

# Código-fonte Java:

- controller
- service
- repository
- dto
- mapper
- entity
- exception
- util
- config
- security
- enums
- integration tests
- unit tests

# Tecnologias/Ferramentas utilizadas

- Maven
- Java (versão 21)
- JUnit (versão 5)
- Spring Data JPA (Repository)
- Spring Security + JWT
- Postgres (Runtime) / H2 (Test)
- Docker
- Sonar (versão 9.9 community)
- Postman
- Jasper Reports (Geração de relatórios)
- Apache POI (Geração de documentos)

# IDE´s utilizadas

- Google AntiGrativy
- Intellij Ultimate

# Testes automatizados - JUnit 5/Mockito

- Testes unitários (ClienteServiceTest, ClienteRepositoryTest, ClienteMapperTest, etc.)
- Testes de integração (ClienteControllerIntegrationTest)
- Testes de segurança (AuthenticationTest)

# Cobertura de Testes (SonarQube)

- Link para o Dashboard:  [SonarQube Dashboard](http://localhost:9000/dashboard? id=sistema-clientes-api)

# Funcionalidades Principais

## Autenticação e Autorização
- Sistema de login com JWT
- Controle de perfis (ROLE_USER, ROLE_MODERATOR, ROLE_ADMIN)
- Validação de tokens
- Integração com reCAPTCHA

## Gestão de Clientes
- Cadastro de clientes
- Atualização de dados
- Consulta de clientes
- Exclusão de registros
- Geração de relatórios

## Configurações
- CORS configurado
- Segurança com Spring Security
- Profiles para diferentes ambientes (dev, prod)
- Suporte a deploy no Heroku

# Variáveis de Ambiente

```env
DB_PASSWORD=XXX
JWT_SIGNING_KEY=XXXXXXXXXXX
JWT_SIGNING_KEY_2=XXXXXXXXXXXXXXXXXXXXXXXXXXX
RECAPTCHA_SECRET=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
```

# Deploy

O projeto está configurado para deploy no Heroku através do arquivo Procfile:

```
web: java -Dserver.port=$PORT -Dspring.profiles.active=prod -jar target/clientes-api. jar
```

# Documentação de Endpoints - Sistema de Clientes API

A documentação completa dos endpoints estará disponível em breve, incluindo:

- Endpoints de Autenticação (Login, Registro, Refresh Token)
- Endpoints de Clientes (CRUD completo)
- Endpoints de Serviços de Prestação
- Endpoints de Usuários
- Endpoints de Relatórios

# Como Executar

1. Clonar o repositório
2. Configurar as variáveis de ambiente no arquivo `.env`
3. Configurar o banco de dados PostgreSQL
4. Executar: `mvn clean install`
5. Executar: `mvn spring-boot:run`

A aplicação estará disponível em: http://localhost:8080

# Estrutura do Projeto

```
clientes/
├── src/
│   ├── main/
│   │   ├── java/io/github/hvogel/clientes/
│   │   │   ├── config/          # Configurações (Security, CORS, etc)
│   │   │   ├── enums/           # Enumerações (Perfis de usuário)
│   │   │   ├── model/           # Entidades JPA
│   │   │   ├── repository/      # Repositórios Spring Data
│   │   │   ├── rest/            # Controllers e DTOs
│   │   │   ├── service/         # Lógica de negócio
│   │   │   ├── security/        # JWT e autenticação
│   │   │   ├── mapper/          # MapStruct mappers
│   │   │   ├── util/            # Utilitários
│   │   │   └── exception/       # Exceções customizadas
│   │   └── resources/
│   └── test/                    # Testes unitários e integração
├── pom.xml
├── Procfile
└── README.md
```

# Esquema do Banco de Dados (DDL)

```sql
CREATE TABLE meusservicos.usuario (
	id bigserial NOT NULL,
	email varchar(150) NULL,
	senha varchar(120) NULL,
	login varchar(100) NULL,
	CONSTRAINT uk5171l57faosmj8myawaucatdw UNIQUE (email),
	CONSTRAINT uk_pm3f4m4fqv89oeeeac4tbe2f4 UNIQUE (login),
	CONSTRAINT ukpm3f4m4fqv89oeeeac4tbe2f4 UNIQUE (login),
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

CREATE TABLE meusservicos.perfil (
	id serial4 NOT NULL,
	nome varchar(20) NULL,
	CONSTRAINT perfil_pkey PRIMARY KEY (id)
);

CREATE TABLE meusservicos.usuario_perfil (
	usuario_id int8 NOT NULL,
	perfil_id int4 NOT NULL,
	CONSTRAINT usuario_perfil_pkey PRIMARY KEY (usuario_id, perfil_id),
	CONSTRAINT fk22cgfn0obntlvqyfn33pyk24d FOREIGN KEY (perfil_id) REFERENCES meusservicos.perfil(id),
	CONSTRAINT fknrjqnbylalt4ykxbcef24f57w FOREIGN KEY (usuario_id) REFERENCES meusservicos.usuario(id)
);

Create Table meusservicos.cliente
(
	id bigserial NOT NULL PRIMARY KEY,
	nome character varying(150),
	cpf character varying(11),
	data_cadastro date,
	pix character varying(50),
    cep character varying(12),
    endereco character varying(100),
    complemento character varying(50),
    uf character varying(2),
    cidade character varying(200)
);

Create Table meusservicos.empresa
(
	id bigserial NOT NULL PRIMARY KEY,
	nome character varying(150),
	cpnj character varying(11),
	data_cadastro date,
	pix character varying(50)
);

Create Table meusservicos.gestor
(
	id bigserial NOT NULL PRIMARY KEY,
	nome character varying(150),
	cpf character varying(11),
	data_cadastro date,
	pix character varying(50),
    cep character varying(12),
    endereco character varying(100),
    complemento character varying(50),
    uf character varying(2),
    cidade character varying(200)
);

Create Table meusservicos.planejamento
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(150) NOT NULL,
	data_inicio date,
	data_fim date,



	id_cliente bigint REFERENCES meusservicos.cliente (id),
	valor numeric(16,2),
	data date,
	data_conclusao date,
	status character varying(1) CHECK (status in ('P', 'E', 'C', 'F')) NOT NULL,  --previsto, em atendimento, cancelado, finalizado
	id_prestador bigint REFERENCES meusservicos.prestador (id),
	tipo character varying(1) CHECK (tipo in ('U','P')) NOT NULL,  --unitário, pacote (de serviços),
	id_natureza bigint REFERENCES meusservicos.natureza (id), --Corretiva, Periódica, Preventiva
	id_atividade bigint REFERENCES meusservicos.atividade (id), --Manutenção, Aquisição
	local_atendimento character varying(150),
	conclusao character varying(150)
);

Create Table meusservicos.profissao
(
	id bigserial NOT NULL PRIMARY KEY,
	codigo bigint,
	descricao character varying(200)
);

Create Table meusservicos.prestador
(
	id bigserial NOT NULL PRIMARY KEY,
	nome character varying(150),
	cpf character varying(11),
	data_cadastro date,
	pix character varying(50),
	avaliacao bigint,
    id_profissao bigint REFERENCES meusservicos.profissao (id),
	email character varying(200)
);

Create Table meusservicos.atividade
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(150) NOT NULL
);

Create Table meusservicos.natureza
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(150) NOT NULL
);

Create Table meusservicos.ServicoPrestado
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(150) NOT NULL,
	id_cliente bigint REFERENCES meusservicos.cliente (id),
	valor numeric(16,2),
	data date,
	data_conclusao date,
	status character varying(1) CHECK (status in ('P', 'E', 'C', 'F')) NOT NULL,  --previsto, em atendimento, cancelado, finalizado
	id_prestador bigint REFERENCES meusservicos.prestador (id),
	tipo character varying(1) CHECK (tipo in ('U','P')) NOT NULL,  --unitário, pacote (de serviços),
	id_natureza bigint REFERENCES meusservicos.natureza (id), --Corretiva, Periódica, Preventiva
	id_atividade bigint REFERENCES meusservicos.atividade (id), --Manutenção, Aquisição
	local_atendimento character varying(150),
	conclusao character varying(150)
);

Create Table meusservicos.chamado
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(150) NOT NULL,
	id_cliente bigint REFERENCES meusservicos.cliente (id),
	data date,
	local_acontecimento character varying(150),
	status character varying(1) CHECK (status in ('A', 'E', 'C', 'F')) NOT NULL  --aguardando atendimento, em atendimento, cancelado, finalizado
)

Create Table meusservicos.diagnostico  --status identificado
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(150) NOT NULL,
	servico_prestado_id bigint REFERENCES meusservicos.ServicoPrestado (id)
);

Create Table meusservicos.solucao --status final
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(150) NOT NULL,
	servico_prestado_id bigint REFERENCES meusservicos.ServicoPrestado (id),
	valor numeric(16,2),
	desconto numeric(16,2)
);

Create Table meusservicos.equipamento
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(150) NOT NULL,
	servico_prestado_id bigint REFERENCES meusservicos.ServicoPrestado (id),
	marca character varying(50) NULL,
	modelo character varying(50) NULL,
	ano_fabricacao bigint NULL,
	ano_modelo bigint NULL
);

CREATE TABLE meusservicos.produto (
    id bigserial NOT NULL PRIMARY KEY,
    descricao character varying(100) NOT NULL,
    preco_unitario numeric(20,2),
	marca character varying(50) NULL,
	modelo character varying(50) NULL,
	ano_fabricacao bigint NULL,
	ano_modelo bigint NULL
);

CREATE TABLE meusservicos.pedido (
    id bigserial NOT NULL PRIMARY KEY,
	servico_prestado_id bigint REFERENCES meusservicos.ServicoPrestado (id),
    data_pedido timestamp,
    status varchar(20),
    total numeric(20,2)
);

CREATE TABLE meusservicos.item_pedido (
    id bigserial NOT NULL PRIMARY KEY,
    pedido_id integer references meusservicos.pedido (id),
    produto_id integer references meusservicos.produto (id),
    quantidade integer
);

Create Table meusservicos.documento (
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(150) NOT NULL,
	status character varying(1) CHECK (status in ('A','I')) NOT NULL --ATIVO, INATIVO
);

CREATE TABLE meusservicos.imagem
(
	id bigserial NOT NULL PRIMARY KEY,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    status boolean,
    updated_by character varying(255) COLLATE pg_catalog."default",
    updated_date timestamp without time zone,
    data bytea,
    file_name character varying(255) COLLATE pg_catalog."default",
    file_type character varying(255) COLLATE pg_catalog."default",
    size bigint,
    system_name character varying(255) COLLATE pg_catalog."default",
    uuid character varying(255) COLLATE pg_catalog."default",
	documento_id integer references meusservicos.documento (id) NOT NULL,
	chave_id bigint,
	original_file_name character varying(255) NULL
);

Create Table meusservicos.pacote
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(300) NOT NULL,
	justificativa character varying(100) NOT NULL,
	data date,
	data_previsao date,
	data_conclusao date,
	status character varying(1) CHECK (status in ('I', 'A', 'E', 'C', 'F')) NOT NULL --iniciado, aguardando atendimento, em atendimento, cancelado, finalizado
);

Create Table meusservicos.itemPacote
(
	id bigserial NOT NULL PRIMARY KEY,
	id_pacote bigint REFERENCES meusservicos.pacote (id),
	id_servico_prestado bigint REFERENCES meusservicos.ServicoPrestado (id)
);

Create Table meusservicos.parametro
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(300) NOT NULL,
	data date
);

Create Table meusservicos.arvore
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(300) NOT NULL,
	data date,
	id_servico_prestado bigint REFERENCES meusservicos.ServicoPrestado (id),
	valor numeric(16,2)
);

Create Table meusservicos.ramificacao
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(300) NOT NULL,
	data date,
	id_arvore bigint REFERENCES meusservicos.arvore (id),
	valor numeric(16,2)
);

Create Table meusservicos.no
(
	id bigserial NOT NULL PRIMARY KEY,
	descricao character varying(300) NOT NULL,
	data date,
	id_ramificacao bigint REFERENCES meusservicos.ramificacao (id),
	valor numeric(16,2)
);

*************************************************************************************************************
insert into meusservicos.natureza (descricao) values ('Corretiva');
insert into meusservicos.natureza (descricao) values ('Periódica');
insert into meusservicos.natureza (descricao) values ('Preventiva');
select * from meusservicos.natureza;
insert into meusservicos.atividade (descricao) values ('Manutenção');
insert into meusservicos.atividade (descricao) values ('Aquisição');
select * from meusservicos.atividade;


INSERT INTO meusservicos.perfil(nome) VALUES('ROLE_USER');
INSERT INTO meusservicos.perfil(nome) VALUES('ROLE_MODERATOR');
INSERT INTO meusservicos.perfil(nome) VALUES('ROLE_ADMIN');
select * from meusservicos.documento;
insert into meusservicos.documento (descricao, status) values ('P', 'A'); -- P = Prestador
insert into meusservicos.documento (descricao, status) values ('C', 'A'); -- C = Cliente
```

# Documentação da API

Abaixo estão listados os principais endpoints da aplicação, com exemplos de requisição e resposta.

## Clientes

### Criar Cliente
**POST** `/api/clientes`

Cria um novo cliente no sistema.

**Body (JSON):**
```json
{
  "nome": "Fulano de Tal",
  "cpf": "123.456.789-00",
  "pix": "email@example.com",
  "cep": "12345-678",
  "endereco": "Rua das Flores, 123",
  "complemento": "Apto 101",
  "uf": "SP",
  "cidade": "São Paulo"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "nome": "Fulano de Tal",
  "cpf": "123.456.789-00",
  "dataCadastro": "2023-10-27",
  "pix": "email@example.com",
  "cep": "12345-678",
  "endereco": "Rua das Flores, 123",
  "complemento": "Apto 101",
  "uf": "SP",
  "cidade": "São Paulo",
  "infoResponseDTO": {
    "mensagem": "Cliente criado com sucesso.",
    "titulo": "Informação"
  }
}
```

### Obter Cliente por ID
**GET** `/api/clientes/{id}`

Retorna os detalhes de um cliente específico.

**Response (200 OK):**
```json
{
  "id": 1,
  "nome": "Fulano de Tal",
  "cpf": "123.456.789-00",
  "dataCadastro": "27/10/2023",
  "pix": "email@example.com",
  "cep": "12345-678",
  "endereco": "Rua das Flores, 123",
  "complemento": "Apto 101",
  "uf": "SP",
  "cidade": "São Paulo"
}
```

### Atualizar Cliente
**PUT** `/api/clientes/{id}`

Atualiza os dados de um cliente existente.

**Body (JSON):**
```json
{
  "nome": "Fulano de Tal Editado",
  "cpf": "123.456.789-00",
  "pix": "novo@example.com",
  "cep": "12345-000",
  "endereco": "Rua Nova, 456",
  "complemento": "Casa",
  "uf": "RJ",
  "cidade": "Rio de Janeiro"
}
```

**Response (200 OK):**
```json
{
  "mensagem": "Cliente atualizado com sucesso.",
  "titulo": "Informação"
}
```

### Deletar Cliente
**DELETE** `/api/clientes/{id}`

Remove um cliente do sistema.

**Response (200 OK):**
```json
{
  "mensagem": "Cliente deletado com sucesso.",
  "titulo": "Informação"
}
```

### Listar Clientes (Paginado)
**GET** `/api/clientes/pesquisa-paginada`

Retorna uma lista paginada de clientes.

**Parâmetros de Query:**
- `page`: Número da página (padrão 0)
- `size`: Tamanho da página (padrão 10)
- `sort`: Ordenação (ex: `nome,asc`)
- `nome`: Filtro por nome (opcional)

**Response (200 OK):**
```json
{
  "content": [
    {
      "id": 1,
      "nome": "Fulano de Tal",
      "cpf": "123.456.789-00",
       ...
    }
  ],
  "pageable": { ... },
  "totalPages": 1,
  "totalElements": 1,
  ...
}
```

---

## Serviços Prestados

### Criar Serviço Prestado
**POST** `/api/servicos-prestados`

Registra um novo serviço prestado a um cliente.

**Body (JSON):**
```json
{
  "descricao": "Formatação de Computador",
  "preco": "150,00",
  "data": "10/10/2023",
  "idCliente": 1,
  "status": "E",
  "tipo": "Manutenção",
  "idNatureza": 1,
  "idAtividade": 1,
  "idPrestador": 1,
  "localAtendimento": "Domicilio",
  "conclusao": "Serviço realizado com sucesso"
}
```
*Nota: Status: E (Em Atendimento), C (Cancelado), F (Finalizado), P (Previsto)*

**Response (201 Created):**
```json
{
  "descricao": "Formatação de Computador",
  "preco": "150,00",
  "data": "10/10/2023",
  "idCliente": 1,
  "status": "E",
  "infoResponseDTO": {
    "mensagem": "Serviço criado com sucesso.",
    "titulo": "Informação"
  },
  ...
}
```

### Pesquisar Serviços
**GET** `/api/servicos-prestados`

Pesquisa serviços por nome do cliente e mês.

**Parâmetros de Query:**
- `nome`: Nome do cliente (parcial)
- `mes`: Mês do serviço (número)

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "descricao": "Formatação de Computador",
    "cliente": { "id": 1, "nome": "Fulano de Tal", ... },
    "valor": 150.00,
    "status": "E",
    "data": "2023-10-10"
    ...
  }
]
```

---

## Prestadores

### Criar Prestador
**POST** `/api/prestador`

Cadastra um novo prestador de serviços.

**Body (JSON):**
```json
{
  "nome": "Prestador Silva",
  "cpf": "987.654.321-00",
  "pix": "prestador@example.com",
  "avaliacao": 5,
  "idProfissao": 1,
  "email": "prestador@example.com"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "nome": "Prestador Silva",
  "cpf": "987.654.321-00",
  "pix": "prestador@example.com",
  "avaliacao": 5,
  "idProfissao": 1,
  "email": "prestador@example.com",
  "infoResponseDTO": {
    "mensagem": "Prestador criado com sucesso.",
    "titulo": "Informação"
  }
}
```

### Listar Prestadores (Paginado)
**GET** `/api/prestador/pesquisa-paginada`

Retorna uma lista paginada de prestadores.

**Parâmetros de Query:**
- `page`: Número da página (padrão 0)
- `size`: Tamanho da página (padrão 4)
- `sort`: Ordenação (ex: `nome,asc`)
- `nome`: Filtro por nome (opcional)
- `id`: Filtro por ID (opcional)

**Response (200 OK):**
```json
{
  "content": [
    {
      "id": 1,
      "nome": "Prestador Silva",
      "cpf": "987.654.321-00",
      ...
    }
  ],
  ...
}
```
