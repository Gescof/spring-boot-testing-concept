# Spring Boot Testing Concept

A comprehensive example project demonstrating clean architecture and testing best practices in a Spring Boot application. This project showcases:

- Layered architecture (Controller → Service → Repository)
- RESTful API design
- Comprehensive test coverage (Unit, and Integration tests)
- Global exception handling
- Clean code practices with Java Records
- JUnit 5 and Mockito for testing

## Features

- **RESTful API** with endpoints for managing example resources
- **Clean Architecture** with clear separation of concerns
- **Global Exception Handling** with consistent error responses
- **Comprehensive Test Suite** including:
  - Unit tests for service layer
  - Integration tests for controllers using `@WebMvcTest`
  - Component tests for service layer with repository integration
- **Modern Java Features**:
  - Java Records for immutable data
  - Stream API for data processing
  - Constructor-based dependency injection
- **Logging** with SLF4J

## Project Structure

```
src/
├── main/
│   ├── java/com/github/gescof/springboottestingconcept/
│   │   ├── controllers/         # REST controllers with API endpoints
│   │   │   └── ExampleController.java
│   │   │
│   │   ├── exceptions/          # Custom exceptions and error handling
│   │   │   ├── ErrorMessage.java
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   └── NotFoundException.java
│   │   │
│   │   ├── models/              # Data transfer objects
│   │   │   ├── Example.java
│   │   │   ├── GetExampleResponse.java
│   │   │   └── GetExamplesResponse.java
│   │   │
│   │   ├── persistence/         # Data access layer
│   │   │   ├── ExampleEntity.java
│   │   │   └── ExampleRepository.java
│   │   │
│   │   └── services/            # Business logic
│   │       ├── ExampleService.java
│   │       └── ExampleServiceImpl.java
│   │
│   └── resources/
│       └── application.yml      # Application configuration
│
└── test/
    └── java/com/github/gescof/springboottestingconcept/
        ├── controllers/          # Integration tests for controllers
        │   └── ExampleControllerIT.java
        │
        ├── exceptions/          # Unit tests for exception handling
        │   └── GlobalExceptionHandlerTest.java
        │
        ├── persistence/         # Integration tests for repositories
        │   └── ExampleRepositoryTest.java
        │
        └── services/            # Unit and integration tests for services
            ├── ExampleServiceImplIT.java
            └── ExampleServiceImplTest.java
```

## Testing Strategy

### 1. Controller Layer Tests (`@WebMvcTest`)
- **Isolation**: Tests controllers in isolation with mocked services
- **Verification**: Validates HTTP status codes, response bodies, and error handling
- **Coverage**:
  - Success scenarios
  - Error scenarios (e.g., resource not found)
  - Request validation
  - Response structure

### 2. Service Layer Tests
- **Unit Tests**:
  - Test business logic in isolation
  - Mock external dependencies (repositories)
  - Verify interactions with dependencies
  - Test edge cases and error conditions

- **Integration Tests**:
  - Test service integration with real repository
  - Verify data transformation and business rules
  - Test transaction management

### 3. Exception Handling
- **Global Exception Handler**:
  - Consistent error responses
  - Proper HTTP status codes
  - Meaningful error messages
- **Custom Exceptions**:
  - `NotFoundException` for 404 scenarios
  - Extensible for other error types
- **Test Coverage**:
  - Exception translation to error responses
  - Error message formatting
  - HTTP status code mapping

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6.3 or higher
- Spring Boot 3.5.3 or higher
- An IDE (IntelliJ IDEA, Eclipse, or VS Code with Java extensions)

### Building the Project

1. Clone the repository
2. Build the project:
   ```bash
   mvn clean install
   ```

### Running Tests

Run all tests:
```bash
mvn test
```

Run specific test class:
```bash
mvn test -Dtest=ExampleControllerIT
```

### Running the Application

1. Start the application:
   ```bash
   mvn spring-boot:run
   ```
2. The application will be available at `http://localhost:8080`
3. Explore the API documentation at `http://localhost:8080/swagger-ui.html` (if Swagger is configured)

### API Endpoints

#### Get All Examples
```
GET /examples
```

**Example Response (200 OK):**
```json
{
  "examples": [
    {
      "id": 1,
      "name": "Example 1"
    },
    {
      "id": 2,
      "name": "Example 2"
    }
  ]
}
```

#### Get Example by ID
```
GET /examples/{id}
```

**Success Response (200 OK):**
```json
{
  "id": 1,
  "name": "Example 1"
}
```

**Error Response (404 Not Found):**
```json
{
  "httpStatus": "NOT_FOUND",
  "message": "Example with id 999 not found"
}
```

## Dependencies

### Core
- **Spring Boot 3.x** - Application framework
- **Spring Data JPA** - Data access
- **H2 Database** - In-memory database for development and testing

### Testing
- **JUnit 5** - Testing framework
- **Mockito** - Mocking framework
- **AssertJ** - Fluent assertions
- **Spring Test** - Testing utilities
- **Spring Boot Test** - Integration testing support

### Development Tools
- **Spring Boot DevTools** - Developer tools
- **Spring Boot Actuator** - Application monitoring
- **Spring Configuration Processor** - Configuration metadata

## Contributing

Feel free to submit issues and enhancement requests.

## License

This project is open-source and available under the [MIT License](LICENSE).
