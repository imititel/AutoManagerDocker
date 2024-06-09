# Folosim imaginea oficială OpenJDK ca imagine de bază
FROM openjdk:11-jre-slim

VOLUME /tmp

WORKDIR /app

COPY target/producing-web-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]

