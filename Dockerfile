FROM openjdk:17-jdk-slim

WORKDIR /app

COPY app.jar /app/app.jar

# ✅ 정적 리소스 복사 (이미지 포함)
COPY src/main/resources/static /app/static

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 80

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
