# Wallet Service Application

## Overview:

The Wallet Service Application allows users to create a personal wallet and perform operations with it.
The operations which the wallet currently supports are **DEPOSIT** and **WITHDRAW** as well as an option for the user to check their balance and account movements.

## Technologies used:

- Java 17
- Spring Boot 3.4.2
- Spring Data JPA
- Spring Web
- Spring Validation
- Spring Security
- Lombok
- H2
- PostgreSQL
- Docker

## Instructions to start the application:

1. You will need a PostgreSQL Server in order to access the database and Docker to run the server in a container.
2. By using the "docker compose up -d" command in the terminal from the project a PostgreSQL Server should start in a Docker container and create the "wallet_db" database.
3. Run the application through the `WalletServiceApplication.class`.

## Instructions to test the application:

1. You will need Postman in order to send requests to the application and test the endpoints functionality.
2. Below are example requests for each endpoint.

### 1. Create Wallet Endpoint

-  curl --location 'http://localhost:8080/wallet/create' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "fullName" : "Full Name",
   "email": "fullName@gmail.com",
   "password": "P@ssword123",
   "confirmPassword": "P@ssword123"
   }'

### 2. View Balance Endpoint

- curl --location 'http://localhost:8080/wallet/balance?email=fullName%40gmail.com'

### 3. Operation Endpoint

#### Withdraw Operation

- curl --location 'http://localhost:8080/wallet/operation' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "email" : "fullName@gmail.com",
  "name" : "WIHDRAW",
  "sum" : 100.50
  }'

#### Deposit Operation

- curl --location 'http://localhost:8080/wallet/operation' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "email" : "fullName@gmail.com",
  "name" : "DEPOSIT",
  "sum" : 200
  }'

## Developer Notes:

- The View Balance Endpoint provides history for the operations ("Deposit", "Withdraw") which the user made as well as the remaining balance inside the wallet.
- There are input validations implemented for each endpoint which return a custom error response if the input provided by the user is incorrect.
- Currently, the Spring Security configuration permits all requests without authentication and is mainly used for the `BCryptPasswordEncoder.class`, however for future development user accounts should be implemented and all operations regarding the wallets should be authenticated.
