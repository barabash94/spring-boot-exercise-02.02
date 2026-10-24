# GitHub Repository API

REST API built with Spring Boot for retrieving GitHub repositories and branch information, with PostgreSQL persistence.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen)
![OpenFeign](https://img.shields.io/badge/OpenFeign-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue)

## Features

* Retrieve repositories for a GitHub user
* Exclude forked repositories
* Retrieve branches and latest commit SHA
* Persist repositories in PostgreSQL
* CRUD operations with pagination
* Integration with GitHub REST API using OpenFeign
* Centralized exception handling and request validation

## Tech Stack

* Java 17
* Spring Boot 4.1.0
* Spring Web MVC
* Spring Cloud OpenFeign
* Spring Data JPA / Hibernate
* PostgreSQL
* Docker Compose
* Gradle
* Lombok

## Architecture

```text
Client
   ↓
Controller
   ↓
Service
   ↓
OpenFeign Client
   ↓
GitHub REST API
```

For persistence:

```text
GitHub API
   ↓
GithubRepositoryService
   ↓
GithubRepoMapper
   ↓
Repo Entity
   ↓
RepoRepository
   ↓
PostgreSQL
```

## API

### Get GitHub repositories

```http
GET /api/{userName}
```

Example:

```http
GET /api/octocat
Accept: application/json
```

### Save GitHub repositories

```http
POST /api/repos/github/{userName}
```

### Repository CRUD

```text
GET    /api/repos
GET    /api/repos/{id}
POST   /api/repos
PUT    /api/repos/{id}
DELETE /api/repos/{id}
```

Pagination example:

```http
GET /api/repos?page=0&size=10
```

## Database

The application uses PostgreSQL running in Docker.

Database configuration:

```text
Host: localhost
Port: 54321
Database: postgres
Username: user
Password: admin
```

The `repo` table is created using `init.sql`.

## Running the Application

Start PostgreSQL:

```bash
docker compose up -d
```

Run the application:

**Windows**

```bash
gradlew.bat bootRun
```

**Linux/macOS**

```bash
./gradlew bootRun
```

The application will be available at:

```text
http://localhost:8080
```

## Error Handling

The application uses `@RestControllerAdvice` for centralized exception handling.

Examples:

* `404 Not Found` – GitHub user or repository not found
* `406 Not Acceptable` – HTML responses are not supported
* `400 Bad Request` – invalid request parameters

## Author

**Mateusz Bara**
