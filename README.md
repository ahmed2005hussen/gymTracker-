# 🏋️ Hadidy - Gym Tracker Backend

A backend REST API for a Gym Tracking platform built with **Java** and **Spring Boot**. The application helps users manage workout plans, exercises, diet plans, meals, supplements, and personal profiles while providing secure authentication and a clean layered architecture.

---

## 🚀 Features

- 🔐 User Authentication with Spring Security
- 👤 User Profile Management
- 💪 Workout Plans & Workout Days Management
- 🏃 Exercise Management
- 🥗 Diet Plans & Meals Management
- 💊 Supplements Management
- ✅ Request Validation
- ⚠️ Global Exception Handling
- 📦 DTO-based API Responses
- 🗄️ MySQL Database Integration
- 🏛️ Layered Architecture (Controller → Service → Repository)

---

## 🛠️ Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate

### Database

- MySQL

### Build Tool

- Maven

### Tools

- IntelliJ IDEA
- Postman
- Git
- GitHub

---

## 📂 Project Structure

```
src
 ├── config
 ├── controllers
 ├── dto
 ├── entity
 ├── exceptions
 ├── repository
 ├── service
 └── HadidyApplication.java
```

---

## 📚 Main Modules

- User
- Profile
- Workout Plan
- Workout Day
- Exercise
- Diet Plan
- Meal
- Supplement
- Progress Tracking

---

## 🔒 Security

The project uses **Spring Security** to secure application endpoints and manage user authentication.

Current implementation includes:

- User authentication
- Password encryption
- Role-based foundation
- Protected endpoints

---

## 📡 REST APIs

The project exposes REST APIs for managing:

- Users
- Profiles
- Workout Plans
- Workout Days
- Exercises
- Diet Plans
- Meals
- Supplements

---

## 🏗️ Architecture

The application follows a layered architecture:

```
Controller
     ↓
Service
     ↓
Repository
     ↓
Database
```

This structure improves:

- Maintainability
- Scalability
- Testability
- Separation of Concerns

---

## ⚙️ Getting Started

### Clone the repository

```bash
git clone https://github.com/ahmed2005hussen/gymTracker-.git
```

### Navigate to the project

```bash
cd gymTracker-
```

### Configure the database

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gymtracker
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Run the application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

## 📈 Future Improvements

- JWT Authentication
- Role-Based Authorization
- Swagger / OpenAPI Documentation
- Docker Support
- Unit & Integration Tests
- Pagination & Sorting
- Logging
- File Uploads
- Email Verification
- Password Reset

---

## 👨‍💻 Author

Ahmed Hussein

- GitHub: https://github.com/ahmed2005hussen
- LinkedIn: https://linkedin.com/in/ahmed-elsherif-119b60337/

