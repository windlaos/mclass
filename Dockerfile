FROM openjdk:17-jdk-slim

WORKDIR /app

COPY app.jar /app/app.jar

# ✅ Spring Boot 정적 리소스 복사
COPY static/ /app/static/

EXPOSE 80

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

