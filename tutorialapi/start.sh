# Start.sh script to handle Postgres URL conversion
#!/bin/bash

# Convert postgres:// to jdbc:postgresql:// if needed
if [[ "$SPRING_DATASOURCE_URL" == postgres://* ]]; then
    export SPRING_DATASOURCE_URL="jdbc:postgresql://${SPRING_DATASOURCE_URL#postgres://}"
fi

echo "Starting Tutorial API..."
echo "Database URL: ${SPRING_DATASOURCE_URL}"
echo "Port: ${PORT}"

exec java \
  -XX:+UseContainerSupport \
  -XX:MaxRAMPercentage=75.0 \
  -Dserver.port=${PORT:-8080} \
  -Dspring.datasource.url="${SPRING_DATASOURCE_URL}" \
  -jar app.jar

