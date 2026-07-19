# Stage 1: Build
FROM maven:3.9.2-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml and source code
COPY tutorialapi/pom.xml .
COPY tutorialapi/src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/target/tutorialapi-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Set environment variables (Render will override these)
ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=prod

# Run the application
CMD ["java", "-jar", "app.jar"]
