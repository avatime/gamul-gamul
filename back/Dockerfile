FROM openjdk:8-jdk-alpine
WORKDIR /var/jenkins_home/workspace/backend/back
EXPOSE 8080
COPY ./build/libs/*.jar app.jar
CMD ["java","-jar","app.jar"]

