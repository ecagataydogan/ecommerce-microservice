## Table of Contents
1. [About This Project](#About-This-Project)
1. [Technologies Used](#technologies-used)
2. [Architecture](#architecture)
3. [API Documentation](#api-documentation)
4. [Testing](#testing)

## About This Project

I developed this project to experience microservice architecture and get hands-on practice. My goal was to create a basic e-commerce API.

> “Programming isn't about what you know; it's about what you can figure out.” – Chris Pine

## Technologies Used

This project leverages the following technologies:

- **Java 21**: The core programming language.
- **Spring Boot**: Framework for building microservices.
- **Spring Data JPA**: For database interaction and ORM.
- **Spring Security**: To secure the application.
- **MongoDB**: NoSQL database for flexible data storage.
- **PostgreSQL**: Relational database for structured data.
- **Kafka**: Message broker for event-driven communication.
- **Zipkin**: Distributed tracing for monitoring and debugging.
- **Docker**: For containerization and seamless deployment.

## Architecture

The architecture of this project is built around microservices. Below is an overview of the key components and their roles:

![Architecture Diagram](path/to/your/image.png) <!-- Replace with your actual image path -->

### Components

- **Gateway**:  
  Acts as the entry point for all external requests. It routes the requests to the appropriate microservices and handles tasks such as authentication and logging.

- **Config Server**:  
  Manages the centralized configuration for all microservices, ensuring consistent and synchronized configurations across the architecture.

- **Discovery Server (Eureka)**:  
  Facilitates service discovery, enabling microservices to dynamically locate each other and communicate seamlessly.

### Core Microservices

- **Auth Service**:  
  Handles user-related operations, such as registration, authentication.

- **Customer Service**:  
  Manages customer onboarding and customer information.

- **Order Service**:  
  Handles the creation and management of orders, including payment integration.

- **Payment Service**:  
  Sends payment-related information to the Notification Service. Note: Payment processing implementation is not included.

- **Product Service**:  
  Manages products and inventory control.

- **Notification Service**:  
  Sends emails to users for order confirmations and payment confirmations.

### Event-Driven Communication

The microservices communicate asynchronously using **Apache Kafka** as the message broker. Kafka ensures reliable, scalable, and decoupled event-driven messaging between services, such as notifications for order and payment events.

### Observability Tools

- **Zipkin**: Enables distributed tracing to monitor the flow of requests through different microservices.
- **Docker**: Ensures consistent and scalable deployments for each microservice.

This architecture is modular, scalable, and built to demonstrate the core principles of microservice design.

## API Documentation

### Register a New User

**Endpoint:**  
`POST http://localhost:8080/api/v1/auth/register`

**Description:**  
Registers a new user with the provided email and password.

---

**Request Body:**
```json
{
    "email": "ecagataydgn@gmail.com",
    "password": "123456"
}
```

**Success Response:**
Status Code: 200 

**Error Response:**
Status Code: 409
```json
{
  "timestamp": "2024-12-03T23:56:16.882046+03:00",
  "status": 409,
  "error": "account_already_exists",
  "message": "User already exists"
}
```

---

### Login

**Endpoint:**  
`POST http://localhost:8080/api/v1/auth/login`

**Description:**  
Login user with the provided email and password.

---

**Request Body:**
```json
{
    "email": "ecagataydogan@gmail.com",
    "password": "123456"
}
```

**Success Response:**
Status Code: 409
```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzMzMjU3NjQ0LCJleHAiOjE3MzY4NTc2NDR9.zXzI_xDIC1aHVr97aE9E4XIacPEm-7ZXqpaJe2kcYP8CN--86hv5bPFDROeTSqrpkqyYfBa4QnzCrnPaKqu24g",
  "refreshToken": "5365a37f-0ea0-4ee4-bc10-24559219c647",
  "id": 1,
  "identifier": "ecagataydgn@gmail.com"
}
```

**Error Response:**
Status Code: 403

---

### Onboard

**Endpoint:**  
`POST http://localhost:8080/api/v1/customers/onboard`

**Description:**  
Onboard user, creates a customer.

---

**Headers:**
- `Authorization: Bearer <JWT_TOKEN>`

**Request Body:**
```json
{
  "firstName": "esref",
  "lastName":"dogan",
  "email":"ecagataydgn@gmail.com",
  "address": {
    "street":"Yeni",
    "zipCode":"42550",
    "houseNumber":"239H"
  }
}
```

**Success Response:**
Status Code: 200


**Error Response:**
Status Code: 409
```json
{
"timestamp": "2024-12-03T23:09:13.521254+03:00",
"status": 409,
"error": "account_already_exists",
"message": "Customer already exists"
}
```


### Get Products

**Endpoint:**  
`GET http://localhost:8080/api/v1/products`

**Description:**  
Get all products with available quantity, price, category etc.

---

**Headers:**
- `Authorization: Bearer <JWT_TOKEN>`

**Request Body:**
null


**Success Response:**
Status Code: 200
```json
[
  {
    "id": 1,
    "name": "Mechanical Keyboard 1",
    "description": "Mechanical keyboard with RGB lighting",
    "availableQuantity": 10.0,
    "price": 99.99,
    "category": {
      "id": 1,
      "name": "Keyboards",
      "description": "Computer Keyboards"
    }
  },
  {
    "id": 2,
    "name": "Wireless Compact Keyboard 1",
    "description": "Wireless compact keyboard",
    "availableQuantity": 15.0,
    "price": 79.99,
    "category": {
      "id": 1,
      "name": "Keyboards",
      "description": "Computer Keyboards"
    }
  },
  {
    "id": 3,
    "name": "Gaming Keyboard 1",
    "description": "Backlit gaming keyboard with customizable keys",
    "availableQuantity": 20.0,
    "price": 129.99,
    "category": {
      "id": 1,
      "name": "Keyboards",
      "description": "Computer Keyboards"
    }
  },
  {
    "id": 4,
    "name": "Ergonomic Keyboard 1",
    "description": "Mechanical keyboard with wrist rest",
    "availableQuantity": 25.0,
    "price": 109.99,
    "category": {
      "id": 1,
      "name": "Keyboards",
      "description": "Computer Keyboards"
    }
  }
]
```

### Create Order

**Endpoint:**  
`POST http://localhost:8080/api/v1/products`

**Description:**  
Get all products with available quantity, price, category etc.

---

**Headers:**
- `Authorization: Bearer <JWT_TOKEN>`

**Request Body:**
```json
{
  "paymentMethod": "BITCOIN",
  "products": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 2,
      "quantity": 1
    }
  ]
}
```

**Success Response:**
Status Code: 200

