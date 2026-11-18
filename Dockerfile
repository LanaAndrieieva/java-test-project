# Use the official Maven image with JDK 17
FROM maven:3.9.6-eclipse-temurin-17

# Set the working directory inside the container
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the entire source code and testng.xml configuration
COPY src ./src
COPY testng.xml .

# Command to run tests
CMD ["mvn", "test"]