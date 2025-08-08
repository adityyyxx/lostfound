# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk
WORKDIR /app
# Use wildcard to copy the generated jar regardless of exact version/name
COPY --from=build /app/target/*.jar app.jar
EXPOSE 57014
ENTRYPOINT ["java", "-jar", "app.jar"]
