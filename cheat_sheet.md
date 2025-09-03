# Cheat sheet 
commands and other relevant issues

### Frontend

Install depenencies: ``` npm install ``` 

Run frontend in dev mode: ``` npm run dev```

### Maven

Compile and install: 

``` mvn clean install ```

``` mvn clean package```

Run with and without additional parameters:

``` mvn spring-boot:run ```

``` mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8005```

### Rabbit MQ

``` rabbitmq-server ```

### Docker 

```docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management```
