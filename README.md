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
