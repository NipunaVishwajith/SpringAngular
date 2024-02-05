#!/bin/bash

# Set up environment variables
export DATABASE_URL="jdbc:mysql://localhost:3306/mydatabase"

# Build and run the application
./mvnw clean install -DskipTests
java -jar target/myapp.jar
