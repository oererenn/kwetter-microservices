FROM openjdk:17
ARG JAR_FILE=target/timeline-service.jar
COPY ${JAR_FILE} timeline-service.jar
ENTRYPOINT ["java","-jar","/timeline-service.jar"]