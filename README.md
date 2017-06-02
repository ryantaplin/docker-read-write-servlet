# Docker-Write Application ![Travis build status](https://travis-ci.org/RyanTaplin1705/docker-write-servlet.svg?branch=master "Build Status")

As a placement student at Sky I have spent the past few months being exposed to the technology industry and their practices. One area that has interested me is the rise of scalable distrituable container platforms such as Docker.

Docker has been the first piece of exposure to such platforms and as part of my own investigation I plan to create two independent application that run side by side. A docker-write-servlet and a docker-read-servlet.

# The Implementation
The Docker-Write-Servlet will be able to accept various POST requests of which contains a body type of JSON. This JSON will be unmarshelled and validated; if successfully validated it will be persisted to an instance of [MySQL](https://www.mysql.com/).

Along side this application I plan to be running a docker-read-servlet. This will be reading the persisted data from the same instance of MySQL. At this point in time I foresee the database being a single point of failure but ultimately I would also like to make MySQL scalable.

The Docker-Read-Servlet will be able to accept various GET requests. Each request will return a body of JSON back with a relevent message and code. This JSON will be formed from data found in the defined instance of MySQL running locally or in docker - if the value is not found then an error will be returned as a result of this.

![Diagram of Applications](https://image.ibb.co/ebSXtF/Write_Read_Serv.png)

# Running the Application (Read & Write)

| Env Var       | Value                          |
| ------------- |:------------------------------:|
| ENVIRONMENT   | "localhost" / "ci" / "docker"  |
| ROLE          | "READ" / "WRITE"               |

# Running MySQL in Docker from [Dockerhub](https://hub.docker.com/_/mysql/)

1. Run the '**docker run**' command to pull mysql image and deploy it locally.
```
docker run --detach --name mysql -e MYSQL_ROOT_PASSWORD=pass -d mysql:tag
```

2. Run the '**docker exec**' command to go inside the container and run mysql commands.
```
docker exec -it mysql /bin/bash
```

3. Run the '**mysql**' command to enter mysql console mode. Here you can apply your schema.
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
- Apply database update mechanism to application.
- Apply some logic to input data (unmarshalling/validation/manipulation)
- Create database schema.
- Create script to apply database schema if tables don't exist.
- Think about how to do database versioning. (via script?)
- ~~Successfully deploy MySQL to docker (requires dualboot of Ubuntu)~~
- Create docker file for application using base JRE image.
- Deploy docker image successfully and get it successfully interact with MySQL container.
- Setup Kubernetes and deploy docker containers under Kubernetes.

# TODO - Read Servlet
- Apply database read mechanism to application. (May need to be revisited with more advanced queries).
- Apply some logic to data pulled from data (unmarshalling/validation/manipulation)
- Think about how to do database versioning and how this app will handle it.
- Create docker file for application using base JRE image.
- Deploy docker image successfully and get it successfully interact with MySQL container.
- Setup Kubernetes and deploy docker containers under Kubernetes.

