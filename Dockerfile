# Build stage
FROM gradle:7.6-jdk17 AS build
WORKDIR /app
# Copy only the backend folder content
COPY backend/ .
# Ensure the wrapper has executable permissions
RUN chmod +x gradlew
# Build with debugging info to see the exact error
RUN ./gradlew build -x test --no-daemon --stacktrace --info

# Run stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Only copy the main executable JAR (avoiding the "plain" jar)
COPY --from=build /app/build/libs/*[!plain].jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
