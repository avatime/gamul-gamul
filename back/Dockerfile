FROM openjdk:8-jdk-alpine
EXPOSE 8080
docker build -f back/Dockerfile .
COPY ./build/libs/*.jar app.jar
CMD ["java","-jar","app.jar"]

