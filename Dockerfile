FROM openjdk:17-jdk-alpine3.14
WORKDIR /app
COPY target/tdd-supermarket-1.0.0-SNAPSHOT.jar /app/app.jar
CMD java -jar /app/app.jar

