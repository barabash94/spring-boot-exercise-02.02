# GitHub Repository API

REST API built with Spring Boot for retrieving GitHub repositories and branch information for a given user.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen)
![OpenFeign](https://img.shields.io/badge/Spring%20Cloud-OpenFeign-blue)

## Features

* Retrieve repositories for a GitHub user
* Exclude forked repositories
* Retrieve repository branches and latest commit SHA
* Integration with GitHub REST API using OpenFeign
* Centralized exception handling
* Request validation
* JSON error responses

## Tech Stack

* **Java 17**
* **Spring Boot 4.1.0**
* **Spring Web MVC**
* **Spring Cloud OpenFeign**
* **Spring Validation**
* **Gradle**
* **Lombok**

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

## API

### Get user repositories

```http
GET /api/{userName}
```

Example:

```http
GET /api/octocat
Accept: application/json
```

Response:

```json
[
  {
    "name": "Hello-World",
    "owner": "octocat",
    "branches": [
      {
        "name": "main",
        "commit": {
          "sha": "abc123..."
        }
      }
    ]
  }
]
```

## Error Handling

The application uses `@RestControllerAdvice` for centralized exception handling.

**User not found**

```http
HTTP 404 Not Found
```

```json
{
  "status": 404,
  "message": "User not found"
}
```

**HTML not supported**

When the client requests an HTML response:

```http
Accept: text/html
```

the API returns:

```http
HTTP 406 Not Acceptable
```

```json
{
  "status": 406,
  "message": "HTML is not supported"
}
```

## Configuration

`application.properties`:

```properties
spring.application.name=spring-boot-exercise
gitHubApi.url=https://api.github.com
```

## Running the Application

### Requirements

* Java 17+
* Git

### Run with Gradle

**Windows:**

```bash
gradlew.bat bootRun
```

**Linux/macOS:**

```bash
./gradlew bootRun
```

The application will be available at:

```text
http://localhost:8080
```

## Project Structure

```text
src/main/java/com/bara/spring_boot_exercise
├── controller
├── service
├── client
├── model
└── exception
```

## Author

**Mateusz Bara**
