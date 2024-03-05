# Raspberry Award Tracking System

Bem-vindo ao repositório do projeto Raspberry Award Tracking System! Este projeto consiste em uma API Spring Boot, desenvolvida para verificar qual o produtor com maior intervalo entre dois premios *Raspberry Award*, confome a especificação presenta na [Documentação](documentacao\Especificação.pdf).

## Tecnologias Utilizadas 🛠️

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

## Utilizando a solução 🚀

Para configurar o ambiente de desenvolvimento, siga os passos abaixo:

1. Clone este repositório para o seu computador:

   ```bash
   git clone https://github.com/GiovaniRizzato/pior-filme-backend.git
   ```
2. Subistitua o arquivo 'src\main\resources\csv\movies.csv\movies.csv' [Aqui](src/main/resources/csv/movies.csv);
3. Rodar a aplicação utilizando a IDE Springboot de sua preferencia ou insira os comandos:

   ```bash
   mvn dependency:resolve
   mvn spring-boot:run
   ```
4. Acesse o Endpoint GET em http://localhost:8080/movies?projection=max-win-interval-for-producers,
