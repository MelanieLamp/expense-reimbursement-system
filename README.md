# Enterprise Expense Management & Authorization System

A secure, role-based full-stack Java CRUD application that enables employees to submit expense reimbursement requests and empowers authorized managers to view, filter, approve, or deny pending claims. 

## Key Features

* **Role-Based Access Control:** Strict backend permission validation using JSON Web Tokens (JWT) to dynamically grant manager and employee privileges.
  
* **Claim Lifecycle Management:** Streamlined system for viewing, filtering, approving, and denying pending reimbursement claims.
  
* **Secure Portal Authentication:** Protected portal login to ensure data confidentiality and user accountability.

## Tech Stack & Architecture

### Backend Core

* **Framework:** Spring Boot (Java)
* **API Routing:** Javalin
* **Architecture Pattern:** Model-View-Controller (MVC) with Data Access Object (DAO) layers

### Security & Data

* **Authentication:** JSON Web Tokens (JWT)
* **Database:** PostgreSQL
* **ORM / Persistence:** Hibernate

### Dev Tools & Ecosystem

* **Build Automation:** Gradle
* **Testing Suite:** JUnit, Mockito
* **Logging & Diagnostics:** Log4j
