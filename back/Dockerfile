FROM openjdk:8-jdk-alpine as builder
WORKDIR /var/jenkins_home/workspace/backend/back
RUN chmod +x gradlew
RUN gradlew bootJAR

FROM openjdk:8-jdk-alpine
WORKDIR /var/jenkins_home/workspace/backend/back
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} spring.jar
ENTRYPOINT ["java","-jar","/spring.jar"]

