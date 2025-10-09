# Cheat sheet 
commands and other relevant issues

### Frontend

Install dependencies: ``` npm install ``` 

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

``` 
docker run -it --rm --name rabbitmq \
-p 5672:5672 \
-p 15672:15672 \
--network micro2025 \
-d rabbitmq:4-management 
```


Postgres in docker


```
docker run -it --rm --name postgresresolver \
  -e POSTGRES_USER=serviceuser \
  -e POSTGRES_PASSWORD=servicepwd \
  -e POSTGRES_DB=resolver \
  -p 5432:5432 \
  -v resolver:/var/lib/postgresql/data \
  --network micro2025 \
  -d postgres:15
```

```
docker run -it --rm --name postgresrounds \
  -e POSTGRES_USER=serviceuser \
  -e POSTGRES_PASSWORD=servicepwd \
  -e POSTGRES_DB=rounds \
  -p 5433:5432 \
  -v rounds:/var/lib/postgresql/data \
  --network micro2025 \
  -d postgres:15
```

#### Building Docker images:

##### Option 1:
- Compile and package the project with: ``` mvn clean package ```
- Write Dockerfile
- Build  image: ``` docker build -t [container_name] . ```
- Example: ``` docker build -t resolver:0.0.1 . ```

##### Option 2
- Maven: ``` mvn spring-boot:build-image ```

#### Running Docker images

Example: 

``` docker run --name manual_round --network micro2025 -p 8000:8000 manual_round ```


``` docker run --name resolver --network micro2025 -p 8005:8005 resolver:0.0.1-SNAPSHOT ```

``` 
docker run -it --rm --name deck \
    --network micro2025 \
    -p 8001:8001 \
    -d deck:0.0.1-SNAPSHOT 
    
```

``` 
docker run -it --rm --name automaplayer \
    --network micro2025 \
    -p 8003:8003 \
    -d automaplayer:0.0.1-SNAPSHOT 
    
```

```
docker run -it --rm --name resolver \
    --network micro2025 \
    -p 8005:8005 \
    -d resolver:0.0.1-SNAPSHOT 
```

```
docker run -it --rm --name round \
    --network micro2025 \
    -p 8000:8000 \
    -d round:0.0.1-SNAPSHOT 
```

``` docker run --name frontend --network micro2025 -p 5173:5173 frontend:0.1 ```

##### Startup Procedure 2025-09-16

``` docker network create micro2025 ```
``` docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 --network micro2025 rabbitmq:4-management ```

``` docker build -t frontend:0.1 . ```
``` docker run --name frontend --network micro2025 -p 5173:5173 frontend:0.1 ```

``` mvn spring-boot:build-image ```
``` docker run --name round --network micro2025 -p 8000:8000 round:0.0.1-SNAPSHOT ```

##### Startup Procedure 2025-09-18

``` docker network create micro2025 ```
``` docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 --network micro2025 rabbitmq:4-management ```

```
docker run -it --rm --name postgresresolver \
  -e POSTGRES_USER=serviceuser \
  -e POSTGRES_PASSWORD=servicepwd \
  -e POSTGRES_DB=resolver \
  -p 5432:5432 \
  -v resolver:/var/lib/postgresql/data \
  --network micro2025 \
  -d postgres:15
```

```
docker run -it --rm --name postgresrounds \
  -e POSTGRES_USER=serviceuser \
  -e POSTGRES_PASSWORD=servicepwd \
  -e POSTGRES_DB=rounds \
  -p 5433:5432 \
  -v rounds:/var/lib/postgresql/data \
  --network micro2025 \
  -d postgres:15
```

``` docker build -t frontend:0.1 . ```
``` docker run --name frontend --network micro2025 -p 5173:5173 frontend:0.1 ```

``` mvn spring-boot:build-image ```
``` docker run --name round --network micro2025 -p 8000:8000 round:0.0.1-SNAPSHOT ```

#### Start a service, in test profile

```
     mvn clean verify -Dspring.profiles.active=test
```


check config:
```
docker exec -it deck wget -qO- http://consul:8500/v1/kv/config/deck/application-docker.yml?raw

```





