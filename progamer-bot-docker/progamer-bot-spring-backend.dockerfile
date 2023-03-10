### STAGE 1: Build ###
FROM gradle:8-jdk17 as build

WORKDIR /usr/app/
COPY . .
RUN gradle build -PprojVersion=0.0.0

### STAGE 2: Run ###
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /usr/app/build/libs/progamer-bot-spring-backend-0.0.0.jar ./progamer-bot-spring-backend.jar

ENTRYPOINT ["java", "-jar", "./progamer-bot-spring-backend.jar"]
