FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE
COPY ${JAR_FILE} converter.jar
ENTRYPOINT ["java","-jar","/converter.jar"]