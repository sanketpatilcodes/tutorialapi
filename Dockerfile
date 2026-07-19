# Stage 1: Build
FROM maven:3.9.2-eclipse-temurin-17 AS builder

WORKDIR /build

# Copy pom.xml
COPY tutorialapi/pom.xml .

# Copy source code
COPY tutorialapi/src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /build/target/*.jar app.jar

# Copy startup script
COPY tutorialapi/start.sh .
RUN chmod +x start.sh

# Expose port
EXPOSE 8080

# Set environment variables
ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=prod

# Run using the startup script
CMD ["./start.sh"]
