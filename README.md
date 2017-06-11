# Docker-Read-Write Application ![Travis build status](https://travis-ci.org/RyanTaplin1705/docker-read-write-servlet.svg?branch=master "Build Status")

As a placement student at Sky I have spent the past few months being exposed to the technology industry and their practices. One area that has interested me is the rise of scalable distrituable container platforms such as Docker.

Docker has been the first piece of exposure to such platforms and as part of my own investigation I plan to create two independent application that run side by side. A docker-write-servlet and a docker-read-servlet.

# The Implementation
This application will support a two in one system. A write-servlet and a read-servlet. The role of the application will be determined at run time by the environment variable set by the user. The write and read servlets should run side by side allowing you to persist and read data from the database simultaneously.

**Docker-Write**
The docker-write-servlet will accept various POST requests of which will contain a body of JSON. This JSON will be unmarshelled and validated; if successfully validated it will be persisted to the database. 

**Docker-Read**
The docker-read-servlet will be able to accept various GET requests. Each request will return a body of JSON back with a relevent message and code. This JSON will be formed from data found in the instance of my database - if the value is not found then an error will be returned as a result of this.

**Database**
At this point in time I foresee the database being a single point of failure but ultimately I would also like to make MySQL scalable. My choice of database is currently an instance of [MySQL](https://www.mysql.com/).

**Note: 11/06/17**
Change database to [Oracle](https://hub.docker.com/search/?isAutomated=0&isOfficial=0&page=1&pullCount=0&q=oracle&starCount=0) to support database editioning.

![Diagram of Applications](https://image.ibb.co/ebSXtF/Write_Read_Serv.png)

# Build Docker Image for your Application
1. Run the '**docker build**' command in the project repository.
```
    docker build -t myapp ./
```

# Running the Application (Read & Write)
1. Run the '**docker network create**' command to resolve containers by name. For more info [visit](http://www.dasblinkenlichten.com/docker-networking-101-user-defined-networks/).
```
docker network create --driver=bridge \
--subnet=192.168.127.0/24 --gateway=192.168.127.1 \
--ip-range=192.168.127.128/25 mybridge
```

2. Start up an instance of your database from [Dockerhub](https://hub.docker.com/_/mysql/) by running the '**docker run**' command.
```
    docker run --name --net=mybridge mysql -e MYSQL_ROOT_PASSWORD=pass -d mysql:tag
```

3. Run the '**docker run**' or '**docker service create**' command to start your application.
```
    docker run --name write-servlet --net=mybridge -e ENVIRONMENT=docker -e ROLE=WRITE -p 8082:8080 -d -it myapp
    docker run --name read-servlet --net=mybridge -e ENVIRONMENT=docker -e ROLE=READ -p 8082:8080 -d -it myapp
```

| Env Var       | Value                    |
| ------------- |:------------------------:|
| ENVIRONMENT   | localhost / docker / ci  |
| ROLE          | READ / WRITE             |


# Running the Application (Read & Write) with distribution
1. Run the '**docker network create**' command to resolve containers by name via docker services.
```
docker network create -d overlay --subnet=10.10.10.0/24 myoverlay
```

2. Run the '**docker swarm**' command to start a swarm; this will initialise your host as a manager for container distribution and load balancing.
```
docker swarm init
```

3. Start up an instance of your database from [Dockerhub](https://hub.docker.com/_/mysql/) by running the '**docker service create**' command.
- replicas is the number of instances of your application that will be running at any given time. 
- You must also have created a network overlay **not** a bridge.

```
docker service create --name mysql --replicas 1 --network=myoverlay -p 3306:3306 -e MYSQL_ROOT_PASSWORD=pass -d mysql
```

4. Start up 1 or more instances of your application by runing the '**docker service create**'.
```
docker service create --replicas 3 --name app1 --network=myoverlay  -e ENVIRONMENT=docker -e ROLE=READ -p 8081:8080 -d -t myapp
docker service create --replicas 3 --name app2 --network=myoverlay  -e ENVIRONMENT=docker -e ROLE=WRITE -p 8082:8080 -d -t myapp
```


# MySQL Running Statements Manually
1. Run the '**docker exec**' command to go inside the container and run mysql commands.
```
    docker exec -it mysql /bin/bash
```

2. Run the '**mysql**' command to enter mysql console mode. Here you can apply your schema.
```
root@b7641d3ecf53:/# mysql
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 3
Server version: 5.7.18 MySQL Community Server (GPL)

Copyright (c) 2000, 2017, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
```

# TODO - Write Servlet
- ~~Apply database write mechanism to application.~~
- ~~Apply database update mechanism to application~~.
- ~~Apply some logic to input data (unmarshalling/validation/manipulation)~~
- ~~Create database schema~~.
- ~~Create script to apply database schema if tables don't exist~~.
- Think about how to do database versioning. (via script?)
- ~~Successfully deploy MySQL to docker (requires dualboot of Ubuntu)~~
- ~~Create docker file for application using base JRE image~~.
- ~~Deploy docker image successfully and get it successfully interact with MySQL container~~.
- ~~Research: Check if I can reference MySQL container URL by container name in Docker~~.
- Setup Kubernetes and deploy docker containers under Kubernetes.

# TODO - Read Servlet
- ~~Apply database read mechanism to application.~~ (May need to be revisited with more advanced queries).
- Apply some logic to data pulled from data (unmarshalling/validation/manipulation)
- Think about how to do database versioning and how this app will handle it.
- ~~Create docker file for application using base JRE image.~~
- ~~Deploy docker image successfully and get it successfully interact with MySQL container.~~
- Setup Kubernetes and deploy docker containers under Kubernetes.

