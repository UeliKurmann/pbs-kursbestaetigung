FROM maven:3.6.3-jdk-8
WORKDIR /app
COPY . /app
RUN mvn clean install
CMD ["java", "-jar", "target/benevole-renderer.jar", "server", "config.yml"]
