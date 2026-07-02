# Ticket Management REST API

A simple REST API built using **Java** and **Spring Boot** for managing support tickets. The application uses an in-memory `ConcurrentHashMap` as its data store, so no database setup is required.

---

## Features

* Create, Read, Update and Delete (CRUD) tickets
* Filter tickets by status and priority
* Input validation
* Global exception handling
* In-memory data storage
* Swagger UI for API testing

---

## Tech Stack

* Java 17+
* Spring Boot
* Spring Web
* Spring Validation
* Maven
* ConcurrentHashMap (In-Memory Storage)
* Swagger (SpringDoc OpenAPI)

---

## Project Structure

```text
src
├── controller
├── service
├── repository
├── model
├── dto
├── exception
└── TicketManagementApplication.java
```

---

## Prerequisites

* Java 17 or later
* Maven 3.8+
* IntelliJ IDEA (recommended)

---

## Running the Application

### Clone the repository

```bash
git clone <repository-url>
cd ticket-management
```

### Build the project

```bash
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run
```

Or run `TicketManagementApplication.java` directly from your IDE.

The application starts on:

```text
http://localhost:8080
```

---

## Swagger UI

After starting the application, open:

```text
http://localhost:8080/swagger-ui/index.html
```

You can use Swagger to test all available endpoints.

---

# API Endpoints

## Create Ticket

**POST** `/tickets`

Request

```json
{
  "title": "Payment Failure",
  "description": "Payment gateway returns HTTP 500",
  "priority": "HIGH"
}
```

Response

```json
{
  "id": 1,
  "title": "Payment Failure",
  "description": "Payment gateway returns HTTP 500",
  "status": "OPEN",
  "priority": "HIGH"
}
```

---

## Get All Tickets

**GET** `/tickets`

Example

```text
GET http://localhost:8080/tickets
```

---

## Get Ticket by ID

**GET** `/tickets/{id}`

Example

```text
GET http://localhost:8080/tickets/1
```

---

## Update Ticket

**PUT** `/tickets/{id}`

Request

```json
{
  "title": "Payment Failure",
  "description": "Issue resolved after service restart",
  "status": "RESOLVED",
  "priority": "LOW"
}
```

---

## Delete Ticket

**DELETE** `/tickets/{id}`

Example

```text
DELETE http://localhost:8080/tickets/1
```

---

## Filter by Status

```text
GET /tickets?status=OPEN
```

---

## Filter by Priority

```text
GET /tickets?priority=HIGH
```

---

## Filter by Status and Priority

```text
GET /tickets?status=OPEN&priority=HIGH
```

---

# Ticket Status Values

* OPEN
* IN_PROGRESS
* RESOLVED
* CLOSED

---

# Ticket Priority Values

* LOW
* MEDIUM
* HIGH
* CRITICAL

---

# Validation

The following validations are implemented:

* Title cannot be blank
* Description cannot be blank
* Priority is required
* Status is required when updating a ticket

---

# Error Handling

The API returns appropriate HTTP status codes.

| Status Code | Description           |
| ----------- | --------------------- |
| 200         | Success               |
| 201         | Ticket Created        |
| 204         | Ticket Deleted        |
| 400         | Invalid Request       |
| 404         | Ticket Not Found      |
| 500         | Internal Server Error |

---

# Storage

This application uses an in-memory `ConcurrentHashMap` to store tickets.

Data is **not persisted** and will be cleared whenever the application is restarted.

---

## Author

Developed as a Spring Boot coding assignment demonstrating REST API design, validation, exception handling, and in-memory data management.
