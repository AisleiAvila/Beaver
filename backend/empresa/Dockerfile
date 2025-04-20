# Estágio de build
FROM eclipse-temurin:22-jdk-jammy as build

# Instala Maven e dependências do PostgreSQL temporário
RUN apt-get update && apt-get install -y maven postgresql netcat-openbsd

# Configura PostgreSQL temporário para gerar classes JOOQ
RUN mkdir -p /var/run/postgresql && chown -R postgres:postgres /var/run/postgresql

# Script para iniciar PostgreSQL e aguardar inicialização
COPY . /app
WORKDIR /app

# Inicia PostgreSQL, configura e executa schema SQL
RUN service postgresql start && \
    echo "Aguardando PostgreSQL iniciar..." && \
    until pg_isready; do sleep 1; done && \
    su postgres -c "psql -c \"ALTER USER postgres WITH PASSWORD 'admin';\"" && \
    su postgres -c "createdb -O postgres empresa" && \
    su postgres -c "psql -d empresa -f /app/src/main/resources/schema.sql" && \
    # Gera classes JOOQ e constrói o JAR
    mvn clean package -Dspring.datasource.url=jdbc:postgresql://localhost:5432/empresa \
    -Dspring.datasource.username=postgres \
    -Dspring.datasource.password=admin -DskipTests

# Estágio final
FROM eclipse-temurin:22-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/src/main/resources ./resources

# Instala netcat para o healthcheck no entrypoint
RUN apt-get update && apt-get install -y netcat-openbsd && apt-get clean

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "while ! nc -z db 5432; do sleep 3; done; java -jar app.jar"]