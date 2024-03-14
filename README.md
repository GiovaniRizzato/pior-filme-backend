# Raspberry Award Tracking System

Welcome to the Raspberry Award Tracking System project repository! This project consists of a Spring Boot API, developed to check which producer has the longest interval between two *Raspberry Awards*, according to the specification presented in [Documentation (PT-br)](documentacao\Especificação.pdf).

## Technologies Used 🛠️

[![Java](https://img.shields.io/badge/java-%233a75b0.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/pt-BR/)
[![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)

## Using the solution 🚀

1. Clone this repository to your computer:
   ```bash
   git clone https://github.com/GiovaniRizzato/pior-filme-backend.git
   ```

2. If necessary, replace the data in file at 'src\main\resources\csv\movies.csv' [Here](src/main/resources/csv/movies.csv), falloing the format:

3. Run the application using the Springboot IDE of your choice or enter the commands:
   ```bash
   mvn dependency:resolve
   mvn spring-boot:run
   ```

## List of endpoints and Models :clipboard:

| Method  |                       URL                          |                      Response Body                    | Description |
| ------- | ---------------------------------------------------| ----------------------------------------------------- | ----------- |
| `GET`   | `/movies?projection=max-win-interval-for-producers`| [Producer Winning Gap DTO](#producer-winning-gap-dto) | Retrieve the producer with the biggest gap between two awards.|

#### Producer Winning Gap DTO
  ```Typescript
  {
    producer: string,
    interval: string,
    previousWin: number,
    followingWin: number,
  }
  ```
