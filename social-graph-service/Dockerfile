FROM openjdk:17
ARG JAR_FILE=target/social-graph-service.jar
COPY ${JAR_FILE} social-graph-service.jar
ENTRYPOINT ["java","-jar","/social-graph-service.jar"]