# Starting up 

### Docker and Infrastructure services

1. Creating the Docker network for our services:

- ```docker network create micro2025```

2. RabbitMQ - 

``` 
docker run -it --rm --name rabbitmq \
-p 5672:5672 \
-p 15672:15672 \
--network micro2025 \
-d rabbitmq:4-management 
```

3. The Postgres Service(s) - we have 2 at the moment

- a. Postgres for resolver

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

- b. Postgres for rounds

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

### Building our services

4. Frontend - built from Docker file. 
- a. Build:  ``` docker build -t frontend:0.1 . ```
- b. Run: ``` docker run -d --rm --name frontend --network micro2025 -p 5173:5173 frontend:0.1 ```

5. Build and run the other services:
Build: 
- a. ``` ls [service_folder] ```
- b. Build ``` mvn spring-boot:build-image ```

OR 

``` mvn clean package ``` +
``` docker build -t [image_name] . ```

- c. Run: 
- ``` 
  docker run [-d] [--rm] --name [service_name] 
  --network [network_name] 
  -p 8000:8000 
  [image_name] 
  ```

- where 
  - ```[-d]``` => run in detached mode (i.e. not tied to the terminal)
  - ```[--rm]``` => remove when done

      - a. Round - Central service
- ```
    docker run -d --rm --name round --network micro2025 -p 8000:8000 round:0.0.1-SNAPSHOT 
  ```

    - b. Deck - handles cards and shuffling
- ``` 
    docker run -d --rm --name deck --network micro2025 -p 8001:8001 deck:0.0.1-SNAPSHOT 
  ```

    - c. AutomaPlayer - handles the card play by an automated player
- ``` 
  docker run -d --rm --name automaplayer --network micro2025 -p 8003:8003 automaplayer:0.0.1-SNAPSHOT 
  ```

    - d. Resolver - resolves the round and stores results
- ``` 
  docker run -d --rm --name resolver --network micro2025 -p 8005:8005 resolver:0.0.1-SNAPSHOT 
  ```

    - e. Run resolver again
- ```
  docker run -d --rm --name resolver2 \
  --network micro2025 \
  -p 8006:8005 \
  resolver:0.0.1-SNAPSHOT  
  ```

- Gateway:

```
  docker run -d --rm --name gateway \
  --network micro2025 \
  -p 8100:8100 \
  gateway:0.0.1-SNAPSHOT  
  ```

#### Additional useful docker commands

1. ``` docker stop $(docker ps -aq) && docker rm $(docker ps -aq) ```

What is does:
- ``` docker ps -aq ``` => Get ids for all running containers
- ``` docker stop (ids)``` => stop containers with the ids identified above
- ```docker rm (ids) ``` => Remove containers with those ids

2. ```docker image prune -a ``` => removes all images without a container (i.e. that are not running). Useful when cleaning up old images. 
- Warning: This makes the next startup slower.

3. ```docker volume prune -a``` => removes all volumes not in use (database storage, for example)

4.  ``` docker ps ``` => check running containers
5.  ``` docker images ```  => check available images 



Adding consul:
```
docker run -d --rm --name=consul \
-p 8500:8500 \
-p 8600:8600/udp \
--network=micro2025 \
hashicorp/consul agent -dev -client 0.0.0.0 -node=cards2025
```

Locally: ``` consul agent -dev -node cards ```