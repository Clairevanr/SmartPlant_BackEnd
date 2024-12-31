FROM openjdk:21-jdk
LABEL authors="clair"

WORKDIR /app

COPY build/libs/SmartPlant-0.0.1-SNAPSHOT.jar /app/app.jar

ENV PORT=8080
EXPOSE ${PORT}

ENTRYPOINT java -jar /app/app.jar --server.port=$PORT