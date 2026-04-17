# Quick Reference - Method Documentation Guide

## 🎯 All Documented Methods at a Glance

### Main Application Flow
```
main() → creates Spring context
         ↓
         → initializes DatabaseConfig bean
         → initializes ToolCallbackProvider bean  
         → initializes McpAsyncServer bean
         ↓
         Application ready on port 9060
```

---

## 📦 Method Listing by Class

### 1️⃣ McpServerApplication (3 methods)

| # | Method | Type | Purpose |
|---|--------|------|---------|
| 1 | `main(String[])` | static void | **Entry Point** - Starts Spring Boot app |
| 2 | `tools(SellerAccountTools)` | Bean | **Registers AI Tools** - Creates tool provider |
| 3 | `mcpAsyncServer(...)` | Bean | **MCP Server** - Creates async server on `/mcp` |

---

### 2️⃣ DatabaseConfig (1 method)

| # | Method | Type | Purpose |
|---|--------|------|---------|
| 1 | `initializer(ConnectionFactory)` | Bean | **DB Init** - Runs schema.sql on startup |

---

### 3️⃣ SellerAccount Entity (6 methods)

| # | Method | Returns | Purpose |
|---|--------|---------|---------|
| 1 | `getId()` | Long | Get account ID (PK) |
| 2 | `getName()` | String | Get account name |
| 3 | `getOwner()` | String | Get owner/org name |
| 4 | `getTest()` | Boolean | Get test status |
| 5 | `getType()` | String | Get account type |
| 6 | `getStatus()` | Integer | Get account status |

---

### 4️⃣ SellerAccountRepository (2 methods)

| # | Method | Parameters | Returns | Purpose |
|---|--------|-----------|---------|---------|
| 1 | `findByName(String)` | name | Flux<List<...>> | Find by name (reactive) |
| 2 | `findByOwner(String)` | owner | Flux<List<...>> | Find by owner (reactive) |

---

### 5️⃣ SellerAccountTools (4 methods)

| # | Method | Type | Purpose |
|---|--------|------|---------|
| 1 | `SellerAccountTools(Repo)` | Constructor | **Initialize** - Inject repository |
| 2 | `getAccountByName(String)` | **AI Tool** | Search seller by name |
| 3 | `getAllAccount()` | **AI Tool** | List all sellers (limit 5) |
| 4 | `getAccountByOwner(String)` | **AI Tool** | Search sellers by owner |

---

### 6️⃣ McpServerApplicationTests (1 method)

| # | Method | Type | Purpose |
|---|--------|------|---------|
| 1 | `contextLoads()` | Test | **Smoke Test** - Verify Spring context loads |

---

## 🛠️ AI Tools for LLM/MCP Clients

```
┌─────────────────────────────────────────────────────────────┐
│                  Available AI Tools                         │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│ 1. search_seller_accounts_by_name                          │
│    Parameters: name (string)                               │
│    Returns: JSON array of matching seller accounts         │
│    Example: Search for "harry" returns Harry's account    │
│                                                             │
│ 2. fetch_all_seller_accouts                                │
│    Parameters: (none)                                      │
│    Returns: List<SellerAccount> (max 5 records)           │
│    Example: Get overview of all available sellers         │
│                                                             │
│ 3. search_seller_accounts_by_owner                         │
│    Parameters: owner (string)                              │
│    Returns: JSON array of seller accounts                 │
│    Example: Search for "amazon" returns all Amazon sellers│
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

---

## 🔌 API Endpoints

```
Application runs on: http://localhost:9060

MCP Protocol Endpoints:
├── POST   /mcp/message          - Send MCP messages
├── GET    /sse                  - Server-Sent Events (streaming)
└── POST   /mcp                  - Main MCP endpoint (Streamable HTTP)
```

---

## 💾 Database Schema

```
PostgreSQL Database
├── schema: postgres
└── table: seller_account
    ├── id (BIGSERIAL PRIMARY KEY)
    ├── name (VARCHAR 255, NOT NULL) - searchable
    ├── owner (VARCHAR 255)          - searchable
    ├── is_test (BOOLEAN, default=false)
    ├── type (VARCHAR 255)
    └── status (INTEGER, NOT NULL)

