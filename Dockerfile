FROM gradle:8.5.0-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle assemble

FROM openjdk:17-alpine
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/api-1.0.0.jar /app/servinet-app.jar
ENTRYPOINT ["java", "-jar","/app/servinet-app.jar"]