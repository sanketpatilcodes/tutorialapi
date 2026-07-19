#!/bin/sh

# If SPRING_DATASOURCE_URL is set, convert format if needed
if [ ! -z "$SPRING_DATASOURCE_URL" ]; then
    if echo "$SPRING_DATASOURCE_URL" | grep -q "^postgresql://"; then
        SPRING_DATASOURCE_URL="jdbc:postgresql://${SPRING_DATASOURCE_URL#postgresql://}"
    elif echo "$SPRING_DATASOURCE_URL" | grep -q "^postgres://"; then
        SPRING_DATASOURCE_URL="jdbc:postgresql://${SPRING_DATASOURCE_URL#postgres://}"
    fi
    echo "✓ Database URL: $SPRING_DATASOURCE_URL"
    
    # Pass as system property
    DB_URL_PROP="-Dspring.datasource.url=$SPRING_DATASOURCE_URL"
else
    echo "⚠ No database URL provided, will use localhost fallback"
    DB_URL_PROP=""
fi

echo "Port: $PORT"
echo "Profile: $SPRING_PROFILES_ACTIVE"

# Start the application
exec java ${JAVA_OPTS} \
  -Dserver.port=${PORT} \
  -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-prod} \
  $DB_URL_PROP \
  -jar app.jar


