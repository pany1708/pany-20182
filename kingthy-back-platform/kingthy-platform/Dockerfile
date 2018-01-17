FROM java:8
MAINTAINER pany

ADD ./target/kingthy-back-platform-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/kingthy-back-platform-0.0.1-SNAPSHOT.jar","--spring.profiles.active=test"]

EXPOSE 8088