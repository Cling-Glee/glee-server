FROM openjdk:11-jdk-slim
WORKDIR /app
ARG JAR_FILE=./build/libs/glee-interfaces-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/app.jar
ENTRYPOINT ["java","-jar", "app.jar"]