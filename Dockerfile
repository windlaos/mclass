FROM amazoncorretto:17-alpine
WORKDIR /app
COPY app.jar /app/app.jar

#COPY static/ /app/static/
#COPY templates/ /app/templates/

EXPOSE 80
CMD ["java","-Dserver.port=80","-jar","/app/app.jar"]
