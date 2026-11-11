# API REST - Gerenciamento de Chamados

## Sobre o projeto

API REST desenvolvida em Java e Spring Boot
para gerenciamento de chamados de suporte técnico.

A aplicação permite cadastrar, consultar e filtrar
chamados, além de identificar situações prioritárias
e apresentar um resumo dos atendimentos.

## Tecnologias utilizadas

- Java
- Spring Boot
- Maven
- JSON
- Coleções Java

## Funcionalidades

- Listar todos os chamados.
- Consultar chamado por ID.
- Filtrar chamados por setor.
- Filtrar chamados por prioridade.
- Buscar chamados pelo título.
- Listar chamados críticos.
- Consultar resumo por status.
- Cadastrar novos chamados.

## Estrutura da aplicação

- Model: representa os dados dos chamados.
- Controller: recebe e processa as requisições HTTP.
- Enums: definem os valores permitidos para
  prioridade e status.

## Análise técnica

### Como o Spring Boot recebe e processa requisições HTTP?

O Spring Boot utiliza o Spring MVC para receber
e direcionar as requisições HTTP aos métodos
correspondentes do Controller.

As anotações como @GetMapping e @PostMapping
definem quais métodos serão executados de acordo
com o endereço e o tipo da requisição.

### Qual é a função do Controller?

O Controller é responsável por receber as
requisições do cliente, capturar os parâmetros
enviados, executar as operações solicitadas
e retornar as respostas HTTP.

Nesta aplicação, ele também realiza diretamente
as operações sobre a coleção de chamados,
pois o projeto não utiliza uma camada Service
ou Repository.

### Como ocorre a conversão entre objetos Java e JSON?

O Spring Boot utiliza a biblioteca Jackson para
realizar a conversão automática entre objetos
Java e JSON.

Quando uma requisição POST recebe um JSON,
o Jackson converte seu conteúdo em um objeto
Java por meio do processo de desserialização.

Quando o Controller retorna um objeto Java,
o Jackson realiza a serialização, transformando
esse objeto em JSON para a resposta HTTP.

### Por que os chamados não permanecem armazenados após reiniciar a aplicação?

Os chamados são armazenados em uma coleção
Java na memória RAM da aplicação.

Como não existe um banco de dados ou outro
mecanismo de persistência, os dados são perdidos
quando a aplicação é encerrada.

Ao iniciar novamente, a aplicação carrega
os dez chamados fictícios definidos no código.

## Como executar

1. Clone o repositório.
2. Abra o projeto na sua IDE.
3. Execute a aplicação Spring Boot.
4. Acesse http://localhost:8080/chamados
   para consultar os chamados.

## Integrante

Eva Raposo
