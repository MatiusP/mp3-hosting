# ==== Build stage ====
FROM gradle:8.12.1-jdk17-alpine AS build
WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY song-service/build.gradle song-service/
COPY gradle/wrapper/ gradle/wrapper/

RUN chmod +x gradlew && ./gradlew :song-service:dependencies --no-daemon

COPY song-service/src song-service/src

RUN ./gradlew :song-service:assemble --no-daemon -x test


#==== Runtime stage ====
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/song-service/build/libs/*.jar app.jar

EXPOSE 8072

CMD ["sh", "-c", "java -jar app.jar --server.port=${SONG_SERVER_PORT:-8072}"]
