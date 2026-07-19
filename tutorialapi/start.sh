# Start.sh script to handle Postgres URL conversion
#!/bin/sh

# Convert postgresql:// or postgres:// to jdbc:postgresql://
if [ ! -z "$SPRING_DATASOURCE_URL" ]; then
    if echo "$SPRING_DATASOURCE_URL" | grep -q "^postgresql://"; then
        export SPRING_DATASOURCE_URL="jdbc:postgresql://${SPRING_DATASOURCE_URL#postgresql://}"
    elif echo "$SPRING_DATASOURCE_URL" | grep -q "^postgres://"; then
        export SPRING_DATASOURCE_URL="jdbc:postgresql://${SPRING_DATASOURCE_URL#postgres://}"
    fi
fi

echo "Database URL: $SPRING_DATASOURCE_URL"
echo "Port: $PORT"

exec java ${JAVA_OPTS} -Dserver.port=${PORT} -jar app.jar


