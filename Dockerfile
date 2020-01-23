FROM maven:3.6.3-jdk-8 AS build
WORKDIR /app
COPY . /app
RUN mvn clean install

FROM adoptopenjdk:8-jre-hotspot-bionic
COPY --from=build /app/target/benevole-renderer.jar /app/benevole-renderer.jar
COPY --from=build /app/config.yml /app/config.yml
WORKDIR /app
CMD ["java", "-jar", "benevole-renderer.jar", "server", "config.yml"]