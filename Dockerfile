# dockerfile
FROM java:8

RUN apt-get update
RUN apt-get install -y maven

WORKDIR /code

ADD pom.xml /code/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

ADD sql /code/sql
ADD src /code/src
RUN ["mvn", "package"]

ENV MAIN_CLASS_NAME App

EXPOSE 8080
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/${MAIN_CLASS_NAME}-jar-with-dependencies.jar"]