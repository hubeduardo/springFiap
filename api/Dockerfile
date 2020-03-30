FROM openjdk:8-jre-alpine

RUN apk add --update bash && rm -rf /var/cache/apk/*

USER root
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN apk add --update python
RUN apk add --update py-pip

RUN mkdir -p /usr/local/api
ADD build/libs/api-user-0.0.1-SNAPSHOT.jar /usr/local/api/api-user-0.0.1-SNAPSHOT.jar
ADD src/main/resources/base.txt /usr/local/api/base.txt

ADD run.sh run.sh
RUN chmod +x run.sh

ADD job.sh job.sh
RUN chmod +x job.sh

ADD transactions.py /usr/local/api/transactions.py
RUN chmod +x /usr/local/api/transactions.py

CMD ./run.sh
CMD ./job.sh






