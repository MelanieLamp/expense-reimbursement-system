
# Enterprise Expense Management and Authorization System


A full-stack Java CRUD application featuring strict backend permission validation leveraging JSON Web Tokens (JWT) to securely authenticate users and dynamically grant management privileges for viewing, filtering, approving, and denying pending reimbursement claims.


## <-- Key Features -->


*   **Role-Based Security:** Secure employee portal login using JWT-based security tokens to enforce strict backend permission validation.
  
*   **Dynamic Claim Lifecycle:** Real-time management privileges for authorized managers to filter, view, approve, and deny pending reimbursement claims.
  
*   **Decoupled Architecture:** Clean Separation of Concerns implemented via Model-View-Controller (MVC) architecture and Data Access Objects (DAO).
  


## <-- Tech Stack -->



### Frontend & Client-Side:


*   **HTML5 / CSS3**: Structural layout and responsive user portal styling.
  
*   **JavaScript (ES6+)**: Dynamic UI rendering and asynchronous API consumption.
  


### Backend Core & Frameworks:


*   **Java**: Core programming language utilizing MVC design patterns.
  
*   **Spring Boot**: Enterprise-level application framework and dependency injection.
  
*   **Javalin**: Lightweight REST API routing and service calls.



### Security & Data Layer:


*   **JSON Web Tokens (JWT)**: Stateless user authentication and authorization.
  
*   **PostgreSQL**: Relational database management system for secure data storage.
  
*   **Hibernate**: Object-Relational Mapping (ORM) framework for database communication via DAO.

  

### Dev Tools, Testing & Logging:


*   **Gradle**: Automation build tool and dependency management.
  
*   **JUnit & Mockito**: Unit testing and mock object framework.
  
*   **Log4j**: Application logging, tracking, and debugging.
  
