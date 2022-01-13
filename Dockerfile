FROM openjdk:8-alpine
EXPOSE 8080
ADD target/easy-notes.jar easy-notes.jar
ENTRYPOINT ["java","-jar","/easy-notes.jar"]