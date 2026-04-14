# 🚀 Enterprise Blog Engine - RESTful Web API

A feature-rich, secure backend solution for modern blogging platforms. Built with **Spring Boot 3**, this API handles complex relationships between users, posts, categories, and comments, all while maintaining high security through **JWT** and **RBAC**.

---

## 🎯 Project Overview
This project focuses on building a robust backend ecosystem that follows REST principles. It emphasizes security and efficiency, implementing professional patterns like **Pagination**, **Global Exception Handling**, and **Data Transfer Objects (DTOs)**.

## 🛠 Tech Stack
* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Security:** Spring Security & JWT (JSON Web Token)
* **Database:** MySQL (Production) / H2 (Testing)
* **API Documentation:** Swagger UI / OpenAPI 3
* **Build Tool:** Maven

## ✨ Core Features

### 🔐 Security & Access Control
* **JWT Authentication:** Stateless security implementation for secure client-server communication.
* **RBAC (Role-Based Access Control):** Granular access levels for `ADMIN` (Full Control) and `USER` (Content Creator) roles.

### 📝 Content Management
* **Post & Comment System:** Dynamic interaction between users and content with relational data integrity.
* **Category Mapping:** Logical grouping of posts for better discoverability.
* **Optimized Retrieval:** Server-side **Pagination and Sorting** for high-performance data loading.

### 🛠 Administrative Tools
* **User Management:** Admin-exclusive endpoints to monitor and manage platform users.
* **Category Control:** Centralized management of blog categories.

## 🔗 Key API Endpoints (Quick Reference)

| Category | Endpoint | Method | Access |
| :--- | :--- | :--- | :--- |
| **Auth** | `/auth/create-user` | `POST` | Public |
| **Auth** | `/auth/login` | `POST` | Public |
| **Users** | `/api/users` | `GET` | **ADMIN** |
| **Posts** | `/api/posts?pageNo=0&pageSize=10` | `GET` | Public |
| **Posts** | `/api/users/{userId}/posts` | `POST` | User/Admin |
| **Comments** | `/api/posts/{postId}/comments` | `POST` | User/Admin |

---

## 🚀 Getting Started

### 1. Database Setup
Create a MySQL database named `blogapp` and update your `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogapp
spring.datasource.username=your_username
spring.datasource.password=your_password
