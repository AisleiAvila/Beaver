# Estágio de build
FROM eclipse-temurin:22-jdk-jammy as build

RUN apt-get update && apt-get install -y maven

COPY . /app
WORKDIR /app

RUN mvn clean package -DskipTests

# Estágio final
FROM eclipse-temurin:22-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/src/main/resources ./resources

RUN apt-get update && apt-get install -y netcat-openbsd && apt-get clean

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]