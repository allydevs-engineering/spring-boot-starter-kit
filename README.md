# Spring Boot Starter Kit

[![CI](https://github.com/allydevs-engineering/spring-boot-starter-kit/actions/workflows/ci.yml/badge.svg)](https://github.com/allydevs-engineering/spring-boot-starter-kit/actions/workflows/ci.yml)

A practical Spring Boot foundation for production-oriented backend services.

The project establishes a small, deliberate engineering baseline around API development, validation, error handling, health checks, testing, and continuous integration.

It is intentionally not a full enterprise boilerplate.

## What is included

- Spring Boot
- Java 21
- Maven Wrapper
- Type-safe application configuration
- REST API foundation
- Request validation
- RFC 9457-style `ProblemDetail` error responses
- Global validation error handling
- Actuator health endpoints
- Liveness and readiness probes
- Application and build information
- Graceful shutdown
- JUnit 5
- Spring Boot Test
- MockMvc API tests
- Spotless + Google Java Format
- GitHub Actions CI
- Dependabot configuration

## Project structure

```text
src/
├── main/
│   ├── java/com/allydevs/starter/
│   │   ├── StarterApplication.java
│   │   ├── config/
│   │   │   └── ApplicationProperties.java
│   │   ├── exception/
│   │   │   └── ApiExceptionHandler.java
│   │   └── web/
│   │       ├── ApiController.java
│   │       ├── ApiResponse.java
│   │       ├── EchoRequest.java
│   │       └── EchoResponse.java
│   │
│   └── resources/
│       └── application.yml
│
└── test/
    └── java/com/allydevs/starter/
        ├── StarterApplicationTests.java
        └── web/
            └── ApiControllerTests.java
```

## Getting started

### Prerequisites

- Java 21
- Git

Maven does not need to be installed separately because the project includes the Maven Wrapper.

## Clone

```bash
git clone https://github.com/allydevs-engineering/spring-boot-starter-kit.git

cd spring-boot-starter-kit
```

## Run tests

```bash
./mvnw test
```

The application starts on:

```text
http://localhost:8080
```

## API

### Application information

```http
GET /api/v1
```

Example response:

```json
{
    "name": "Spring Boot Starter Kit",
    "version": "0.1.0",
    "status": "UP"
}
```

### Echo example

```http
POST /api/v1/echo
Content-Type: application/json
```

Request:

```json
{
    "message": "Hello AllyDevs"
}
```

Response:

```json
{
    "message": "Hello AllyDevs"
}
```

The endpoint exists primarily to demonstrate request validation and API error handling.

### Validation

Requests can use Jakarta Bean Validation annotations.

For example:

```java
@NotBlank
@Size(max = 500)
String message
```

Invalid requests return a structured `ProblemDetail` response.

Example:

```json
{
    "type": "about:blank",
    "title": "Invalid request",
    "status": 400,
    "detail": "Request validation failed",
    "errors": {
        "message": "message must not be blank"
    }
}
```

### Health endpoints

Spring Boot Actuator provides operational health endpoints.

### General health

```text
GET /actuator/health
```

### Liveness

```text
GET /actuator/health/liveness
```

### Readiness

```text
GET /actuator/health/readiness
```

### Application information

```text
GET /actuator/info
```

These endpoints provide a foundation for containerized and orchestrated deployments without coupling the starter to a specific cloud provider or platform.

## Configuration

Application-owned configuration is represented through type-safe configuration properties.

Current configuration:

```yaml
application:
    name: Spring Boot Starter Kit
    version: 0.1.0
```

Environment-specific configuration can be supplied through Spring Boot's externalized configuration mechanisms.

## Code quality

The project uses Spotless with Google Java Format.

Format the project:

```bash
./mvnw spotless:apply
```

Check formatting:

```bash
./mvnw spotless:check
```

Run the complete verification:

```bash
./mvnw verify
```

## Testing

The project uses:

- JUnit 5
- Spring Boot Test
- MockMvc

The current tests verify:

- Application context startup
- Application information endpoint
- Valid API requests
- Invalid API requests
- Validation error responses

Run:

```bash
./mvnw test
```

## Continuous integration

Every push to `main` and pull request targeting `main` runs the CI workflow.

The workflow verifies:

```text
Checkout
   ↓
Java 21
   ↓
Maven verify
   ↓
Spotless check
```

The repository should remain green before changes are merged.

## What is intentionally not included

- This starter deliberately does not include:
- Database
- JPA
- ORM configuration
- Liquibase
- Flyway
- Authentication
- Authorization
- Messaging
- Redis
- External service clients
- Docker
- Kubernetes manifests
- Cloud-provider configuration
- Business/domain logic

These concerns should be introduced when the application actually requires them.

Adding every possible enterprise technology to a starter makes the foundation harder to understand and harder to adapt.

## Design principles

### Start small

The starter establishes boundaries without creating empty architectural layers.

### Configuration should be explicit

Application-owned settings use typed configuration properties rather than scattered configuration access.

### API contracts should be predictable

Validation failures use a consistent problem-details structure.

### Operational concerns belong in the foundation

Health checks, graceful shutdown, and build information are established before application complexity grows.

### Tests should verify behavior

Tests focus on observable API behavior and application startup rather than implementation details.

### CI should enforce the baseline

The same project should be easy to verify locally and automatically checked on GitHub.

## Contributing

See [CONTRIBUTING.md](./CONTRIBUTING.md) for development and contribution guidelines.

## Security

See [SECURITY.md](SECURITY.md) for information about reporting security vulnerabilities.

## License

This project is licensed under the MIT License. See [LICENSE](./LICENSE).

## Maintained by

AllyDevs Engineering

Engineering capability for digital agencies.

[https://allydevs.com](https://allydevs.com)
