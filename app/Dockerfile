FROM openjdk:8-jre-alpine

RUN apk add --update bash && rm -rf /var/cache/apk/*

USER root
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN apk add --update python
RUN apk add --update py-pip

RUN mkdir -p /usr/local/batch

ADD build/libs/app-0.0.1-SNAPSHOT.jar /usr/local/batch/app-0.0.1-SNAPSHOT.jar
ADD src/main/resources/base.txt /usr/local/batch/base.txt
ADD transactions.py /usr/local/batch/transactions.py
ADD run.sh run.sh

RUN chmod +x /usr/local/batch/transactions.py
RUN chmod +x run.sh

CMD ./run.sh

EXPOSE 8000