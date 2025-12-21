FROM eclipse-temurin:21-jre

WORKDIR /app

COPY . .

RUN apt-get update && apt-get install -y maven

CMD ["mvn", "spring-boot:run"]