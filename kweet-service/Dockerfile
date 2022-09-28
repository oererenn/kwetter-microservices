FROM openjdk:17
ARG JAR_FILE=target/kweet-service.jar
COPY ${JAR_FILE} kweet-service.jar
ENTRYPOINT ["java","-jar","/kweet-service.jar"]