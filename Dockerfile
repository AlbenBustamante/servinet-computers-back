FROM gradle:7.6.1-jdk17 AS builder

COPY build.gradle .
COPY src ./src

RUN gradle build -x test

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /home/gradle/build/libs/servinet-app.jar .

EXPOSE 8080

CMD ["java", "-jar", "servinet-app.jar"]
