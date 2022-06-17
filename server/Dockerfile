FROM openjdk:18-bullseye

RUN apt-get update && \
    apt-get install -y build-essential maven default-jdk figlet netcat telnet tree && \
    update-alternatives --config javac

RUN mkdir app
WORKDIR app

COPY src ./src
COPY pom.xml .
COPY target/webapp-insecure-1.0-SNAPSHOT.jar .
RUN tree

#CMD ["sleep", "3600"]
#CMD ["mvn", "spring-boot:run"]
CMD ["java", "-jar", "webapp-insecure-1.0-SNAPSHOT.jar"]
