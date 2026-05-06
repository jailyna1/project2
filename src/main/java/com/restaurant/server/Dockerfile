FROM envoyproxy/envoy:v1.29-latest as envoy

FROM eclipse-temurin:17-jre

# Copy your server JAR
COPY server/target/restaurant-project-1.0-SNAPSHOT.jar /app/server.jar

# Copy Envoy config
COPY envoy/envoy.yaml /etc/envoy/envoy.yaml

# Expose Envoy port (Cloud Run listens here)
EXPOSE 8080

# Start both Envoy and your Java server
CMD ["sh", "-c", "java -jar /app/server.jar & envoy -c /etc/envoy/envoy.yaml"]
