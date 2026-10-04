FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:17-jre
RUN apt-get update && apt-get install -y --no-install-recommends \
    libgtk-3-0 libxtst6 libxrender1 libxi6 libgl1 libxxf86vm1 fonts-dejavu \
    && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY --from=build /build/target/tempConverter-1.0-SNAPSHOT.jar app.jar
COPY --from=build /build/target/lib ./lib
ENV DB_HOST=host.docker.internal
ENTRYPOINT ["java", "-Dprism.order=sw", "-jar", "app.jar"]