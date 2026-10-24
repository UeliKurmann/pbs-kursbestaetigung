FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

FROM eclipse-temurin:25-jre-alpine
COPY --from=build /app/target/benevole-renderer.jar /app/benevole-renderer.jar
COPY --from=build /app/config.yml /app/config.yml
WORKDIR /app
CMD ["java", "-XX:MaxRAMPercentage=75.0", "-jar", "benevole-renderer.jar", "server", "config.yml"]
