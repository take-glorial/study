FROM openjdk:8-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java" ,"-Dfile.encoding=UTF-8","-jar","/app.jar"]
