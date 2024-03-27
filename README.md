# Raspberry Award Tracking System

Welcome to the Raspberry Award Tracking System project repository! This project consists of a Spring Boot API, developed to check which producer has the longest interval between two *Raspberry Awards*, according to the specification presented in [Documentation (pt-BR)](documentacao\Especificação.pdf).

## Technologies Used 🛠️

[![Java](https://img.shields.io/badge/java-%233a75b0.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/pt-BR/)
[![SpringBoot](https://img.shields.io/badge/spring%20boot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/)
[![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)
[![HsqlDB](https://img.shields.io/badge/hsqldb-2f648a?style=for-the-badge)](https://www.hsqldb.org/)

## Using the solution :rocket:

1. Clone this repository to your computer:
   ```bash
   git clone https://github.com/GiovaniRizzato/pior-filme-backend.git
   ```

2. If necessary, replace the data in file at [src\main\resources\csv\movielist.csv](src/main/resources/csv/movielist.csv), following the format, as the exemple:
   | year | title | studios | producers | winner |
   | ---- | ----- | ------- | --------- | ------ |
   | 1980 | Can't Stop the Music | Associated Film Distribution | Allan Carr | yes |
   | 1980 | Cruising | Lorimar Productions, United Artists | Jerry Weintraub | |
   | 1981 | The Formula | MGM and United Artists | Steve Shagan | |

3. Run the application using the Springboot IDE of your choice or enter the commands:
   ```bash
   mvn dependency:resolve
   mvn spring-boot:run
   ```

## Using the solution :heavy_check_mark:

1. Clone this repository to your computer:
   ```bash
   git clone https://github.com/GiovaniRizzato/pior-filme-backend.git
   ```

2. Run all the integration tests at once using the commands:
   ```bash
   mvn dependency:resolve
   mvn test
   ```

*Note.: The data for testing is stored at [src\test\resources\csv\movielist.csv](src\test\resources\csv\movielist.csv), if that is changed the integration testing is going to break.

## List of endpoints and Models :clipboard:

| Method  |                         URL                            |                      Response Body                    |
| ------- | -------------------------------------------------------| ----------------------------------------------------- |
| `GET`   | `/movies`| [Movie DTO](#movie-dto) |
| `GET`   | `/movies?projection=min-max-win-interval-for-producers`| [Producer Winning Gap DTO](#producer-winning-gap-dto) |

#### Movie DTO
   ```TypeScript
   {
      movies: Movie[],
   };

   ProducerWinning = { 
      id: number ,
      year: number,
      title: string,
      winner: boolean,
      studios: string[],
      producers: string[],
   };
   ```

#### Producer Winning Gap DTO
   ```TypeScript
   {
      min: ProducerWinning[],
      min: ProducerWinning[],
   };

   ProducerWinning = {
      producer: string,
      interval: string,
      previousWin: number,
      followingWin: number,
   };
   ```
