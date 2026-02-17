# API de Tarefas

Esta é uma API REST desenvolvida em Java com Spring Boot para gerenciamento de tarefas, utilizando PostgreSQL como banco de dados. O projeto foi criado com foco em aprendizado de backend moderno e boas práticas, incluindo CRUD completo, filtros dinâmicos, ordenação, busca por texto, uso de ENUM no banco, PATCH parcial e integração com JPA/Hibernate.

## Tecnologias utilizadas

Java 21, Spring Boot, Spring Data JPA, Hibernate, PostgreSQL e Maven.

## Estrutura do projeto

O projeto segue uma organização em camadas, contendo controller (responsável pelos endpoints HTTP), model (entidades e enums), repository (acesso ao banco com Spring Data JPA) e a classe principal da aplicação. A estrutura principal fica dentro do pacote `com.tarefas.api_banco`.

## Banco de dados

O banco utiliza um tipo ENUM para prioridade e uma tabela de tarefas com UUID como chave primária.

```sql
CREATE TYPE prioridade_tarefa AS ENUM ('BAIXA', 'MEDIA', 'ALTA');

CREATE TABLE tarefas (
    id UUID PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    concluido BOOLEAN NOT NULL,
    prioridade prioridade_tarefa NOT NULL
);
```

## Configuração da aplicação

No arquivo `application.properties`, configure a conexão com o PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tarefas_db
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Como executar

Para iniciar a aplicação, execute no terminal:

```
mvn spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

## Endpoints disponíveis

Criar tarefa: `POST /tarefas`
Listar tarefas: `GET /tarefas`
Atualizar tarefa: `PUT /tarefas/{id}`
Atualizar status (toggle): `PATCH /tarefas/{id}/concluido`
Deletar tarefa: `DELETE /tarefas/{id}`

### Exemplo de criação (JSON)

```json
{
  "titulo": "Estudar",
  "descricao": "Spring Boot",
  "concluido": false,
  "prioridade": "ALTA"
}
```

## Filtros disponíveis

A API permite filtros via parâmetros de query:

* `GET /tarefas?concluido=false` → apenas pendentes
* `GET /tarefas?prioridade=ALTA` → apenas prioridade alta
* `GET /tarefas?titulo=est` → busca por título (contém texto)
* `GET /tarefas?concluido=false&prioridade=ALTA` → filtros combinados

## Exemplos de uso (PowerShell)

Criar tarefa:

```powershell
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/tarefas" -ContentType "application/json" -Body '{"titulo":"Teste","descricao":"ok","concluido":false,"prioridade":"MEDIA"}'
```

Listar tarefas:

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/tarefas"
```

## Funcionalidades implementadas

CRUD completo, ordenação por status, filtros dinâmicos, busca por texto (LIKE), enum mapeado no PostgreSQL, PATCH parcial e tratamento de erros com retorno 404.

## Próximas melhorias

Paginação, ordenação dinâmica, validação com Bean Validation, uso de DTOs para separar entidade da API e documentação com Swagger/OpenAPI.

## Autor

Desenvolvido por Marcelo Masaharu como projeto de estudo e portfólio backend.
