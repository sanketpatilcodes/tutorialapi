#!/bin/bash

# Default port if not set
PORT=${PORT:-8080}

echo "========================================"
echo "Starting Tutorial API"
echo "Port: $PORT"
echo "Profile: $SPRING_PROFILES_ACTIVE"
echo "========================================"

# Start the application
exec java \
  -XX:+UseContainerSupport \
  -XX:MaxRAMPercentage=75.0 \
  -Dserver.port=$PORT \
  -Dlogging.level.root=INFO \
  -Dlogging.level.com.codeminton=DEBUG \
  -jar app.jar
