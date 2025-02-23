# ==== Build stage ====
FROM gradle:8.12.1-jdk17-alpine AS build
WORKDIR /app

COPY build.gradle settings.gradle gradle.properties gradlew ./
COPY gateway-service/build.gradle gateway-service/
COPY gradle/wrapper/ gradle/wrapper/

VOLUME /home/gradle/.gradle

RUN chmod +x gradlew && ./gradlew :gateway-service:dependencies --no-daemon

COPY gateway-service/src gateway-service/src

RUN ./gradlew :gateway-service:assemble --no-daemon -x test


#==== Runtime stage ====
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/gateway-service/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["sh", "-c", "java -jar app.jar --server.port=${GATEWAY_SERVICE_PORT:-8080}"]
