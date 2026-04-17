# MCP Server - Spring Boot AI Project

A sophisticated **Model Context Protocol (MCP) Server** built with Spring Boot 4.0.5 and Spring AI. This project demonstrates real-time seller account management with AI-powered tools for querying and searching seller data via the Model Context Protocol.

## 🚀 Features

- **MCP Server Implementation**: Streamable HTTP protocol-based MCP server for AI client integration
- **AI-Powered Tools**: Spring AI tools for intelligent seller account queries
- **Async WebFlux**: Reactive programming model with Spring WebFlux and R2DBC
- **Ollama Integration**: Local LLM support via Ollama AI model
- **PostgreSQL Database**: Reactive database access with R2DBC driver
- **Docker Compose**: Pre-configured development environment with PostgreSQL and Ollama
- **Seller Account Management**: Query and search seller accounts by name, owner, and more

## 🛠️ Technology Stack

- **Java**: Version 26 (LTS)
- **Spring Boot**: 4.0.5
- **Spring AI**: 2.0.0-M4
- **Spring Data R2DBC**: Reactive database access
- **Spring WebFlux**: Reactive web framework
- **PostgreSQL**: Database
- **Ollama**: Local LLM provider
- **Build Tool**: Gradle

## 📋 Prerequisites

Before you start, ensure you have:

- **Java 26+** installed
- **Docker & Docker Compose** (for database and Ollama)
- **Gradle** (optional, included via gradlew)

## 🏗️ Project Structure

```
MCPServer/
├── src/main/java/org/vis/mcpserver/
│   ├── McpServerApplication.java          # Main Spring Boot application
│   ├── config/
│   │   └── DatabaseConfig.java            # Database configuration
│   ├── entity/
│   │   └── SellerAccount.java             # JPA entity for seller accounts
│   ├── repository/
│   │   └── SellerAccountRepository.java   # R2DBC repository for reactive DB access
│   └── tools/
│       └── SellerAccountTools.java        # AI tools for seller account operations
├── src/main/resources/
│   ├── application.yml                    # Spring Boot configuration
│   ├── schema.sql                         # Database schema
│   ├── init.sql                           # Initial data setup
│   └── data.sql                           # Sample data
├── compose.yaml                           # Docker Compose for development
└── build.gradle                           # Gradle build configuration
```

## 🔧 Available AI Tools

The MCP server exposes the following AI tools:

### 1. **Search Seller Accounts by Name**
- **Tool Name**: `search_seller_accounts_by_name`
- **Parameter**: `name` (Seller Account Name)
- **Returns**: JSON list of matching seller accounts
- **Description**: Find all seller accounts by name

### 2. **Fetch All Seller Accounts**
- **Tool Name**: `fetch_all_seller_accouts`
- **Parameters**: None
- **Returns**: List of seller accounts (limited to 5)
- **Description**: Retrieves all seller accounts from the database

### 3. **Search Seller Accounts by Owner**
- **Tool Name**: `search_seller_accounts_by_owner`
- **Parameter**: `owner` (Seller Account Owner)
- **Returns**: JSON list of matching seller accounts
- **Description**: Find all seller accounts by owner

## 🚀 Getting Started

### 1. Clone the Repository

```bash
cd /Users/visu/IdeaProjects/MCPServer
```

### 2. Start Dependencies with Docker Compose

```bash
docker-compose up -d
```

This will start:
- **PostgreSQL** on port 5433 (username: postgres, password: secret)
- **Ollama** on port 11434 with persistent data

### 3. Build the Application

```bash
./gradlew build
```

Or on Windows:
```bash
gradlew.bat build
```

### 4. Run the Application

```bash
./gradlew bootRun
```

The MCP Server will start on **http://localhost:9060**

## 📡 API Endpoints

### MCP Endpoints

- **MCP Message Endpoint**: `/mcp/message` (POST) - Send MCP messages
- **SSE Endpoint**: `/sse` - Server-Sent Events for streaming responses
- **Streamable HTTP Endpoint**: `/mcp` - Main MCP endpoint

## 🗄️ Database Configuration

The application uses R2DBC for reactive database access:

```yaml
r2dbc:
  url: r2dbc:postgresql://localhost:5433/postgres
  username: postgres
  password: secret
```

### Sample Data

The database is automatically initialized with sample seller accounts:

| ID | Name   | Owner      | Type | Status |
|----|---------|-----------| -----|--------|
| 1  | harry   | manomano  | STD  | 1      |
| 2  | mike    | amazon    | STD  | 1      |
| 3  | David   | manomano  | STD  | 1      |
| 4  | davids  | manomano  | STD  | 1      |
| 5  | lucas   | flipkart  | STD  | 1      |

## 🧠 AI/LLM Configuration

The application is configured to use Ollama for local LLM support:

```yaml
ai:
  mcp:
    server:
      name: vis-mcp-server
      capabilities:
        resource: true
      type: ASYNC
      protocol: STREAMABLE
      sse-message-endpoint: /mcp/message
      sse-endpoint: /sse
```

### Pulling a Model in Ollama

Once Ollama is running, pull a model:

```bash
docker exec ollama ollama pull llama2
```

Or use other available models like `mistral`, `neural-chat`, etc.

## 📊 Seller Account Entity

```java
@Table(name = "seller_account")
public class SellerAccount {
    @Id private Long id;
    private String name;
    private String owner;
    private Boolean isTest;
    private String type;
    private Integer status;
}
```

## 🔍 Logging

The application uses Spring AI debug logging:

```yaml
logging:
  level:
    org.springframework.ai: DEBUG
```

Enable this in `application.yml` to see detailed Spring AI operations.

## 📦 Main Dependencies

```gradle
// Spring AI MCP Server
implementation 'org.springframework.ai:spring-ai-starter-mcp-server-webflux'

// Reactive Database
implementation 'org.springframework.boot:spring-boot-starter-data-r2dbc'
implementation 'org.springframework.boot:spring-boot-starter-webflux'

// Local LLM Support
implementation 'org.springframework.ai:spring-ai-starter-model-ollama'

// Database Drivers
runtimeOnly 'org.postgresql:postgresql'
runtimeOnly 'org.postgresql:r2dbc-postgresql'
```

## 🛑 Stopping the Application

To stop the Docker containers:

```bash
docker-compose down
```

To remove all data (including volumes):

```bash
docker-compose down -v
```

## 🧪 Testing

Run the test suite:

```bash
./gradlew test
```

## 📝 Configuration Reference

### Server Port
- Default: `9060`
- Configure in `application.yml`: `server.port`

### Database
- PostgreSQL Container Port: `5433`
- Database Name: `postgres` (via compose.yaml: `ai_db`)
- Username: `postgres`
- Password: `secret`

### Ollama
- Container Port: `11434`
- Default Model: `llama2` (must be pulled separately)

## 🤝 Contributing

This is a development/prototype project. Feel free to extend with:
- Additional seller account tools
- More AI-powered features
- Enhanced error handling
- Additional database integrations

## 📜 License

This project is part of the MCP Server initiative.

## 🔗 Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring AI Documentation](https://spring.io/projects/spring-ai)
- [Model Context Protocol](https://spec.modelcontextprotocol.io)
- [Ollama Documentation](https://ollama.ai)
- [PostgreSQL R2DBC Documentation](https://r2dbc.io)

---

**Version**: 0.0.1-SNAPSHOT  
**Last Updated**: April 2026
