FROM openjdk:8-jdk-alpine as builder
WORKDIR /var/jenkins_home/workspace/backend/back
COPY ./ ./
RUN gradle clean build --no-daemon

FROM openjdk:8-jdk-alpine
WORKDIR /var/jenkins_home/workspace/backend/back
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} spring.jar
ENTRYPOINT ["java","-jar","/spring.jar"]

