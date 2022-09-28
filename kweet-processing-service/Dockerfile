FROM openjdk:17
ARG JAR_FILE=target/kweet-processing-service.jar
COPY ${JAR_FILE} kweet-processing-service.jar
ENTRYPOINT ["java","-jar","/kweet-processing-service.jar"]