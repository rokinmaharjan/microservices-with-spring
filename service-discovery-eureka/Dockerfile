FROM openjdk:17-slim
LABEL maintainer="Rokin Maharjan <rokinmaharjan@gmail.com>"

# For healthcheck
RUN apt-get update
RUN apt-get install -y curl

COPY target/service-discovery-eureka-0.0.1-SNAPSHOT.jar service-discovery-eureka-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/service-discovery-eureka-0.0.1-SNAPSHOT.jar"]