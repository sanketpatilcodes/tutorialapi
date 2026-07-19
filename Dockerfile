# Build stage
FROM maven:3.9.2-eclipse-temurin-17 AS builder

WORKDIR /build

# Copy pom.xml
COPY tutorialapi/pom.xml .

# Copy source code
COPY tutorialapi/src ./src

# Build the application
RUN mvn clean package -DskipTests -Dorg.slf4j.simpleLogger.defaultLogLevel=warn

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the jar from builder
COPY --from=builder /build/target/*.jar app.jar

# Copy startup script
COPY tutorialapi/start.sh .
RUN chmod +x start.sh

# Create non-root user for security
RUN addgroup -S appuser && adduser -S appuser -G appuser
USER appuser

# Expose port (Render assigns PORT dynamically)
EXPOSE 8080

# Set environment variables for Render
ENV PORT=8080
ENV JAVA_OPTS="-Xmx512m"
ENV SPRING_PROFILES_ACTIVE=prod

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/api/health || exit 1

# Run the startup script
CMD ["./start.sh"]
