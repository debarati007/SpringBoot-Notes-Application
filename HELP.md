# Read Me First
The following was discovered as part of building this project:

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/#build-image)

#Docker
* Docker is like a container that allows developer to package an application with all required libraries such as jdk,sql etc
required dependencies and ship it all in one package. Since in test,dev,prod all env code can run from docker container, 
it will be consistent throughout all env. It occupies memory in host OS not in guest OS, so it is memory efficient as well.

Developer writes docker file. Using that a docker image is created. Which is the skeleton of the application. Docker container is 
running instance of docker image which is kept in docker hub. Similar to maven central repository, stage and production servers are pulling
the docker image from docker hub. 

Go to hub.docker.com to search and download any dependency image such as mysql.

##Steps to dockerize a springboot+mysql application

Hack : (Not recommended for prod use) To work in local, I have followed the below steps-
* in mysql command line client -> GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost';
* UPDATE mysql.user SET host='%' WHERE user='root';  
* Restart mysql server.
  
  
* In project pom.xml add name of jar file under finalName tag
* Add a Dockerfile in project as given here and add the required details and in application.properties use "host.docker.internal:3306" as host
url instead of localhost:3306.
* Build the jar using mvn clean install.
* Build spring boot docker image using docker build -t <jar name> .
Use docker images to see the present images.
* Install mysql in docker using docker pull mysql
* Run mysql using the following command
docker run -p 3307:3306 --name mysqldb  -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=<your database name. Here "notes_app"> mysql
* Create a new network where 2 separate containers i.e spring-boot and mysql can interact.
    * docker netwrok create spring-net
    * docker network ls
    * docker network connect mysqldb
    * docker container inspect mysqldb
* Run mysql and springboot application in the newly created spring-net network using below command.
docker run -p 9090:8080 --name easy-notes.jar --net spring-net -e MYSQL_HOST=mysqldb -e MYSQL_USER=root -e MYSQL_PASSWORD=password -e MYSQL_PORT=3306 easy-notes.jar  
* If it runs successfully, we can access all apis in docker port. Here at localhost:9090/api/notes.
    

