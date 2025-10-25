FROM openjdk:17-jdk-slim
WORKDIR /app

# Spring Boot 실행 JAR
COPY app.jar app.jar

# ✅ 정적 파일을 Boot의 정적 리소스 경로로 복사
COPY static/ /app/static/

# ✅ Boot가 static 파일을 자동 인식하도록 경로 설정
ENV SPRING_WEB_RESOURCES_STATIC_LOCATIONS=file:/app/static/

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

