FROM java:8-jre
WORKDIR /
ADD target/fortune-1.0-SNAPSHOT.jar fortune-1.0-SNAPSHOT.jar
ADD test.yml test.yml
ADD rules.drl rules.drl
EXPOSE 8080 8081
CMD java -jar fortune-1.0-SNAPSHOT.jar server test.yml
