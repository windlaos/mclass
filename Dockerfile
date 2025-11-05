FROM amazoncorretto:17-alpine
WORKDIR /app
COPY app.jar /app/app.jar

EXPOSE 80
CMD ["java","-Dserver.port=80","-jar","/app/app.jar"]
