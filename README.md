# 🏦 Banking Management System

A secure RESTful Banking Management System built with Spring Boot.

This project demonstrates modern backend development practices including JWT Authentication, Spring Security, REST APIs, PostgreSQL, and Hibernate/JPA.

---

## 🚀 Features

- Customer Management
- Account Management
- Deposit Money
- Withdraw Money
- Transfer Money Between Accounts
- JWT Authentication
- Password Encryption (BCrypt)
- Spring Security Authorization
- Global Exception Handling
- Request Validation
- Swagger/OpenAPI Documentation

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven
- Swagger / OpenAPI

---

## 📁 Project Structure

```
src
 ├── config
 ├── controller
 ├── dto
 ├── entity
 ├── exception
 ├── repository
 ├── security
 └── service
```

---

## 🔐 Authentication

The application uses JWT (JSON Web Token) authentication.

Authentication flow:

1. Create a customer.
2. Login using email and password.
3. Receive a JWT token.
4. Authorize in Swagger using the JWT.
5. Access protected endpoints.

---

## 📖 API Endpoints

### Authentication

- POST `/api/auth/login`

### Customers

- POST `/api/customers`

### Accounts

- POST `/api/accounts`
- GET `/api/accounts`
- POST `/api/accounts/{id}/deposit`
- POST `/api/accounts/{id}/withdraw`
- POST `/api/accounts/transfer`

---

## ▶️ Running the Project

### Clone

```bash
git clone https://github.com/gokdenizyildirim020-svg/banking-management-system.git
```

### Configure PostgreSQL

Create a database named:

```
banking_db
```

Update:

```
src/main/resources/application.properties
```

with your PostgreSQL credentials.

### Run

```bash
./mvnw spring-boot:run
```

Open Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📷 Swagger

The API can be tested directly using Swagger UI.

---

## 📌 Future Improvements

- Docker support
- React frontend
- Unit & Integration Tests

---

## 👨‍💻 Author

**Gökdeniz Yıldırım**

Computer Engineering Student
