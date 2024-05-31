# ApiBancoDeSangue

## Descrição
Este é um projeto que fornece uma API para um banco de sangue. A API permite gerenciar doadores, receptores e 
transações de sangue. Com ela, é possível registrar pessoas, buscar pessoas cadastradas, calcular a média do índice de 
massa corporal (IMC) por década de idade, consultar a quantidade de candidatos por estado, calcular a percentagem de 
obesos por sexo, calcular a média de idade por tipo sanguíneo e encontrar quantos doadores estão disponíveis para 
cada tipo de sangue.

## Tecnologias utilizadas
- Jakarta EE com importações de jakarta
- Spring Data JPA
- Spring MVC
- Lombok
- Java SDK versão 21

## Instalação
Para instalar e rodar o projeto, você precisa ter Java 21 instalado. Seguem os passos para a instalação:

```bash
# clonar repositório
git clone https://github.com/alexandreluchetti/ApiBancoDeSangue.git

# entrar na pasta raiz do projeto, abrir o concole e digitar
mvn wrapper:wrapper

# Altere os dados de configurações de banco de dados (nome do banco, usuario e senha) no arquivo application.yml
src/main/resources/application.yml
# jdbcUrl: jdbc:mysql://localhost:3306/banco_de_sangue
# username: root
# password: root
```

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

## Layout web
<img src="/assets/operacoes.png">

## Autor
Alexandre Lucchetta

+55 16 99169-9718

luchetti.92@gmail.com

https://www.linkedin.com/in/alexandreluchetti/
