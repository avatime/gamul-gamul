FROM openjdk:8-jdk-alpine
WORKDIR app
EXPOSE 8080
COPY ./build/libs/*.jar app.jar
CMD ["java","-jar","app.jar"]

