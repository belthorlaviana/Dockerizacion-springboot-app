FROM openjdk:17-jdk-alpine
COPY target/Dockerizacion-springboot-app-0.0.1-SNAPSHOT.jar java-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "java-app.jar"]