Sample Data:
├── ID:1 | name: harry  | owner: manomano | type: STD | status: 1
├── ID:2 | name: mike   | owner: amazon   | type: STD | status: 1
├── ID:3 | name: David  | owner: manomano | type: STD | status: 1
├── ID:4 | name: davids | owner: manomano | type: STD | status: 1
└── ID:5 | name: lucas  | owner: flipkart | type: STD | status: 1
```

---

## 🧩 Method Call Chain Example

### Scenario: AI asks "Find seller named 'harry'"

```
1. MCP Client sends request
   └─> Tool: search_seller_accounts_by_name
       Params: { name: "harry" }

2. Spring AI routes to SellerAccountTools.getAccountByName("harry")
   
3. Method executes:
   ├─> repository.findByName("harry")           // reactive query
   ├─> .collectList()                           // collect to list
   ├─> .block()                                 // wait for completion
   └─> objectMapper.writeValueAsString(list)    // convert to JSON

4. Returns JSON:
   [{ id: 1, name: "harry", owner: "manomano", type: "STD", status: 1 }]

5. MCP Server sends response to AI client
```

---

## 📊 Method Complexity Overview

| Complexity | Methods |
|-----------|---------|
| **Low** (Getters/Basic) | `getId()`, `getName()`, `getOwner()`, etc. |
| **Medium** (Bean Creation) | `tools()`, `initializer()` |
| **Medium** (Repository) | `findByName()`, `findByOwner()` |
| **High** (AI Tools) | `getAccountByName()`, `getAllAccount()`, `getAccountByOwner()` |
| **Entry Point** | `main()` |

---

## 📝 Documentation Files Created

1. **METHOD_DOCUMENTATION.md** - Comprehensive method reference
2. **BLOCK_COMMENTS_IMPLEMENTATION_SUMMARY.md** - Implementation details
3. **QUICK_REFERENCE.md** (this file) - Quick lookup guide

---

## ✅ Checklist - What Was Documented

- ✅ Method names and signatures
- ✅ All parameters with types and descriptions
- ✅ Return types and return value descriptions
- ✅ Annotations used (@Bean, @Tool, @Service, etc.)
- ✅ Usage explanations and purpose
- ✅ Execution flow and side effects
- ✅ Examples for AI tools
- ✅ Database field mappings
- ✅ Test assertions and success criteria
- ✅ Integration with MCP protocol

---

## 🎓 Learning Path for New Developers

1. **Start with**: `main()` in McpServerApplication
2. **Understand**: Bean creation pattern (`tools()`, `mcpAsyncServer()`, `initializer()`)
3. **Learn**: Entity model in SellerAccount (6 getter methods)
4. **Study**: Repository pattern in SellerAccountRepository (reactive queries)
5. **Deep dive**: AI tools in SellerAccountTools (MCP tool definitions)
6. **Verify**: Integration test in McpServerApplicationTests

---

## 🚀 Quick Start Commands

```bash
# Navigate to project
cd /Users/visu/IdeaProjects/MCPServer

# Start Docker containers (DB + Ollama)
docker-compose up -d

# Build project
./gradlew build

# Run application
./gradlew bootRun

# View method documentation
cat METHOD_DOCUMENTATION.md

# Stop containers
docker-compose down
```

---

## 🔍 Finding Specific Documentation

**In source code**: Look for `/**` blocks above method definitions

**In generated files**: 
- Open `METHOD_DOCUMENTATION.md` for complete reference
- Open `BLOCK_COMMENTS_IMPLEMENTATION_SUMMARY.md` for implementation details
- Open this file (`QUICK_REFERENCE.md`) for quick lookup

**In IDE**: 
- Hover over method name (shows tooltip)
- Press Cmd+J (Mac) or Ctrl+Q (Windows/Linux) for quick doc
- Press Cmd+B (Mac) to navigate to definition

---

## 📞 Support & Resources

- **Spring Boot**: https://spring.io/projects/spring-boot
- **Spring AI**: https://spring.io/projects/spring-ai
- **MCP Spec**: https://spec.modelcontextprotocol.io
- **Ollama**: https://ollama.ai
- **PostgreSQL R2DBC**: https://r2dbc.io
- **Reactor**: https://projectreactor.io

---

**Last Updated**: April 2026  
**Project Version**: 0.0.1-SNAPSHOT  
**Java Version**: 26 (LTS)

