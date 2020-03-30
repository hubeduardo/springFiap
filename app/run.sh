#!/bin/sh

echo "********************************************************"
echo "Wait for mongodb to be available"
echo "MONGODB_STATUS_HOST: $MONGODB_STATUS_HOST"
echo "MONGODB_URI: $MONGODB_URI"
echo "********************************************************"

while ! nc -z $MONGODB_STATUS_HOST $MONGODB_STATUS_PORT; do
  printf 'mongodb is still not available. Retrying...\n'
  sleep 3
done

echo "********************************************************"
echo "Starting app"
echo "********************************************************"

java -Dspring.profiles.active=local \
     -Dserver.port=$SERVER_PORT \
     -Dspring.data.mongodb.uri=$MONGODB_URI \
     -jar /usr/local/batch/app-0.0.1-SNAPSHOT.jar


echo "********************************************************"
echo "Wait for transaction by script python"
echo "********************************************************"

pip install easy_install pip
pip install python
pip install py-pip
pip install requests

exec python /usr/local/api/transactions.py