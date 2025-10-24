FROM openjdk:17-jdk

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일 복사
COPY app.jar app.jar

# ✅ Spring 프로파일(prod) 활성화
ENV SPRING_PROFILES_ACTIVE=prod

# ✅ 80 포트로 서비스
EXPOSE 80

# ✅ 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
