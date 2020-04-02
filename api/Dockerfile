FROM openjdk:8-jre-alpine

RUN apk add --update bash && rm -rf /var/cache/apk/*

USER root
RUN apk update && apk upgrade && apk add netcat-openbsd

RUN mkdir -p /usr/local/api

ADD build/libs/api-user-0.0.1-SNAPSHOT.jar /usr/local/api/api-user-0.0.1-SNAPSHOT.jar
ADD run.sh run.sh

RUN chmod +x run.sh

CMD sh run.sh






