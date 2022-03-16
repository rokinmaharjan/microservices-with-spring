FROM openjdk:17-slim
LABEL maintainer="Rokin Maharjan <rokinmaharjan@gmail.com>"

# For healthcheck
RUN apt-get update
RUN apt-get install -y curl

COPY target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1.jar
ENTRYPOINT ["java","-jar","/config-server-0.0.1.jar"]