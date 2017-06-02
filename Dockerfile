# dockerfile
FROM java:8

RUN apt-get update
RUN apt-get install -y maven

WORKDIR /

ADD pom.xml /code/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

ADD src /src
RUN ["mvn", "package"]

EXPOSE 8080
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/sparkexample-jar-with-dependencies.jar"]