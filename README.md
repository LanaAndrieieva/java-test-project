# Test Automation Framework (Java, Selenium, TestNG, Maven, RestAssured)

A robust test automation framework for testing Web UI and REST APIs using Java, Selenium WebDriver, TestNG, Maven, and RestAssured.

---

## Prerequisites

To run this project locally, ensure you have the following installed:
* **Java JDK** (Version 17 or higher)
* **Maven** (Version 3.8+)
* **Google Chrome** browser (for UI tests)
* **Git** (optional, for cloning the repository)

---

## Local Execution

Clone the repository and navigate to the project root directory in your terminal. 

### 1. Run UI Tests
To execute the UI test suite:
```bash
mvn test -Dtest={TestClassName}
```

### 2. Run API Tests
To execute the API test suite:
```bash
mvn test -Dtest={TestClassName}
```

### 3. Run all Tests
To execute the entire test suite (both UI and API as configured in testng.xml):
```bash
mvn test
```

## Docker Execution
You can run all tests inside a Docker container without needing to install Java, Maven, or local browser drivers.

### 1. Build the Docker Image
Open your terminal in the project root directory and build the image:
```bash
docker build -t qa-automation-tests .
```
### 2. Execute Tests Inside Docker
Run the container to execute the test suite:
```bash
docker run --rm qa-automation-tests
```

## Test Approach
### Locators Strategy
Used stable CSS selectors and explicit IDs. Avoided brittle absolute XPaths.

### Synchronization
Explicit Waits: Replaced all hardcoded Thread.sleep() with WebDriverWait and Expected Conditions. This prevents flakiness caused by network latency or slow DOM rendering.
Implicit Waits: Kept to zero or a minimal baseline to avoid conflicts with explicit waits.

### Code Structure (UI/API Architecture)
Page Object Model (POM): UI tests adhere to the POM design pattern. Page classes encapsulate web elements and user actions, keeping the test scripts clean and focused purely on assertions and user flows.

API Client Layer: API tests utilize a dedicated client structure combined with RestAssured RequestSpecification and ResponseSpecification to eliminate code duplication across endpoints.
  **clients**: Encapsulate endpoint execution logic and HTTP methods.
  **models**: POJO/DTO classes representing request and response payloads for clean serialization/deserialization.
  **providers**: Manage test data generation, states, or configurations.

Base Classes: Shared setup/teardown logic, driver initialization, and configuration management are handled centrally in base test classes (BaseTest).

## Assumptions & Limitations
Headless Execution in Docker: UI tests run in headless mode inside Docker containers since there is no physical display server attached.