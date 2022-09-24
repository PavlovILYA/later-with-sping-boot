FROM amazoncorretto:11-alpine-jdk
#ENV POSTGRES_USER=later
#ENV POSTGRES_PASSWORD=later_password
#ENV DB_HOST=localhost
#ENV DB_PORT=5432
#ENV DB_NAME=later
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]