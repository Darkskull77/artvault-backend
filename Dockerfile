# Build stage
FROM gradle:7.6-jdk17 AS build
WORKDIR /app
# Copy the backend folder contents into the build image
COPY backend/ .
# Run the Gradle build to create the executable JAR
RUN ./gradlew clean build -x test

# Run stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copy the JAR from the build stage (Gradle puts it in build/libs/)
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
