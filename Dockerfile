#FROM openjdk:17-jdk-alpine
FROM amazoncorretto:21

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle wrapper and configuration files first (to cache dependencies)
COPY gradlew /app/gradlew
COPY gradle /app/gradle
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

# Permisos gradlew wrapper para que sea ejecutable
RUN chmod +x ./gradlew

# Pre-download dependencies
RUN ./gradlew dependencies --no-daemon || true

#Copiamos los archivos al workdir
#COPY . /app

# Compilamos y corremos la app
CMD ["sh", "-c", "./gradlew build && java -jar build/libs/microservices-0.0.1-SNAPSHOT.jar"]


