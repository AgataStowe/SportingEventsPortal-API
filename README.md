# API Sporting Events Portal 
_API of portal for criation and dissemination of Sporting Events_

## Features

- Login
- Register/Update/Remove/List User
- Register/Update/Remove/List event
- Link one or more users to an event

## Endpoints

- GET - /auth
- POST - /api/user/save
- PUT - /api/user/update/{id}
- GET - /api/user/list
- GET - /api/user/find/{id}
- DELETE - /api/user/remove/{id}
- POST - /api/event/save
- PUT - /api/event/update/{id}
- GET - /api/event/list
- GET - /api/event/find/{id}
- DELETE - /api/event/remove/{id}
- POST - /api/event/link/user/{userId}

## Tech

- [Java] - 8
- Spring Boot 2.6.3
- [Lombok] - 1.18.22
- ModelMapper 2.4.5
- Hibernate 5.6.5
- [Maven] - 3.3.9
- Swagger v3
- [PostgreSQL]- 14.2
- PgAdmin 4
- Log4j

## Installation

To run it, follow the steps below.

1. Install Java 8 on the machine;
2. Install lombok on the IDE of choice, you can follow these steps: [Lombok installation];
3. Import the project as "maven project" in the IDE of choice;
4. Click on the project and select the option to update dependencies;
5. Install the PostgreSQL or download an image in docker (version 14.2);
6. Install PgAdmin 4;
7. Create a database called 'EVENT_PORTAL';
8. You can choose to run the script with the database's DDL in the script folder or uncomment the "spring.jpa.hibernate.ddl-auto=create-drop" configuration present in src/main/resources/application.properties.
9. Run as "Java Aplication"

## How to use

1. Create a user
2. Authenticate the user
3. Create an event
4. Link one or more users to event

## Tests

To run the tests, just access the test files in the package "src/test/java/" and run with "JUnit Test".

## Swagger

To access the api documentation, just run the project and access '/portal-docs/swagger-ui/index.html'.

## Logs

File where logs will be saved "/Logs/portal-sports-event.log".

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job.)

   [Java]: <https://www.java.com/pt-BR/download/ie_manual.jsp?locale=pt_BR>
   [Lombok]: <https://projectlombok.org/download>
   [Maven]: <https://archive.apache.org/dist/maven/maven-3/3.3.9/binaries/>
   [PostgreSQL]: <https://www.postgresql.org/ftp/source/v14.2/>
   [Lombok installation]: <https://projectlombok.org/setup/overview>
