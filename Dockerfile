FROM openjdk:8
COPY . src/java
WORKDIR src/java
CMD mvn clean install -DskipTests assembly:single -q
CMD java -jar target/messenger.jar