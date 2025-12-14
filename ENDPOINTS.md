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
