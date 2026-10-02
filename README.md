# 📦 StockControl

API REST para gerenciamento de produtos e controle de estoque, desenvolvida com **Java, Spring Boot e PostgreSQL**.

O projeto foi criado com o objetivo de aplicar, na prática, conceitos de desenvolvimento Back-End, como arquitetura em camadas, operações CRUD, persistência de dados, regras de negócio, validações, tratamento de exceções, documentação de API e testes unitários.

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Swagger / OpenAPI
- JUnit
- Mockito
- Postman
- Git e GitHub

## ⚙️ Funcionalidades

A API permite:

- Cadastrar produtos
- Listar todos os produtos
- Buscar produto por ID
- Atualizar dados de produtos
- Excluir produtos
- Registrar entrada de estoque
- Registrar saída de estoque
- Consultar produtos com estoque baixo
- Consultar histórico de movimentações
- Consultar movimentações de um produto
- Validar dados recebidos pela API
- Impedir movimentações com quantidade inválida
- Impedir saída superior ao estoque disponível
- Registrar automaticamente as movimentações de entrada e saída
- Tratar exceções da aplicação
- Documentar e testar endpoints através do Swagger

## 📁 Estrutura do projeto

```text
src
├── main
│   ├── java/com/raylane/stockcontrol
│   │   ├── config
│   │   │   └── OpenApiConfig.java
│   │   ├── controller
│   │   │   ├── MovimentacaoEstoqueController.java
│   │   │   └── ProdutoController.java
│   │   ├── dto
│   │   │   ├── ProdutoRequestDTO.java
│   │   │   └── ProdutoUpdateDTO.java
│   │   ├── exception
│   │   │   └── GlobalExceptionHandler.java
│   │   ├── model
│   │   │   ├── MovimentacaoEstoque.java
│   │   │   └── Produto.java
│   │   ├── repository
│   │   │   ├── MovimentacaoEstoqueRepository.java
│   │   │   └── ProdutoRepository.java
│   │   ├── service
│   │   │   └── ProdutoService.java
│   │   └── StockcontrolApplication.java
│   └── resources
│       └── application.properties
│
└── test
    └── java/com/raylane/stockcontrol/service
        └── ProdutoServiceTest.java
```

A aplicação utiliza uma arquitetura em camadas:

- **Controller:** recebe as requisições HTTP e disponibiliza os endpoints.
- **Service:** concentra as regras de negócio.
- **Repository:** realiza o acesso ao banco de dados com Spring Data JPA.
- **Model:** representa as entidades persistidas no banco.
- **DTO:** controla os dados recebidos nas requisições.
- **Exception:** centraliza o tratamento de exceções.
- **Config:** contém configurações adicionais, como a documentação OpenAPI.

## 🔗 Endpoints da API

### Produtos

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/produtos` | Cadastrar produto |
| GET | `/produtos` | Listar produtos |
| GET | `/produtos/{id}` | Buscar produto por ID |
| PUT | `/produtos/{id}` | Atualizar produto |
| DELETE | `/produtos/{id}` | Excluir produto |
| PATCH | `/produtos/{id}/entrada?quantidade={valor}` | Registrar entrada de estoque |
| PATCH | `/produtos/{id}/saida?quantidade={valor}` | Registrar saída de estoque |
| GET | `/produtos/estoque-baixo` | Listar produtos com estoque baixo |

### Movimentações

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/movimentacoes` | Listar todas as movimentações |
| GET | `/movimentacoes/produto/{produtoId}` | Listar movimentações de um produto |

## 📝 Exemplo de cadastro

### Requisição

```http
POST /produtos
```

### JSON

```json
{
  "nome": "Teclado Mecânico",
  "descricao": "Teclado mecânico RGB ABNT2",
  "preco": 249.90,
  "quantidade": 20,
  "estoqueMinimo": 5
}
```

## 📦 Controle de estoque

As alterações de quantidade são realizadas pelos endpoints específicos de entrada e saída.

### Entrada

```http
PATCH /produtos/1/entrada?quantidade=5
```

### Saída

```http
PATCH /produtos/1/saida?quantidade=3
```

A aplicação possui regras para impedir quantidades menores ou iguais a zero e também impede uma saída maior que o estoque disponível.

As operações de entrada e saída geram registros no histórico de movimentações.

## 📊 Estoque baixo

A API permite consultar produtos cuja quantidade atingiu o nível definido como estoque mínimo:

```http
GET /produtos/estoque-baixo
```

Essa funcionalidade auxilia na identificação de produtos que precisam de reposição.

## 📖 Swagger / OpenAPI

A documentação interativa da API está disponível através do Swagger UI.

Com a aplicação em execução, acesse:

```text
http://localhost:8081/swagger-ui/index.html
```

No Swagger é possível visualizar e testar os endpoints da API diretamente pelo navegador.

## 🗄️ Banco de dados

O projeto utiliza **PostgreSQL** para persistência dos dados.

Crie um banco chamado:

```text
stockcontrol_db
```

A configuração da conexão está localizada em:

```text
src/main/resources/application.properties
```

A senha do PostgreSQL pode ser fornecida através da variável de ambiente:

```text
DB_PASSWORD
```

Exemplo de configuração:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/stockcontrol_db
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
```

> ⚠️ Senhas e outras credenciais não devem ser armazenadas diretamente no código ou enviadas ao GitHub.

## ▶️ Como executar

### Pré-requisitos

- Java 21
- PostgreSQL
- Git

### Passos

1. Clone o repositório.
2. Crie o banco `stockcontrol_db` no PostgreSQL.
3. Configure a variável de ambiente `DB_PASSWORD`.
4. Abra o projeto no IntelliJ IDEA ou em outra IDE compatível.
5. Execute a classe `StockcontrolApplication`.

A API será iniciada em:

```text
http://localhost:8081
```

Depois disso, os endpoints podem ser acessados pelo Swagger ou testados utilizando o Postman.

## 🧪 Testes

O projeto possui testes unitários utilizando **JUnit e Mockito**.

Atualmente são verificadas regras como:

- Atualização dos dados de um produto sem alterar indevidamente sua quantidade em estoque.
- Bloqueio de saída quando a quantidade solicitada é superior ao estoque disponível.

Para executar os testes pelo Maven no Windows:

```powershell
.\mvnw.cmd clean test
```

Resultado esperado:

```text
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## 💡 Conceitos praticados

Durante o desenvolvimento foram aplicados conceitos como:

- Programação Orientada a Objetos
- API REST
- Arquitetura em camadas
- CRUD
- DTOs
- Validação de dados
- Regras de negócio
- Persistência com JPA/Hibernate
- Relacionamento entre entidades
- Tratamento de exceções
- Documentação de API
- Testes unitários
- Mocks
- Versionamento com Git

## 👩‍💻 Desenvolvedora

**Raylane Barbosa**

Estudante de **Análise e Desenvolvimento de Sistemas**, com formação complementar em **Desenvolvimento Full Stack** e interesse em desenvolvimento de software e Back-End Java.

Este projeto foi desenvolvido para aplicar os conhecimentos adquiridos durante minha formação e fortalecer meu portfólio na área de desenvolvimento.

---

⭐ Projeto desenvolvido para fins de estudo, prática e portfólio.