# 🚀 RestAssured API Automation Framework

## 📖 Overview
This project is a **scalable, maintainable, and CI/CD-ready API automation framework** built using **Java, Rest Assured, TestNG, and Maven**.

It is designed to automate REST API testing with support for **token-based authentication, JSON schema validation, reusable utilities, and data-driven testing**, ensuring reliable and efficient test execution.

The framework follows **industry best practices** and is structured for **high reusability, modularity, and scalability**.

---

## 🎯 Key Features

- ✅ REST API automation using Rest Assured
- ✅ Supports HTTP methods (GET, POST, PUT, DELETE)
- ✅ Token-based authentication handling (centralized)
- ✅ JSON schema validation
- ✅ POJO-based request & response modeling
- ✅ Data-driven testing support
- ✅ Reusable request/response specifications
- ✅ Centralized configuration management
- ✅ Request & response logging
- ✅ CI/CD integration ready (GitHub Actions)

---

## 🧰 Tech Stack

| Technology       | Purpose                     |
|-----------------|----------------------------|
| Java            | Programming Language       |
| Rest Assured    | API Automation             |
| TestNG          | Test Execution Framework   |
| Maven           | Build & Dependency Manager |
| Jackson         | JSON Parsing               |
| GitHub Actions  | CI/CD Pipeline             |

---

## 🏗️ Framework Architecture
src
├── main/java
│ ├── constants → API constants & roles
│ ├── pojo → Request/Response models
│ ├── utils → Utilities (Auth, Config, etc.)
│
├── test/java
│ ├── tests → API test classes
│
├── test/resources
│ ├── testdata → JSON test data
│ ├── schemas → JSON schema files
│ ├── config → Environment properties

---

## 🔐 Authentication Handling

- Token is generated dynamically using login API
- Managed centrally via `AuthTokenProvider`
- Supports multiple user roles (FD, SUP, ENG, QC)
- Eliminates duplication across test cases

---

## 🔄 Test Execution Flow

1. TestNG triggers test execution
2. Token is generated dynamically
3. API request is built using Rest Assured
4. Request is sent to the server
5. Response is validated:
   - Status code
   - Response body
   - JSON schema
6. Logs are captured for debugging

---

## ▶️ How to Run Tests

### 🔹 Run all tests
```bash
mvn clean test
