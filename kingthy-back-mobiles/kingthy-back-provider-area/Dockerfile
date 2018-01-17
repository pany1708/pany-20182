FROM registry.zenmme.net/ops/alpine-java:8_jdk
MAINTAINER pany

ADD ./target/kingthy-back-provider-area-0.0.2-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/kingthy-back-provider-area-0.0.1-SNAPSHOT.jar","--spring.profiles.active=test"]

EXPOSE 7997