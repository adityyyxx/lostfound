# Use official OpenJDK image
FROM eclipse-temurin:22-jdk

# Set work directory
WORKDIR /app

# Copy everything
COPY . .

# Build app
RUN ./mvnw clean package -DskipTests

# Expose port (match Render's PORT env variable)
EXPOSE 57041

# Start app
CMD ["java", "-jar", "target/lostfound-0.0.1-SNAPSHOT.jar"]

