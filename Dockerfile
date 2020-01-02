FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/ConversationMicroservice-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} ConversationMicroservice.jar
ENTRYPOINT ["java","-jar","ConversationMicroservice.jar"]