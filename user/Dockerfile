# syntax=docker/dockerfile:1

# ---- Build stage ----
FROM maven:3.9.12-amazoncorretto-17-alpine AS build
WORKDIR /workspace

# Copy only POMs first for better layer caching
COPY pom.xml .
COPY user/pom.xml user/pom.xml
# Include sibling module POMs so aggregator parent resolves module structure
COPY trading/pom.xml trading/pom.xml
COPY portfolio/pom.xml portfolio/pom.xml
COPY settlement-processor/pom.xml settlement-processor/pom.xml
COPY stock-api/pom.xml stock-api/pom.xml

# Pre-download dependencies for the user module
RUN mvn -q -B -DskipTests -pl user -am dependency:go-offline

# Copy source and build (only user sources needed)
COPY user/src user/src
RUN mvn -q -B -DskipTests -pl user -am package

# ---- Runtime stage ----
FROM amazoncorretto:8u432-alpine3.17-jre
WORKDIR /app

# Use a non-root user
RUN adduser -D -u 10001 spring
USER 10001

# Copy the fat jar built by Spring Boot
COPY --from=build /workspace/user/target/user-*.jar /app/app.jar

# Container-friendly JVM defaults for Fargate
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75 -XX:+ExitOnOutOfMemoryError -XX:+UseStringDeduplication"

EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar"]