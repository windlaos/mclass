FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일 복사
COPY app.jar /app/app.jar

# ✅ static 정적 파일 포함 (이미지가 컨테이너 내에 존재하도록)
COPY static /app/static

# ✅ Spring 프로파일(prod) 활성화
ENV SPRING_PROFILES_ACTIVE=prod

# ✅ 컨테이너의 서비스 포트 설정
EXPOSE 80

# ✅ 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

