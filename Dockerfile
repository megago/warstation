FROM openjdk:17-jdk-slim
LABEL authors="yerzhant"
WORKDIR /app
COPY ./certs /etc/ssl/certs
COPY target/api-0.0.1.jar app.jar
EXPOSE 443
ENTRYPOINT ["java", "-jar", "app.jar"]