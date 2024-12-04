FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/sscore-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} sscore.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","sscore.jar"]