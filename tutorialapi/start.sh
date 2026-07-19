#!/bin/sh

# Database URL handling
DB_URL_PROP=""
DRIVER_PROP=""

if [ ! -z "$SPRING_DATASOURCE_URL" ]; then
    # Convert postgresql:// or postgres:// to jdbc:postgresql://
    if echo "$SPRING_DATASOURCE_URL" | grep -q "^postgresql://"; then
        SPRING_DATASOURCE_URL="jdbc:postgresql://${SPRING_DATASOURCE_URL#postgresql://}"
    elif echo "$SPRING_DATASOURCE_URL" | grep -q "^postgres://"; then
        SPRING_DATASOURCE_URL="jdbc:postgresql://${SPRING_DATASOURCE_URL#postgres://}"
    fi
    echo "✓ Using PostgreSQL: $SPRING_DATASOURCE_URL"
    DB_URL_PROP="-Dspring.datasource.url=$SPRING_DATASOURCE_URL"
    DRIVER_PROP="-Dspring.datasource.driverClassName=org.postgresql.Driver"
else
    echo "ℹ No database URL provided, using H2 in-memory database"
    DB_URL_PROP="-Dspring.datasource.url=jdbc:h2:mem:testdb"
    DRIVER_PROP="-Dspring.datasource.driverClassName=org.h2.Driver"
fi

echo "Port: $PORT"
echo "Profile: $SPRING_PROFILES_ACTIVE"

# Start the application
exec java ${JAVA_OPTS} \
  -Dserver.port=${PORT} \
  -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-prod} \
  $DB_URL_PROP \
  $DRIVER_PROP \
  -jar app.jar



