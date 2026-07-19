#!/bin/sh

# Convert postgresql:// or postgres:// to jdbc:postgresql://
if [ ! -z "$SPRING_DATASOURCE_URL" ]; then
    if echo "$SPRING_DATASOURCE_URL" | grep -q "^postgresql://"; then
        SPRING_DATASOURCE_URL="jdbc:postgresql://${SPRING_DATASOURCE_URL#postgresql://}"
    elif echo "$SPRING_DATASOURCE_URL" | grep -q "^postgres://"; then
        SPRING_DATASOURCE_URL="jdbc:postgresql://${SPRING_DATASOURCE_URL#postgres://}"
    fi
    echo "✓ Database URL configured"
else
    echo "⚠ No database URL provided"
fi

echo "Port: $PORT"
echo "Profile: $SPRING_PROFILES_ACTIVE"

# Pass database URL as system property
exec java ${JAVA_OPTS} \
  -Dserver.port=${PORT} \
  -Dspring.datasource.url="$SPRING_DATASOURCE_URL" \
  -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-prod} \
  -jar app.jar

