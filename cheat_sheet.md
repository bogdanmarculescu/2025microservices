# Cheat sheet 
commands and other relevant issues

### Frontend

Install depenencies: ``` npm install ``` 

Run frontend in dev mode: ``` npm run dev```

### Maven

Compile and install: 

``` mvn clean install ```

``` mvn clean package```

Run as .jar

``` java -jar [filename.jar] ```
Example: ``` java -jar resolver-0.0.1-SNAPSHOT.jar ```


Run with and without additional parameters:

``` mvn spring-boot:run ```

``` mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8005```

### Rabbit MQ

``` rabbitmq-server ```

### Docker 
Run RabbitMQ in docker:
```docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management```

Network
```docker network create [network_name] ```
Example: ```docker network create micro2025```
Run rabbit in network:
``` docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 --network micro2025 rabbitmq:4-management ```

#### Building Docker images:

##### Option 1:
- Compile and package the project with: ``` mvn clean package ```
- Write Dockerfile
- Build  image: ``` docker build -t [container_name] . ```
- Example: ``` docker build -t resolver:0.0.1 . ```

##### Option 2
- Maven: ``` mvn spring-boot:build-image ```

#### Running Docker images

Example: ``` docker run --name manual_round --network micro2025 -p 8000:8000 manual_round ```

``` docker run --name frontend --network micro2025 -p 5173:5173 frontend:0.1 ```

##### Startup Procedure 2025-09-16

``` docker network create micro2025 ```
``` docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 --network micro2025 rabbitmq:4-management ```

``` docker build -t frontend:0.1 . ```
``` docker run --name frontend --network micro2025 -p 5173:5173 frontend:0.1 ```

``` mvn spring-boot:build-image ```
``` docker run --name round --network micro2025 -p 8000:8000 round:0.0.1-SNAPSHOT ```