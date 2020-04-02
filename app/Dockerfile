FROM openjdk:8-jre-alpine

RUN apk add --update bash && rm -rf /var/cache/apk/*

USER root
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN apk add --update python
RUN apk add --update py-pip
RUN pip install requests==2.21.0
RUN pip install requests-futures==0.9.9

RUN mkdir -p /usr/local/batch

ADD build/libs/app-0.0.1-SNAPSHOT.jar /usr/local/batch/app-0.0.1-SNAPSHOT.jar
ADD run.sh run.sh
ADD transactions.py /usr/local/batch/transactions.py
ADD src/main/resources/base.txt /usr/local/batch/base.txt

RUN chmod +x run.sh
RUN chmod +x /usr/local/batch/transactions.py

CMD sh run.sh