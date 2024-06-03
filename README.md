# ApiBancoDeSangue



## Descrição

Imagem do projeto: `docker pull alexandreluchetti/api-banco-de-sangue:1.0.0`

Este é um projeto que fornece uma API para um banco de sangue. A API permite gerenciar doadores, receptores e 
transações de sangue. Com ela, é possível registrar pessoas, buscar pessoas cadastradas, calcular a média do índice de 
massa corporal (IMC) por década de idade, consultar a quantidade de candidatos por estado, calcular a percentagem de 
obesos por sexo, calcular a média de idade por tipo sanguíneo e encontrar quantos doadores estão disponíveis para 
cada tipo de sangue.



## Tecnologias utilizadas
- Jakarta EE com importações de jakarta
- Java SDK versão 21
- Spring Framework
- Spring Security
- Spring Data JPA
- Spring Boot
- Spring MVC
- Swagger
- Lombok
- Docker



## Instalação
### Docker

Altere o host da url de conexão ao banco de dados de `localhost` para `mysql` e execute o comando `docker compose up`;
```bash
# ApiBancoDeSangue/src/main/resources/application.yml 
jdbcUrl: jdbc:mysql://mysql:3306/banco_de_sangue

# Abra a pasta raiz do projeto no terminal e execute o comando
docker compose up
```
Feito isso, será possível acessar a url `http://localhost:4200` (front-end em Angular) e `http://localhost:8080/swagger-ui.html`
(Swagger - documentação da API).



### Local
Para instalar e rodar o projeto localmente, você precisa ter Java 21 instalado. Seguem os passos para a instalação:

```bash
# clonar repositório
git clone https://github.com/alexandreluchetti/ApiBancoDeSangue.git

# entrar na pasta raiz do projeto, abrir o concole e digitar
mvn wrapper:wrapper

# Crie a estrutura de banco de dados de acordo com o arquivo .sql na raiz do projeto
# Se preferir, importe o arquivo no MySQL Workbench
ApiBancoDeSangue/banco_de_sangue_sql.sql

# Altere os dados de configurações de banco de dados (nome do banco, usuario e senha) no arquivo application.yml
# jdbcUrl: jdbc:mysql://localhost:3306/banco_de_sangue
# username: root
# password: root
ApiBancoDeSangue/src/main/resources/application.yml
```
Feito isso, será possível acessar a url `http://localhost:4200` (front-end em Angular) e `http://localhost:8080/swagger-ui.html`
(Swagger - documentação da API).


## Operacoes
```bash
# Para usar a API, a URL base é
http://localhost:8080/v1/bancodesangue

# Use os seguintes endpoints para as operações:
# Buscar todas as pessoas cadastradas no banco de dados
GET - /pessoas

# Buscar IMC médio em cada faixa de idade de dez em dez anos: 0 a 10; 11 a 20; 21 a 30, etc.
GET - /media/imc/decada

# Buscar a quantidade de candidatos cadastrados em cada estado do Brasil
GET - /pessoas/estados

# Buscar o percentual de obesos entre os homens e entre as mulheres
GET - /percentual/obesos/sexo

# Buscar a média de idade para cada tipo sanguíneo
GET - /media/idade/tiposanguineo

# Buscar a quantidade de possíveis doadores para cada tipo sanguíneo receptor
GET - /quantidade/doadores/tiposanguineo/receptor

# Registrar pessoas
POST - /envia/pessoas
```


## Swagger
<img src="/assets/operacoes.png">


## Autor
Alexandre Lucchetta

+55 16 99169-9718

luchetti.92@gmail.com

https://www.linkedin.com/in/alexandreluchetti/
