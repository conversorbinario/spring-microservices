#FROM openjdk:17-jdk-alpine
FROM amazoncorretto:21
# the JAR file path, evidentemente tras empaquetar con './gradlew build' o similiar
#ARG JAR_FILE=build/libs/*.jar
ARG JAR_FILE=build/libs/microservices-0.0.1-SNAPSHOT.jar

# Copy the JAR file from the build context into the Docker image
COPY ${JAR_FILE} application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]
#ENTRYPOINT ["tail", "-f", "/dev/null"]

