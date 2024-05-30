FROM maven:3.9.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

FROM eclipse-temurin:17-jre-alpine
COPY --from=build /app/target/benevole-renderer.jar /app/benevole-renderer.jar
COPY --from=build /app/config.yml /app/config.yml
WORKDIR /app
CMD ["java", "-jar", "benevole-renderer.jar", "server", "config.yml"]