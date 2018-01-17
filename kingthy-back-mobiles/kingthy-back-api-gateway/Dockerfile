FROM registry.zenmme.net/ops/alpine-java:8_jdk
MAINTAINER pany

ADD ./target/kingthy-back-api-gateway-0.0.2-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "/app/kingthy-back-api-gateway-0.0.2-SNAPSHOT.jar"]

EXPOSE 8989