<h1 align="center"> Authorization System </h1> <br>

<p align="center">
  Permission based authorization system for API access control.
</p>


## Table of Contents

- [Introduction](#introduction)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [API](#requirements)




## Introduction

Permission based authorization system for API access control. 
The assignment contains Public-API (anyone can access) & Private-API (only user with particular role should be able to access).


## Features


* Register User.
* Authenticate User.
* Authorization of user based on role(admin, user).
* List Users.



## Requirements
The requirements for each setup are listed below.

* [Java 11 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/download.cgi)




## Quick Start
Make sure the JWT Verification Key is configured, then you can run the server on your local machine.

### Configure JWT Verification Key
Update __application.properties__.

* `spring.datasource.url` - Database server url `(eg: jdbc:mysql://localhost/<project_name>)`
* `spring.jpa.hibernate.ddl-auto` - put "create" to create a database for first time than change it to "update".
* `spring.datasource.username` - to put database username. 
* `spring.datasource.password` - to put database password.

### Run Local
```bash
$ mvn spring-boot:run
```

Application will run by default on port `9000`

Configure the port by changing `server.port` in __application.properties__.



## API

[Swagger Documentation](http://localhost:9000/swagger-ui/index.html)