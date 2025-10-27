FROM openjdk:17-jdk-slim
WORKDIR /app

COPY app.jar app.jar
COPY static/ /app/static/
COPY templates/ /app/templates/

EXPOSE 80
ENTRYPOINT ["java", "-jar", "app.jar"]
