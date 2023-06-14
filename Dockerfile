# FROM maven:3.8.3-jdk-11-openj9 AS build
# WORKDIR /app
# COPY . .
# RUN mvn clean package -DskipTests

# FROM adoptopenjdk:11-jre-hotspot
# WORKDIR /app
# COPY --from=build /app/target/UserServiceApplication.jar .
# CMD ["java", "-jar", "UserServiceApplication.jar"]

# Use an official Maven runtime as a parent image
FROM maven:3.8.3-openjdk-17-slim AS builder

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src/ ./src/

# Build the project with Maven
RUN mvn package -DskipTests

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file from the builder stage to the container
COPY --from=builder /app/target/*.jar UserServiceApplication.jar

# Run the Spring Boot application
CMD ["java", "-jar", "UserServiceApplication.jar"]
