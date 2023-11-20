#!/bin/bash

# Start MongoDB docker container using docker-compose
docker-compose up -d

# Set gradle version to be used for wrapper
gradle wrapper --gradle-version 8.4

# Make the gradlew script executable
chmod +x gradlew

# Build the application using Gradle
./gradlew build

# Define the default Spring profile (if no argument is provided default is 'test')
SPRING_PROFILE=${1:-test}

# Run the Spring Boot application with the specified Spring profile
java -jar build/libs/httpserver-0.0.1-SNAPSHOT.jar --spring.profiles.active=$SPRING_PROFILE