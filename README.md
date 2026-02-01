# 🃏 Rank My Hand

A Kotlin Spring Boot application for evaluating and ranking Texas Hold'em poker hands.

## Features

- 🃏 **Poker Hand Evaluation**: Evaluate any poker hand with hole cards and board cards
- 📊 **Hand Rankings**: Browse all poker hand rankings from Royal Flush to High Card
- 🎴 **Card Information**: Get details about all cards, ranks, and suits
- 🔢 **Hand Combinations**: Explore all possible hand combinations for each ranking
- 📚 **Interactive API Documentation**: Full Swagger/OpenAPI integration
- 🐳 **Docker Support**: Containerized deployment with Docker Compose

## Quick Start

### Prerequisites

- Java 21
- Gradle (included via wrapper)

### Build & Run

Clean and build the project:

```bash
.\gradlew clean build
```

Run the application:

```bash
.\gradlew bootRun
```

The application will start on `http://localhost:8080`

### Docker

Build and run with Docker Compose in detached mode:

```bash
docker-compose up -d --build
```

Start the container (without rebuilding):

```bash
docker-compose up -d
```

Stop the container:

```bash
docker-compose down
```

## API Documentation

### Swagger UI (Interactive)

Once the application is running, access the interactive API documentation:

**http://localhost:8080/swagger-ui.html**

### OpenAPI Specification

- JSON format: http://localhost:8080/api-docs
- YAML format: http://localhost:8080/api-docs.yaml

## API Endpoints

### Evaluator

- `POST /api/evaluator/evaluate` - Evaluate a poker hand

### Cards & Metadata

- `GET /api/cards` - Get all playing cards
- `GET /api/card-ranks` - Get all card ranks (A-2)
- `GET /api/suits` - Get all card suits (spades, hearts, diamonds, clubs)

### Rankings

- `GET /api/rankings` - Get all poker hand rankings
- `GET /api/rankings/{rankingId}` - Get a specific ranking
- `GET /api/rankings/{rankingId}/hand-combinations` - Get hand combinations for a ranking
- `GET /api/rankings/{rankingId}/hand-combinations/{rankNotation}` - Get specific hand combination

### Hand Combinations

- `GET /api/hand-combinations` - Get all hand combinations

## Example Usage

### Evaluate a Hand

```bash
curl -X POST http://localhost:8080/api/evaluator/evaluate \
  -H "Content-Type: application/json" \
  -d '{"cards": "As Kh Qd Jc Ts"}'
```

Or use the Swagger UI to try it interactively!

## Technology Stack

- **Language**: Kotlin
- **Framework**: Spring Boot 3.4.5
- **Build Tool**: Gradle
- **API Documentation**: SpringDoc OpenAPI 3
- **Testing**: JUnit 5, Kotest, MockK

## Development

### Run Tests

```bash
.\gradlew test
```

### Build JAR

```bash
.\gradlew bootJar
```

The JAR file will be created in `build/libs/`

## License

This project is licensed under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) - see the [LICENSE](LICENSE) file for details.

Copyright © 2026 Big Stack Bully
