#!/bin/sh
/usr/bin/mongod --fork --logpath /var/log/mongodb.log && /usr/bin/mongorestore --db Awesome_Library --drop /seed_data && java -jar /app/build/libs/springboot-mongodb-rest-api-0.1.0.jar

