# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/lostfound-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 57014
CMD ["java", "-jar", "app.jar"]
