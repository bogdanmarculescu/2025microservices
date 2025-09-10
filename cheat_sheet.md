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

``` java -jar resolver-0.0.1-SNAPSHOT.jar ```

Run with and without additional parameters:

``` mvn spring-boot:run ```

``` mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8005```

### Rabbit MQ

``` rabbitmq-server ```

### Docker 
Run RabbitMQ in docker:
```docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management```

Network
```docker network create micro2025```
Run rabbit in network:
``` docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 --network micro2025 rabbitmq:4-management ```

#### Building Docker images:

##### Option 1:
- Compile and package the project with: ``` mvn clean package ```
- Write Dockerfile
- Run ``` docker build -t resolver:0.0.1 . ```