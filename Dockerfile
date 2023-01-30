FROM openjdk:19-jdk-slim as builder

WORKDIR /app

COPY . .

RUN --mount=type=cache,target=/root/.m2 ./mvnw package

RUN ls -l target

FROM openjdk:19-jdk-slim

WORKDIR /app

HEALTHCHECK --interval=5s --timeout=3s --retries=3 CMD wget -q -0 /dev/null http://localhost:8080/actuator/health || exit 1

COPY --from=builder /app/target/workshop1-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]