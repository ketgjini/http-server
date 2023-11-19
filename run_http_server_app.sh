#!/bin/bash

# Install Docker and Docker Compose
sudo apt update
sudo apt install -y docker.io docker-compose

# Start MongoDB docker container using docker-compose
docker-compose up -d

# Make the gradlew script executable
sudo chmod +x gradlew

# Build the application using Gradle
sudo ./gradlew build

# Run the Spring Boot application
sudo java -jar build/libs/httpserver-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev