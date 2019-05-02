# Project Title

GraphQL with Spring boot + MongoDB

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development purposes. See deployment for notes on how to deploy the project on a live system.

### Build with

* [Java8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
* [Spring Boot](https://start.spring.io/) - Java web Framework
* [MongoDB](https://hub.docker.com/_/mongo) - NoSql Database run on docker container

### MongoDB and important command line

Install
```
$ docker run -d -p 27017-27019:27017-27019 --name mongodb mongo:latest
```

Access MongoDB console
```
$ docker exec -it mongodb bash

Then 

/# mongo
```

### Start Application
```
$ gradlew bootRun
```
