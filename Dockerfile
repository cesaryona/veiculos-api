FROM openjdk:21-jdk-slim

WORKDIR /app
COPY . /app

EXPOSE 8080

CMD ["java", "-jar", "build/libs/veiculos-api.jar"]
