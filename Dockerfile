FROM maven:3.9.6-eclipse-temurin-21

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

CMD ["java", "-jar", "target/server.jar"]