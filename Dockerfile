FROM openjdk:17-jdk-slim

WORKDIR /app

COPY app.jar /app/app.jar

EXPOSE 80

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

