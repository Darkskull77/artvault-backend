# Build stage
FROM maven:3.8.4-openjdk-17 AS build
# Set the working directory to where your code is
WORKDIR /app
# Copy the backend folder contents into the build image
COPY backend/ .
# Run the build inside the folder containing pom.xml
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
