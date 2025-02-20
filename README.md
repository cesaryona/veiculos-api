# Veículos API

É uma API para gerenciamento de informações sobre veículos.

## Funcionalidades
- **Operações CRUD** para veículos.
- **Coleção Postman** para facilitar os testes de endpoints.

## Tecnologias
- **Java 21** com **Spring Boot**.
- **JPA** para interação com o banco de dados.
- **Liquibase** para controle de versionamento do banco de dados
- **PostgreSQL** como banco de dados.
- **Docker** e **Docker Compose** para deployment.

## Setup

### Rodando Localmente
1. Clone o repositório:
   ```bash
   git clone https://github.com/cesaryona/veiculos-api.git
   cd veiculos-api

2. Execute o docker-compose
   ```bash
   docker-compose up --build

3. Importar as coleções do postman e as variáveis de ambiente para realizar as chamadas nos endpoints da aplicação