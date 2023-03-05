FROM openjdk:17.0.2-jdk-slim
EXPOSE 8080
VOLUME /tmp
COPY target/*.jar app.jar
HEALTHCHECK --interval=1m --timeout=3s CMD wget -q -T 3 -s http://localhost:8080
