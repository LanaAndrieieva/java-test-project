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

## Run with Docker

Requirement: Docker only (no local Java, Maven or Chrome needed).

### 1. Build the image

```bash
docker build -t qa-automation-tests .
```

> Apple Silicon (M1/M2/M3): Chrome in the image is amd64 only, so add
> `--platform linux/amd64` to both `docker build` and `docker run`.

### 2. Run the tests

```bash
# All tests (default testng.xml)
docker run --rm qa-automation-tests

# API tests only
docker run --rm -e SUITE=testng-api.xml qa-automation-tests

# UI tests only (headless Chrome)
docker run --rm --shm-size=2g -e SUITE=testng-ui.xml qa-automation-tests
```

### 3. Save the Allure results on disk

The container is removed after the run (`--rm`), so mount a folder to keep the results.

Windows (PowerShell):

```powershell
Remove-Item -Recurse -Force allure-results -ErrorAction SilentlyContinue
docker run --rm --shm-size=2g -e SUITE=testng-ui.xml -v "${PWD}/allure-results:/app/target/allure-results" qa-automation-tests
```

Linux / macOS:

```bash
rm -rf allure-results
docker run --rm --shm-size=2g -e SUITE=testng-ui.xml -v "$(pwd)/allure-results:/app/target/allure-results" qa-automation-tests
```

Remove old results before each run, otherwise Allure mixes old and new runs.

### 4. View the Allure report

Windows (PowerShell):

```powershell
docker run --rm -p 5050:5050 -e CHECK_RESULTS_EVERY_SECONDS=5 -v "${PWD}/allure-results:/app/allure-results" frankescobar/allure-docker-service
```

Linux / macOS:

```bash
docker run --rm -p 5050:5050 -e CHECK_RESULTS_EVERY_SECONDS=5 -v "$(pwd)/allure-results:/app/allure-results" frankescobar/allure-docker-service
```

Then open:

```text
http://localhost:5050/allure-docker-service/latest-report
```

Stop the report server with `Ctrl+C`.

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