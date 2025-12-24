FROM eclipse-temurin:21-jdk AS builder

WORKDIR /build

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml pom.xml
COPY src src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder build/target/*.jar project.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "project.jar"]