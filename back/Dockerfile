FROM openjdk:8-jdk-alpine
WORKDIR /var/jenkins_home/workspace/backend/back

CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE_PATH=target/*.jar
COPY ${JAR_FILE_PATH} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
