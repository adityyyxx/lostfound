# 🚀 Run Stage
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/lostfound-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 57014
CMD ["java", "-jar", "app.jar"]
