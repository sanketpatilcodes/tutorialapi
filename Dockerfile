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

# Expose port
EXPOSE 8080

# Set environment variables
ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=prod

# Use shell form to properly expand environment variables
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Dserver.port=${PORT:-8080} -jar app.jar"]
