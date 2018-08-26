# Descrição do problema 

```sh
Objetivo do projeto é fornecer uma api para um desenvolvedor frontend 
criar um modelo de domínio sem precisar de um backend
ex: É possível criar tabelas no sgbd, exclui-las, adicionar colunas, data type e outros.
para mais detalhes acesse o tópico 3. Documentção via Rest Doc
```
 
## 1. Dependências

Instalar as seguintes ferramentas:

    1. JDK 1.8
    2. Maven
    3. Docker compose   
    
O projeto utiliza o sgbd (Postgres) .
 
## 2. Executando o Projeto
 IDEA Intellij pode ser recomendado
Após baixar o projeto, para executá-lo é necessário rodar os seguintes comandos dentro da pasta raiz.

```sh
$ docker-compose up   
$ mvn clean install
```

## 3. Documentação via Rest Doc
Acesse a pasta target/generated-docs/index.html
clique em index.html com o botão direito para abrir o browser
e veja a documentação.

## 4. Recomendo o Postman
Teste as rodas via postman descritas na documentação .

## 5. Encerrar o docker
```sh
$ docker-compose down

```