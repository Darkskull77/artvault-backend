# Build stage
FROM gradle:7.6-jdk17 AS build
WORKDIR /app
COPY . .
RUN chmod +x gradlew
# We use bootJar specifically to create the executable file
RUN ./gradlew bootJar -x test --no-daemon

# Run stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copy the specific app.jar we named in build.gradle
COPY --from=build /app/build/libs/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
