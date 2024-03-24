FROM docker.io/library/maven:3.8.6-openjdk-18 AS builder
WORKDIR /tmp
ARG JAR_FILE
COPY ./src ./src
COPY ./pom.xml .
# 課題: mvnwを使う方法
# COPY ./.mvn ./.mvn
# COPY ./mvnw .
# COPY ./mvnw.cmd .
# RUN mvn wrapper:wrapper
# RUN ./mvnw package
RUN mvn package
# FROM gcr.io/distroless/java17-debian11
# FROM openjdk:18
FROM docker.io/library/openjdk:18-alpine
COPY --from=builder /tmp/$JAR_FILE /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
