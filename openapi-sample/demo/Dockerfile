FROM maven:3.8.6-openjdk-18 AS builder
WORKDIR /tmp
COPY ./src ./src
COPY ./pom.xml .
RUN mvn package
# FROM gcr.io/distroless/java17-debian11
# FROM openjdk:18
FROM openjdk:18-alpine
COPY --from=builder /tmp/target/demo-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]