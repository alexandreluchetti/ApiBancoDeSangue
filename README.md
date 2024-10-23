# ApiBancoDeSangue

---

## Descrição
Este é um projeto que fornece uma API para um banco de sangue. A API permite gerenciar doadores, receptores e 
transações de sangue. Com ela, é possível registrar pessoas, buscar pessoas cadastradas, calcular a média do índice de 
massa corporal (IMC) por década de idade, consultar a quantidade de candidatos por estado, calcular a percentagem de 
obesos por sexo, calcular a média de idade por tipo sanguíneo e encontrar quantos doadores estão disponíveis para 
cada tipo de sangue.

---
## Visão Geral

### Pré-requisitos

- **Java 21** - A versão LTS mais recente do JDK para aproveitar os recursos modernos da linguagem.
- **Spring Framework** - Framework principal para criação da aplicação, fornecendo suporte para configuração automática, segurança, e muito mais.
- **Spring Data JPA**: Abstrai o acesso ao banco de dados, facilitando a manipulação de dados persistidos no MySQL.
- **Maven 3.6+** - Ferramenta de build e gerenciamento de dependências.
- **MySQL** - Banco de dados relacional utilizado pela aplicação.
- **Docker** e **Docker Compose** (opcional) - Para execução da aplicação em contêineres.
- **Swagger/OpenAPI**: Gera automaticamente a documentação da API e fornece uma interface interativa para testes.

---

### Arquitetura

O Mobiauto Server foi desenvolvido seguindo a arquitetura limpa (Clean Archtecture), onde cada componente da aplicação tem responsabilidades claramente definidas:

<div align="center">
    <img src="assets/clean-archtecture.jpg" alt="Clean Archtecture" width="400"/>
</div>

### Componentes da Arquitetura

- **Persistência**: Implementada com Spring Data JPA, utilizando MySQL como banco de dados principal.
- **Documentação**: A API é documentada e testável através do Swagger, integrado com SpringDoc OpenAPI.
- **Testes**: O projeto inclui uma configuração robusta de testes usando JUnit, Mockito, e Spring Boot Test, garantindo que a aplicação funcione conforme esperado.

---

## Instalação
### Execução com Docker

Imagem do projeto: `alexandreluchetti/api-banco-de-sangue:latest`

Altere o host da url de conexão ao banco de dados de `localhost` para `mysql` e execute o comando `docker compose up`;
```bash
# Abra a pasta raiz do projeto no terminal e execute o comando
docker compose up
```
Feito isso, será possível acessar a url `http://localhost:4200` (front-end em Angular) e `http://localhost:8303/swagger-ui.html`
(Swagger - documentação da API).

### Executar o Projeto Manualmente (Alternativa)
Se você preferir rodar o projeto sem Docker, você precisará garantir que todas as dependências estejam instaladas corretamente (Java 21, Maven, MySQL) e então seguir os passos normais de build e execução:

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
Feito isso, será possível acessar a url `http://localhost:4200` (front-end em Angular) e `http://localhost:8303/swagger-ui.html`
(Swagger - documentação da API).

---


## Testes

### Visão Geral
Este projeto inclui uma série de testes de integração focados na camada de controle da aplicação, verificando a interação entre o controller, o serviço e o repositório, bem como a resposta correta aos diferentes cenários de uso da API REST.

---

## Operacoes
```bash
# Para usar a API, a URL base é
http://localhost:8080/v1/bancodesangue

# Use os seguintes endpoints para as operações:

# Registrar pessoas
POST - /pessoas

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
```

### Ferramentas Utilizadas
- **JUnit**: Framework de testes para Java.
- **Spring TestContext Framework**: Integração de testes com o Spring.
- **MockMvc**: Utilizado para testes de controladores Spring MVC.

### Executando os Testes
Para executar os testes, você pode utilizar o Maven ou seu IDE preferido. Aqui estão os comandos para executar os testes via Maven:

```sh
mvn test
```

---
## Imagens
<img src="/assets/operacoes.png">


---

## Suporte e Contato

Canais de Comunicação
- **Alexandre Lucchetta**
- **E-mail**: luchetti.92@gmail.com
- **Celular**: +55 (16) 99169-9718
