FROM openjdk:8
EXPOSE 8080
COPY . src/java
WORKDIR src/java
CMD java -jar target/messenger.jar