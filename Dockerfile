FROM ubuntu:20.04

WORKDIR /app

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

COPY . /app

RUN mvn clean install -DskipTests

# application inside the container will listen on port 8083???
EXPOSE 8083

CMD ["java", "-jar", "/app/target/imageUpload-0.0.1-SNAPSHOT.jar"]
