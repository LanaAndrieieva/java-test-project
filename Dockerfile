# Use the official Maven image with JDK 17
FROM maven:3.9.6-eclipse-temurin-17

# Install Google Chrome (needed for UI tests)
RUN apt-get update \
    && apt-get install -y --no-install-recommends wget ca-certificates \
    && wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
    && apt-get install -y ./google-chrome-stable_current_amd64.deb \
    && rm google-chrome-stable_current_amd64.deb \
    && rm -rf /var/lib/apt/lists/*

# Set the working directory inside the container
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the entire source code and testng.xml configuration
COPY src ./src
COPY testng*.xml ./


ENV SUITE=testng.xml \
HEADLESS_MODE=true
# Command to run tests
CMD ["sh", "-c", "mvn test -B -Dsuite=${SUITE} -DheadlessMode=${HEADLESS_MODE}"]