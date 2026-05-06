# Stage 1: Envoy
FROM envoyproxy/envoy:v1.29-latest as envoy

# Stage 2: Java runtime + Envoy
FROM eclipse-temurin:17-jre

# Copy Envoy binary (works for both possible locations)
COPY --from=envoy /usr/local/bin/envoy /usr/local/bin/envoy
COPY --from=envoy /usr/bin/envoy /usr/local/bin/envoy

# Copy your server JAR (correct path)
COPY target/restaurant-project-1.0-SNAPSHOT.jar /app/server.jar

# Copy Envoy config
COPY envoy/envoy.yaml /etc/envoy/envoy.yaml

# Expose Envoy port (Cloud Run listens here)
EXPOSE 8080

# Start both Envoy and your Java server
CMD ["sh", "-c", "java -jar /app/server.jar & envoy -c /etc/envoy/envoy.yaml"]
