FROM maven:3.6.2-jdk-8
WORKDIR /app
COPY . /app
RUN mvn clean install
CMD ["java", "-jar", "target/reporting-0.0.1-SNAPSHOT.jar", "server", "config.yml"]
