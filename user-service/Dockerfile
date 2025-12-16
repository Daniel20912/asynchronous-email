FROM eclipse-temurin:21-alpine
LABEL authors="Daniel Oliveira"
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]