FROM registry.zenmme.net/ops/alpine-java:8_jdk
MAINTAINER pany

ADD ./target/order-facade-impl-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "/app/order-facade-impl-0.0.1-SNAPSHOT.jar"]

EXPOSE 7700