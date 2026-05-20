# Secure Banking Transaction System

## Overview

Secure Banking Transaction System is a Java Spring Boot application designed to manage secure banking operations such as user authentication, account management, money transfers, and transaction auditing.

The project uses JWT Authentication, Spring Security, MySQL Database, and REST APIs to provide a secure backend banking system.

---

# Features

* User Registration & Login
* JWT Authentication & Authorization
* Role-Based Security
* Secure Account Creation
* Money Transfer Between Accounts
* Transaction History
* Audit Logging
* Exception Handling
* MySQL Database Integration

---

# Tech Stack

## Backend

* Java 17
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate
* JWT Authentication
* Maven

## Database

* MySQL
  
---

# Project Structure

```bash
SecureBankingTransactionSystem
│
├── src/main/java/com/bankingsystem
│   ├── config
│   ├── controller
│   ├── dto
│   │   ├── request
│   │   └── response
│   ├── entity
│   ├── exception
│   ├── repository
│   ├── security
│   ├── service
│   │   └── impl
│   └── util
│
├── src/main/resources
│   └── application.properties
│
├── pom.xml
└── README.md
```

---

# Main Modules

## Authentication Module

Handles:

* User Registration
* User Login
* JWT Token Generation
* Authorization

### Important Files

* `AuthController.java`
* `AuthService.java`
* `JwtUtil.java`
* `JwtAuthenticationFilter.java`
* `SecurityConfig.java`

---

## Account Module

Handles:

* Account Creation
* Account Details
* Balance Management

### Important Files

* `AccountController.java`
* `AccountService.java`
* `Account.java`

---

## Transaction Module

Handles:

* Fund Transfer
* Transaction Validation
* Transaction History

### Important Files

* `TransactionController.java`
* `TransactionService.java`
* `Transaction.java`

---

## Audit Module

Handles:

* Activity Logging
* Transaction Auditing
* Security Monitoring

### Important Files

* `AuditController.java`
* `AuditService.java`
* `AuditLog.java`

---

# Database Configuration

Update your `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

---

# API Endpoints

## Authentication APIs

| Method | Endpoint             | Description   |
| ------ | -------------------- | ------------- |
| POST   | `/api/auth/register` | Register User |
| POST   | `/api/auth/login`    | Login User    |

---

## Account APIs

| Method | Endpoint               | Description         |
| ------ | ---------------------- | ------------------- |
| POST   | `/api/accounts/create` | Create Account      |
| GET    | `/api/accounts/{id}`   | Get Account Details |

---

## Transaction APIs

| Method | Endpoint                                | Description         |
| ------ | --------------------------------------- | ------------------- |
| POST   | `/api/transactions/transfer`            | Transfer Money      |
| GET    | `/api/transactions/history/{accountId}` | Transaction History |

---

## Audit APIs

| Method | Endpoint          | Description    |
| ------ | ----------------- | -------------- |
| GET    | `/api/audit/logs` | Get Audit Logs |

```bash
http://localhost:8080/swagger-ui/index.html
```

---

# How To Run The Project

## Step 1: Clone Repository

```bash
git clone https://github.com/your-username/SecureBankingTransactionSystem.git
```

---

## Step 2: Open Project

Open the project in:

* IntelliJ IDEA
* Eclipse

---

## Step 3: Configure Database

Create MySQL database:

```sql
CREATE DATABASE banking_db;
```

Update credentials inside `application.properties`.

---

## Step 4: Run Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run:

```bash
SecureBankingTransactionSystemApplication.java
```

---

# Sample Request Payloads

## Register User

```json
{
  "name": "Nikhil",
  "email": "nikhil@gmail.com",
  "password": "password123"
}
```

---

## Login User

```json
{
  "email": "nikhil@gmail.com",
  "password": "password123"
}
```

---

## Transfer Money

```json
{
  "fromAccount": "1001001001",
  "toAccount": "2002002002",
  "amount": 5000
}
```

---

# Security Features

* JWT Token Authentication
* Password Encryption
* Stateless Authentication
* Request Authorization
* Exception Handling
* Secure Transaction Processing

---

# Future Enhancements

* Email Notifications
* OTP Verification
* Admin Dashboard
* Docker Deployment
* Kubernetes Support
* Microservices Architecture

---

# License

This project is for educational and learning purposes.
