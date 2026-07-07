# 🚀 FinTradeX - Paper Trading Platform Backend

A **fintech-inspired paper trading platform backend** built using **Spring Boot** and **PostgreSQL** that simulates a real stock trading application. Users can register, authenticate using JWT, trade stocks with virtual money, track their portfolio, and view transaction history while stock prices are periodically updated using a real market data API.

This project focuses on **backend engineering**, **transaction consistency**, **database design**, **REST APIs**, **Spring Security**, and **financial business logic**.

---

## 📌 Features

### 🔐 Authentication & Security

* User Registration
* User Login
* JWT Authentication
* BCrypt Password Encryption
* Stateless Session Management using Spring Security
* Protected REST APIs

### 📈 Stock Management

* View all available stocks
* View stock details
* Add new stocks dynamically
* Real-time stock price updates from external market data API
* Scheduled stock price synchronization

### 💹 Trading Engine

* Buy stocks using virtual balance
* Sell owned stocks
* Automatic balance updates
* Portfolio updates
* Transaction-safe operations using `@Transactional`

### 📊 Portfolio

* View current holdings
* Average buy price calculation
* Current market value
* Total portfolio value (Cash + Holdings)

### 📜 Trade History

* Complete transaction history
* Buy/Sell records
* Executed price
* Quantity
* Timestamp

### ⚙️ Additional Features

* Global Exception Handling
* Request Validation
* Layered Architecture
* DTO Pattern
* Repository Pattern
* RESTful API Design
* Swagger API Documentation

---

# 🛠️ Tech Stack

| Category          | Technology                  |
| ----------------- | --------------------------- |
| Language          | Java 25                     |
| Framework         | Spring Boot                 |
| Security          | Spring Security, JWT        |
| ORM               | Spring Data JPA (Hibernate) |
| Database          | PostgreSQL                  |
| Build Tool        | Maven                       |
| API Documentation | Swagger                     |
| Scheduler         | Spring Scheduling           |
| Market Data       | Twelve Data API             |
| Utilities         | Lombok                      |

---

# 🏗️ Project Architecture

```
Client
   │
   ▼
Spring Security (JWT Authentication)
   │
   ▼
Controllers
   │
   ▼
Services
   │
   ▼
Repositories
   │
   ▼
PostgreSQL Database

                 ▲
                 │
          Scheduler
                 │
                 ▼
       Twelve Data API
```

---

# 🗂️ Project Structure

```
FinTradeX
│
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── scheduler
├── security
├── service
│
├── pom.xml
└── README.md
```

---

# 🗄️ Database Design

### Users

| Field     |
| --------- |
| userId    |
| name      |
| email     |
| password  |
| balance   |
| createdAt |

---

### Stocks

| Field        |
| ------------ |
| stockId      |
| symbol       |
| compName     |
| price        |
| lastUpdated  |

---

### Portfolio

| Field           |
| --------------- |
| portId          |
| user            |
| stock           |
| quantity        |
| avgBuyPrice     |

---

### Trade

| Field         |
| ------------- |
| tradeId       |
| user          |
| stock         |
| tradeType     |
| quantity      |
| exePrice      |
| time          |

---

# 🔗 Entity Relationships

```
Users
  │
  │ 1
  │
  │ *
Portfolio
  │ *
  │
  │ 1
Stocks

Users
  │
  │ 1
  │
  │ *
Trade
  │ *
  │
  │ 1
Stocks
```

---

# 🔄 Trading Workflow

## Buy Stock

```
User Request
      │
      ▼
Authenticate JWT
      │
      ▼
Validate User
      │
      ▼
Validate Stock
      │
      ▼
Check Balance
      │
      ▼
Deduct Balance
      │
      ▼
Update Portfolio
      │
      ▼
Create Trade Record
      │
      ▼
Commit Transaction
```

---

## Sell Stock

```
User Request
      │
      ▼
Authenticate JWT
      │
      ▼
Validate Portfolio
      │
      ▼
Validate Quantity
      │
      ▼
Add Balance
      │
      ▼
Update Portfolio
      │
      ▼
Create Trade Record
      │
      ▼
Commit Transaction
```

---

# 📡 API Endpoints

## Authentication

| Method | Endpoint    | Description           |
| ------ | ----------- | --------------------- |
| POST   | `/register` | Register a new user   |
| POST   | `/login`    | Login and receive JWT |

---

## Stocks

| Method | Endpoint           | Description       |
| ------ | ------------------ | ----------------- |
| GET    | `/stocks`          | Get all stocks    |
| GET    | `/stocks/{symbol}` | Get stock details |
| POST   | `/stocks/add`      | Add a new stock   |

---

## Trading

| Method | Endpoint         | Description        |
| ------ | ---------------- | ------------------ |
| POST   | `/trade/buy`     | Buy stock          |
| POST   | `/trade/sell`    | Sell stock         |
| GET    | `/trade/history` | View trade history |

---

## Portfolio

| Method | Endpoint           | Description           |
| ------ | ------------------ | --------------------- |
| GET    | `/portfolio`       | View current holdings |
| GET    | `/portfolio/value` | View portfolio value  |

---

# 🔐 Authentication

Protected APIs require a JWT token.

Example header:

```http
Authorization: Bearer <your-jwt-token>
```

Login returns:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

# ⚙️ Running the Project

## Prerequisites

* Java 25
* Maven
* PostgreSQL
* Git

---

## Clone Repository

```bash
git clone https://github.com/NikhilKulkani/FinTradeX.git
cd FinTradeX
```

---

## Configure Environment Variables

Set the following values before running the application:

```
DB_URL
DB_USERNAME
DB_PASSWORD

JWT_SECRET

TWELVE_DATA_KEY
```

---

## Run

```bash
mvn spring-boot:run
```

Application starts at:

```
http://localhost:8080
```

Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

---

# 📷 Screenshots

Add screenshots here after deployment.

* Login
* Swagger UI
* Buy Stock
* Portfolio
* Trade History

---

# 🚀 Future Enhancements

* Limit Orders
* Watchlist
* Realized & Unrealized P&L
* Portfolio Allocation Analytics
* Admin Dashboard
* Audit Logging
* Optimistic Locking
* Docker Support
* CI/CD Pipeline
* Unit & Integration Testing
* Email Notifications

---

# 🎯 Key Backend Concepts Demonstrated

* Spring Boot REST APIs
* Spring Security & JWT Authentication
* Password Encryption using BCrypt
* Layered Architecture
* DTO Pattern
* Repository Pattern
* Transaction Management (`@Transactional`)
* Hibernate/JPA Entity Relationships
* Scheduler Jobs
* External API Integration
* Exception Handling
* Request Validation
* Financial Business Logic

---

# 👨‍💻 Author

**Nikhil Kulkarni**

Backend Developer | Java | Spring Boot | DSA

Feel free to connect and provide feedback or suggestions for improvement.
