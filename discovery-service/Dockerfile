# ==== Build stage ====
FROM gradle:8.12.1-jdk17-alpine AS build
WORKDIR /app

COPY build.gradle settings.gradle gradle.properties gradlew ./
COPY discovery-service/build.gradle discovery-service/
COPY gradle/wrapper/ gradle/wrapper/

VOLUME /home/gradle/.gradle

RUN chmod +x gradlew && ./gradlew :discovery-service:dependencies --no-daemon

COPY discovery-service/src discovery-service/src

RUN ./gradlew :discovery-service:assemble --no-daemon -x test


#==== Runtime stage ====
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/discovery-service/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["sh", "-c", "java -jar app.jar --server.port=${DISCOVERY_SERVICE_PORT:-8761}"]
