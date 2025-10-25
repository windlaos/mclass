FROM openjdk:17-jdk-slim
WORKDIR /app

COPY app.jar /app/app.jar
COPY src/main/resources/static /app/static

EXPOSE 80
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
