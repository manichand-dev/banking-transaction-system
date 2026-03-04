# 💳 Banking Transaction System

A Spring Boot REST API for managing bank accounts and transactions.

---

## 🚀 Features

- Create new bank account
- Deposit money
- Withdraw money with balance validation
- View account details
- View transaction history
- Global exception handling
- Layered architecture implementation

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- REST APIs

---

## 🏗 Project Architecture

Controller Layer → Handles API requests  
Service Layer → Business logic  
Repository Layer → Database operations  
Entity Layer → Account & Transaction models  
Exception Layer → Centralized error handling  

---

## 📌 API Endpoints

### Create Account
POST `/api/accounts`

### Deposit
POST `/api/accounts/{id}/deposit`

### Withdraw
POST `/api/accounts/{id}/withdraw`

### Get Account Details
GET `/api/accounts/{id}`

### Get Transaction History
GET `/api/accounts/{id}/transactions`

---

## ▶ How to Run the Project

1. Clone the repository
2. Configure MySQL in `application.properties`
3. Run the Spring Boot application
4. Test APIs using Postman

---

## 📷 Project Screenshots

### 🏗 Project Structure
![Project Structure](screenshots/account.png)

### ▶ Application Running
![Application Running](screenshots/transactions-api-response.png)

### 💰 Deposit API
![Deposit API](screenshots/deposit-api-response.png)

### 💸 Withdraw API
![Withdraw API](screenshots/withdraw-api-response.png)

### 🗄 Accounts Table (MySQL)
![Accounts Table](screenshots/database-table1.png)

### 🧾 Transaction Table (MySQL)
![Transaction Table](screenshots/database-table2.png)

## 👨‍💻 Author

Manichand  
Java Backend Developer
