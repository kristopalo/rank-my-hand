FROM eclipse-temurin:21-jdk-alpine

COPY build/libs/rank-my-hand-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]