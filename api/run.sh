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
echo "Starting api"
echo "********************************************************"

java -Dspring.profiles.active=local \
     -Dserver.port=$SERVER_PORT \
     -Dspring.data.mongodb.uri=$MONGODB_URI \
     -jar /usr/local/api/api-user-0.0.1-SNAPSHOT.jar