# Build stage
FROM gradle:7.6-jdk17 AS build
WORKDIR /app
# Copy everything from the root directly
COPY . .
RUN chmod +x gradlew
RUN ./gradlew build -x test --no-daemon

# Run stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*[!plain].jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
