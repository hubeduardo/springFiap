#!/bin/sh
echo "########## START $OSTYPE" ##########"

echo "********************************************************"
echo "Generate binary files for batch app"
echo "********************************************************"

cd app && ./gradlew bootJar && cd ..

echo "********************************************************"
echo "Generate binary files for service api"
echo "********************************************************"

cd api && ./gradlew bootJar && cd ..

echo "********************************************************"
echo "Run compose docker to create environments"
echo "********************************************************"

docker-compose up -d --build
#docker-compose up

echo "********************************************************"
echo "Starting transactions"
echo "********************************************************"

RUN apk update && apk upgrade && apk add netcat-openbsd
RUN apk add --update python
RUN apk add --update py-pip
pip install easy_install pip
pip install python
pip install py-pip
pip install requests==2.21.0
pip install requests-futures==0.9.9

cd app && python transactions.py