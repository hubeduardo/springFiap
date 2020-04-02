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