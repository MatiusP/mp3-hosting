# ==== Build stage ====
FROM gradle:8.12.1-jdk17-alpine AS build
WORKDIR /app

COPY build.gradle settings.gradle gradle.properties gradlew ./
COPY resource-service/build.gradle resource-service/
COPY gradle/wrapper/ gradle/wrapper/

VOLUME /home/gradle/.gradle

RUN chmod +x gradlew && ./gradlew :resource-service:dependencies --no-daemon

COPY resource-service/src resource-service/src

RUN ./gradlew :resource-service:assemble --no-daemon -x test


#==== Runtime stage ====
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/resource-service/build/libs/*.jar app.jar

EXPOSE 8071

CMD ["sh", "-c", "java -jar app.jar --server.port=${RESOURCE_SERVICE_PORT:-8071}"]
