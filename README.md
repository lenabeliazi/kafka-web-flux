### Author : Léna BELIAZI

### Spring WebFlux and Kafka configuration
This project will guide you through the Spring WebFlux and Kafka configuration and setup.
You will see how we are able to make calls to a reactive controller whilst submitting and consuming data from a Kafka topic.

### Versions
To launch this project, you need Java 17+.

### Pre requisites
[Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)<br/>
[Docker](https://www.docker.com/products/docker-desktop)<br/>

### Build and run te application
You will need to upload this project as a Maven Project.
You will then be able to build the project with : 
```bash
mvn clean install
```
You can then start the Spring Boot application normally.
The docker compose should launch as you run the application, if it doesn't don't hesitate to use this command : 
```bash
docker compose up -d
// docker compose down if you need to shut them down
```

### Understanding the code
In the docker compose, you will find two main services: mongodb (simulation of a database), and Kafka (simulation of a Kafka instance).
Feel free to look at the different parameters and understand what they are used for.
Take a look at each of the files in the project, this will help you understand how WebFlux and Kafka are configured and then used together.

### Testing
You can finally test the services using those three endpoints:
(GET)http://localhost:8000/api/users/ -> to get a list of all the users
(POST)http://localhost:8000/api/users/ -> to create a user (you must created a json body)
(GET)http://localhost:8000/api/users/{userId}-> to get a specific user by id

You can also take a loook at the UserControllerTest, which shows you how to use the WebTestClient annotation.