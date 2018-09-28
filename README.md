# springboot-mongodb-rest-api [![CircleCI](https://circleci.com/gh/dennyabuti/springboot-mongodb-rest-api.svg?style=svg)](https://circleci.com/gh/dennyabuti/springboot-mongodb-rest-api)

MongoDB restful api with Spring Boot
### Running
- clone the Git repository (`git clone https://github.com/dennyabuti/springboot-mongodb-rest-api.git`)
- `cd springboot-mongodb-rest-api`
- build the application by running  `./gradlew clean build` on a linux based system
- once the build is complete cd into `build/libs/`
- run `java -jar build/libs/springboot-mongodb-rest-api-0.1.0.jar`
- on a web browser of your choice go to `http://localhost:8080/swagger-ui.html`

### Docker
- clone the Git repository (`git clone https://github.com/dennyabuti/springboot-mongodb-rest-api.git`)
- `cd springboot-mongodb-rest-api`
- run `docker build -t sbmra ./`
- once the build is complete run `docker run -p 8080:8080 --name web_app -it sbmra bash`
- on a web browser of your choice go to `http://localhost:8080/swagger-ui.html`
