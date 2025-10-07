# Build step

FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR  /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


# Running an image
FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=build /build/target/gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8006
ENTRYPOINT ["java",  "-jar", "app.jar"]