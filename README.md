🚀 Blog Application API
This is a Blog RESTful API project built with Spring Boot, designed to manage users, categories, posts, and comments.

✨ Key Features
Authentication & Authorization: Implemented using JWT (JSON Web Tokens).

Role-Based Access Control (RBAC): Separate roles for ADMIN and USER.

Data Models: Users, Categories, Posts, and Comments.

Admin Control: ADMIN users can manage Categories and Users.

API Documentation: Swagger UI is included for easy testing and exploration of all API endpoints.

Pagination: Posts retrieval supports pagination for efficient data loading.

⚙️ Technologies Used
Backend: Java 17+ (or 21), Spring Boot 3

Database: MySQL (H2)

Security: Spring Security, JWT (Json Web Token)

Build Tool: Maven (or Gradle)

💻 Local Setup & Running
Follow these steps to set up and run the project on your local machine.

1. Database Configuration
Create a new MySQL Database (e.g., blogapp).

Update the src/main/resources/application.properties (or application.yml) file with your database credentials.

API Documentation (Swagger UI)
You can test and view the complete API documentation using Swagger UI.

Endpoint,Method,Description,Access
/auth/create-user,POST,Register a new user.,Public
/auth/login,POST,Log in and obtain a JWT Token.,Public
/api/users,GET,Retrieve all Users (Supports Pagination).,ADMIN
/api/categories,POST,Create a new Category.,ADMIN
/api/posts?pageNo=0&pageSize=10,GET,Retrieve all Posts with Pagination.,Public
/api/users/{userId}/posts,POST,Create a new Post linked to a User.,USER / ADMIN
/api/posts/{postId}/comments,POST,Add a Comment to a specific Post.,USER / ADMIN

URL: http://localhost:3000/swagger-ui.html
