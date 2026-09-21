# 📦 StockControl

Sistema de controle de estoque desenvolvido como projeto de portfólio, com foco na construção de uma API REST utilizando **Java, Spring Boot e PostgreSQL**.

O projeto permite realizar o gerenciamento de produtos por meio das principais operações de um CRUD: cadastrar, consultar, atualizar e excluir produtos.

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Postman
- Git e GitHub

## ⚙️ Funcionalidades

Atualmente, a API permite:

- Cadastrar produtos
- Listar todos os produtos
- Buscar produto por ID
- Atualizar produtos
- Excluir produtos
- Armazenar os dados no PostgreSQL

## 📁 Estrutura do projeto

```text
src/main/java/com/raylane/stockcontrol
├── controller
│   └── ProdutoController.java
├── model
│   └── Produto.java
├── repository
│   └── ProdutoRepository.java
├── service
│   └── ProdutoService.java
└── StockcontrolApplication.java
```

A aplicação segue uma arquitetura em camadas:

- **Controller:** recebe e trata as requisições HTTP.
- **Service:** concentra a lógica da aplicação.
- **Repository:** realiza o acesso aos dados através do Spring Data JPA.
- **Model:** representa as entidades da aplicação.

## 🔗 Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/produtos` | Cadastrar um produto |
| GET | `/produtos` | Listar todos os produtos |
| GET | `/produtos/{id}` | Buscar produto por ID |
| PUT | `/produtos/{id}` | Atualizar um produto |
| DELETE | `/produtos/{id}` | Excluir um produto |

## 📝 Exemplo de cadastro de produto

### Requisição

```http
POST /produtos
```

### JSON

```json
{
  "nome": "Teclado Mecânico",
  "descricao": "Teclado mecânico RGB",
  "preco": 199.90,
  "quantidade": 20,
  "estoqueMinimo": 5
}
```

## 🗄️ Banco de dados

O projeto utiliza **PostgreSQL** para armazenamento dos dados.

Crie um banco de dados chamado:

```text
stockcontrol_db
```

A configuração da conexão está localizada em:

```text
src/main/resources/application.properties
```

Por segurança, a senha do PostgreSQL é fornecida através da variável de ambiente:

```text
DB_PASSWORD
```

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/stockcontrol_db
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
```

> ⚠️ A senha do banco de dados não deve ser armazenada diretamente no código nem enviada ao GitHub.

## ▶️ Como executar o projeto

1. Clone este repositório.
2. Tenha o Java 21 e o PostgreSQL instalados.
3. Crie o banco de dados `stockcontrol_db`.
4. Configure a variável de ambiente `DB_PASSWORD` com a senha do PostgreSQL.
5. Abra o projeto em uma IDE, como IntelliJ IDEA.
6. Execute a classe `StockcontrolApplication`.

A aplicação será executada em:

```text
http://localhost:8081
```

Os endpoints podem ser testados utilizando o Postman.

## 🧪 Exemplo de teste

Para listar todos os produtos:

```http
GET /produtos
```

Para consultar um produto específico:

```http
GET /produtos/1
```

## 🔮 Próximas melhorias

O projeto continuará sendo desenvolvido com:

- Validação dos dados dos produtos
- Categorias de produtos
- Entrada e saída de estoque
- Histórico de movimentações
- Alerta de estoque mínimo
- Tratamento global de exceções
- Documentação com Swagger/OpenAPI
- Testes automatizados
- Front-end para gerenciamento do estoque

## 👩‍💻 Desenvolvedora

**Raylane Barbosa**

Estudante de **Análise e Desenvolvimento de Sistemas**, com formação complementar em **Desenvolvimento Full Stack** e interesse em desenvolvimento de software, Back-End Java e construção de aplicações web.

---

⭐ Projeto desenvolvido para fins de estudo, prática e portfólio.