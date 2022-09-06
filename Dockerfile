FROM openjdk:8-jdk-alpine
MAINTAINER asepsaputra.medium.com
COPY build/libs/GraphQL-0.0.1-SNAPSHOT.jar GraphQL.jar
ENTRYPOINT ["java","-jar","/GraphQL.jar"]