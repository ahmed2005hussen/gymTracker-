# Hadidy

Hadidy is a fitness-management application for organizing workouts, nutrition, supplements, and personal fitness information. It includes a Spring Boot REST API backed by MySQL and a lightweight HTML/CSS/JavaScript dashboard in [`Front/`](Front/).

## Features

### Account and security

- Register users with username and password validation (minimum eight-character password).
- Prevent duplicate usernames.
- Change the authenticated user's password after verifying the current password.
- Store passwords with Spring Security's delegating password encoder.
- Authenticate protected API endpoints with HTTP Basic authentication.
- Return structured errors for validation failures, duplicate usernames, incorrect passwords, and missing users.
- CORS support for local frontend development servers (`localhost:3000`, `localhost:5173`, and port `5500`).

### Profile management

- Retrieve the signed-in user's profile.
- Update name, email, goal, profile picture, subscription dates, gym price, height, and weight.
- Calculate BMI automatically when both height and weight are available.

### Workout planning

- Create, view, edit, and delete personal workout plans.
- Add workout days to a plan, including image, description, expected duration, total exercises, and total repetitions.
- View, edit, and remove individual workout days.
- Add exercises to workout days with sets, repetitions, description, and picture.
- View, edit, and remove exercises.
- Enforce ownership checks so users can manage only their own workout plans, days, and exercises.

### Diet and meals

- Create, list, view, edit, and delete personal diet plans.
- Add meals to diet plans with recipe, photo, meal time, calories, protein, carbohydrates, and fats.
- View, edit, and delete meals.
- Restrict diet plans and meals to their owner.

### Supplement tracking

- Create, list, view, edit, and delete personal supplements.
- Store a supplement's name, description, price, and picture.

### Frontend prototype

The static dashboard provides pages for onboarding, login/register, workout plans, diet plans, supplements, profile settings, a progress log, and community posts. The progress, community, and dashboard interactions currently use browser `localStorage`; they are not yet connected to API endpoints.

## Tech stack

- Java 25 and Spring Boot 4
- Spring Web MVC, Spring Data JPA, Spring Security, and Bean Validation
- MySQL (runtime) with H2 included as an alternative runtime dependency
- Lombok
- springdoc OpenAPI / Swagger UI
- Maven Wrapper
- HTML, CSS, and vanilla JavaScript frontend

## Getting started

### Prerequisites

- JDK 25
- MySQL running locally or accessible over the network

### Configure the database

The application reads these environment variables, with the shown defaults:

| Variable | Default |
| --- | --- |
| `DATABASE_HOST` | `localhost` |
| `DATABASE_PORT` | `3306` |
| `DATABASE_NAME` | `hadidy` |
| `DATABASE_USERNAME` | `root` |
| `DATABASE_PASSWORD` | `root` |

Create a MySQL database named `hadidy`, or set the variables to match an existing database. Hibernate is configured with `ddl-auto=update`, so it manages the schema on startup.

### Run the API

```bash
./mvnw spring-boot:run
```

On Windows:

```bat
mvnw.cmd spring-boot:run
```

Run the tests with:

```bash
./mvnw test
```

When running locally, the API is normally available at `http://localhost:8080`.

### API documentation

- Swagger UI: `http://localhost:8080/ahmed-ui.html`
- OpenAPI JSON: `http://localhost:8080/my-api-docs`

### Open the frontend

Serve the [`Front/`](Front/) directory with a static web server, such as the VS Code Live Server extension, then open `index.html`. The configured CORS policy supports common Live Server ports.

## API overview

Except for `OPTIONS` requests and the configured registration matcher, the API requires HTTP Basic authentication. Send credentials with each protected request, for example:

```bash
curl -u username:password http://localhost:8080/api/workoutplan/list
```

> **Note:** The registration controller is mapped to `/api/users/register`, while the current security configuration permits `/api/user/register`. Align these paths before using unauthenticated registration in a deployed environment.

| Area | Endpoints |
| --- | --- |
| Users | `POST /api/users/register`, `PATCH /api/users/me/password` |
| Profile | `GET /api/profile/getprofile`, `PATCH /api/profile/editprofile` |
| Workout plans | `POST /api/workoutplan/create`, `GET /api/workoutplan/list`, `GET /api/workoutplan/list/{id}`, `PATCH /api/workoutplan/edit/{id}`, `DELETE /api/workoutplan/delete/{id}` |
| Workout days | `POST /api/workoutday/create`, `GET /api/workoutday/list/{workoutPlanId}`, `GET /api/workoutday/list/{workoutPlanId}/{workoutDayId}`, `PATCH /api/workoutday/edit/{workoutPlanId}/{workoutDayId}`, `DELETE /api/workoutday/delete/{workoutPlanId}/{workoutDayId}` |
| Exercises | `POST /api/exercise/create`, `GET /api/exercise/list/{workoutPlanId}/{workoutDayId}`, `GET /api/exercise/list/{workoutPlanId}/{workoutDayId}/{exerciseId}`, `PATCH /api/exercise/edit/{workoutPlanId}/{workoutDayId}/{exerciseId}`, `DELETE /api/exercise/delete/{workoutPlanId}/{workoutDayId}/{exerciseId}` |
| Diet plans | `POST /api/dietplan/create`, `GET /api/dietplan/list`, `GET /api/dietplan/list/{id}`, `PATCH /api/dietplan/edit/{id}`, `DELETE /api/dietplan/delete/{id}` |
| Meals | `POST /api/meal/create`, `GET /api/meal/list/{dietPlanId}`, `GET /api/meal/list/{dietPlanId}/{mealId}`, `PATCH /api/meal/edit/{dietPlanId}/{mealId}`, `DELETE /api/meal/delete/{dietPlanId}/{mealId}` |
| Supplements | `POST /api/supplement/create`, `GET /api/supplement/list`, `GET /api/supplement/list/{id}`, `PATCH /api/supplement/edit/{id}`, `DELETE /api/supplement/delete/{id}` |

## Project structure

```text
src/main/java/com/ahmed/Hadidy/
├── config/          # Security and user-details configuration
├── controllers/     # REST endpoints
├── dto/             # Request and response models
├── entity/          # JPA entities
├── exceptions/      # Domain exceptions and global error handling
├── repository/      # Spring Data repositories
└── service/         # User and fitness-domain services
Front/               # Static dashboard and onboarding pages
src/test/            # Application, controller, and service tests
```
