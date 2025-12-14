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

- Java 17 (compilação para Java 21)
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
- Java (versão 17/21)
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
