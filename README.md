# Sporting Events Portal 
_Portal for criation and dissemination of Sporting Events_

## Features

- Login
- Register/Update/Remove/List User
- Register/Update/Remove/List event
- Link one or more users to an event

## Tech

- Java 8
- Spring Boot 2.6.3
- Lombok 1.18.22
- ModelMapper 2.4.5
- Hibernate 5.6.5
- Maven 4.0.0
- Swagger v3
- PostgreSQL 42.3.1
- Log4j

## Installation

To run it, just have java 8 installed on the machine, and follow the steps below.

1. Import the project as "maven project" in the IDE of choice.
2. Click on the project and select the option to update dependencies.
3. Run as "Java Aplication"

You can choose to run the script with the database's DDL in the script folder or uncomment the "spring.jpa.hibernate.ddl-auto=create-drop" configuration present in application.properties.

## Tests

To run the tests, just access the test files in the package "src/test/java/" and run with "JUnit Test".

## Swagger

To access the api documentation, just run the project and access '/portal-docs/swagger-ui/index.html'.

## Logs

File where logs will be saved "/Logs/portal-sports-event.log".