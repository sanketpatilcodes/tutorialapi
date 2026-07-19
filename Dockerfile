# Stage 1: Build
FROM maven:3.9.2-eclipse-temurin-17 AS builder

WORKDIR /build

# Copy pom.xml
COPY pom.xml .

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /build/target/*.jar app.jar

# Expose port
EXPOSE $PORT

# Set environment variables
ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=prod
ENV SPRING_JAVAOPT="-Dserver.port=$PORT"

# Run the application with proper port binding
CMD ["java", "-Dserver.port=${PORT:-8080}", "-jar", "app.jar"]
