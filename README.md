# Expense Management and Authorization System

A secure, full-stack Java CRUD application that leverages JSON Web Tokens (JWT) to enforce strict backend permission validation and role-based access control. The platform features an employee portal and a dynamic manager dashboard designed to view, filter, approve, and deny pending financial reimbursement claims.

## 🚀 Key Features

* **JWT Stateless Authentication:** Implements secure user login states with signed JSON Web Tokens to eliminate server-side session overhead.
* **Role-Based Authorization:** Validates user permissions directly on the backend to dynamically grant administrative rights to managers.
* **Dynamic Claim Lifecycle:** Enables real-time capabilities to filter, evaluate, approve, or deny pending employee expense reimbursements.
* **Separation of Concerns:** Built upon a clean Model-View-Controller (MVC) architecture backed by isolated Data Access Objects (DAO).

## 🛠️ Tech Stack

### Frontend & Client-Side
* **HTML5 / CSS3:** Structure and responsive styling optimized for employee portal interfaces.
* **JavaScript (ES6+):** Dynamic interface manipulation and asynchronous API handling for seamless claims processing.

### Backend Core & Architecture

| Technology | Role |
| :--- | :--- |
| **Java** | Core enterprise programming language driving the business domain logic. |
| **Spring Boot** | Application framework managing core dependency injection. |
| **Javalin** | Lightweight, high-performance web framework handling REST API routing and service endpoints. |
| **Hibernate** | Enterprise Object-Relational Mapping (ORM) framework bridging the database and DAO layer. |

### Security, Data & DevOps
* **JSON Web Tokens (JWT):** Secure, stateless tokens used to handle authorization claims securely across layers.
* **PostgreSQL:** Scalable relational database management system storing employee profile ledgers and claim histories.
* **Gradle:** Automation build tool ensuring predictable dependency trees and application packaging.
* **JUnit & Mockito:** Comprehensive test suite mapping core code paths, mocking layers, and asserting business rules.
* **Log4j:** Active application logging used for real-time audit trailing, transaction tracking, and debugging.

## ⚙️ Getting Started

### Prerequisites
* **Java Development Kit (JDK):** Version 11 or higher.
* **PostgreSQL:** An active local or cloud instance with database user access.

### Installation & Run
1. **Clone the repository:**
   ```bash
   git clone https://github.com
   cd expense-management-system
   ```
2. **Compile and build:**
   ```bash
   ./gradlew build
   ```
3. **Launch the application server:**
   ```bash
   ./gradlew bootRun
   ```

## 📜 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

  
