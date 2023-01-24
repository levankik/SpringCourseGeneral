FROM openjdk:19-jdk-slim as builder

WORKDIR /app

COPY . .

RUN --mount=type=cache,target=/root/.m2 ./mvnw package

RUN ls -l target

FROM openjdk:19-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/workshop1-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]