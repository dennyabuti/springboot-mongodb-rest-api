FROM java 

#Package verification key
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 9DA31620334BD75D9DCB49F368818C72E52529D4

#Add mongodb repository source list
RUN echo "deb http://repo.mongodb.org/apt/debian jessie/mongodb-org/4.0 main" | tee /etc/apt/sources.list.d/mongodb-org-4.0.list

#update packages
RUN apt-get update

# install mongodb
RUN apt-get install -y mongodb-org

# Create the default data directory
RUN mkdir -p /data/db

#VOLUME /tmp/data/db 

EXPOSE 27017
CMD ["mongod"]
RUN mkdir /app
COPY . /app

EXPOSE 8080
WORKDIR  /app
CMD echo pwd
RUN ./gradlew clean build
ENTRYPOINT java -jar /app/build/libs/springboot-mongodb-rest-api-0.1.0.jar

