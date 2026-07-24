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

- Java 25
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

## 👤 Profile, User, and Supplement design

The Profile and Supplement modules use request/response DTOs, validation, service-layer ownership checks, and authenticated-user lookup. This keeps HTTP concerns in controllers and business rules in services.

### What was completed

- A profile is created together with every new user. `User` and `Profile` are linked on both sides, and the user save cascades to its profile.
- The `profile.user_id` column is required and unique, enforcing one profile per user.
- Profile updates use `PATCH /api/users/me/profile`. All request fields are optional, so omitted fields are preserved. Weight and height must be positive, gym price cannot be negative, and email is validated.
- BMI is recalculated whenever valid height and weight are available.
- Subscription dates are checked after applying an update; an end date before the start date returns `400 Bad Request`.
- Supplement creation uses `CreateSupplementRequest`, where `name` and `price` are required. Supplement updates use `SupplementRequest`, where every field is optional for PATCH.
- Every Supplement query is scoped to the authenticated user's **profile ID**, not the user ID. This prevents accessing another user's supplements and works even when user and profile IDs differ.
- Supplement write operations are transactional, and every supplement must belong to a profile.

### Relevant endpoints

| Method | Endpoint | Purpose |
| --- | --- | --- |
| `GET` | `/api/users/me/profile` | Return the authenticated user's profile. |
| `PATCH` | `/api/users/me/profile` | Partially update the authenticated user's profile. |
| `POST` | `/api/supplement/create` | Create a supplement for the authenticated user. |
| `GET` | `/api/supplement/list` | List the authenticated user's supplements. |
| `GET` | `/api/supplement/list/{id}` | Return one owned supplement. |
| `PATCH` | `/api/supplement/edit/{id}` | Partially update one owned supplement. |
| `DELETE` | `/api/supplement/delete/{id}` | Delete one owned supplement. |

Example partial supplement update:

```json
{
  "price": 500.0
}
```

Only `price` changes; name, description, and picture remain unchanged.

---

## 🧪 Tests

Focused unit tests cover the User, Profile, and Supplement service behavior:

- User registration encodes the password and creates a linked profile.
- Profile updates preserve omitted fields, calculate BMI, and reject invalid subscription dates.
- Supplement creation assigns the authenticated user's profile.
- Supplement list, read, edit, and delete operations use the profile ID for ownership checks.
- Supplement PATCH preserves omitted fields.

Run all tests with:

```bash
mvn test
```

The Profile/User/Supplement tests print a readable result in the console:

```text
[PASS] editSupplement_preservesFieldsOmittedFromPatch()
[FAIL] listSupplement_queriesUsingProfileIdNotUserId() - expected: <...> but was: <...>
```

Maven/Surefire also produces the standard detailed report under `target/surefire-reports`.

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
