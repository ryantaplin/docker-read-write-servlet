# Docker-Write Application ![Travis build status](https://travis-ci.org/RyanTaplin1705/docker-write-servlet.svg?branch=master "Build Status")

As a placement student at Sky I have spent the past few months being exposed to the technology industry and their practices. One area that has interested me is the rise of scalable distrituable container platforms such as Docker.

Docker has been the first piece of exposure to such platforms and as part of my own investigation I plan to create two indepdent application that run side by side. A docker-write-servlet and a docker-read-servlet.

# The Implementation
The Docker-Write-Servlet will be accept various post requests of which contains body type of JSON. This JSON will be unmarshelled and validated; if successfully validated it will be persisted to an instance of [MySQL](https://www.mysql.com/) (of which will hopefully be running in a Docker container). 

The Docker image that will be used can be found [here](https://hub.docker.com/_/mysql/).

Along side this application I will be running a docker-read-servlet which will be reading the persisted data from the same instance of MySQL. At this point in time I foresee the database being a single point of failure but ultimately I would also like to make MySQL scalable. You can read more about the [Docker-Read-Servlet here](https://github.com/RyanTaplin1705/docker-read-servlet/tree/master)

# TODOs
- ~~Apply database write mechanism to application.~~
- Apply database update mechanism to application.
- Apply some logic to input data (unmarshalling/validation/manipulation)
- Create database schema.
- Create script to apply database schema if tables don't exist.
- Think about how to do database versioning. (via script?)
- Successfully deploy MySQL to docker (requires dualboot of Ubuntu)
- Create docker file for application using base JRE image.
- Deploy docker image successfully and get it successfully interact with MySQL container.
- Setup Kubernetes and deploy docker containers under Kubernetes.
