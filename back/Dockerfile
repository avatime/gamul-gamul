FROM openjdk:8-jdk-alpine
WORKDIR /var/jenkins_home/workspace/backend/back
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","/jenkins/images_tar/spring.jar"]

