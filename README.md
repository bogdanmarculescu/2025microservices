2025 - PG3402 - Microservices

# Table of contents:

- [Starting up](#starting-up).
- [Overview of the topics covered](TopicOverview.md).
- [An (incomplete) cheat sheet of the commands used](cheat_sheet.md).
- [Older startup  procedure for running services locally](StartupProcedure.md).
- [Running ToDo list](TODOList.md).
- [Docker compose files](docker).

## Starting Up

#### Simple startup:

1. Switch to Docker folder 
    ```
    cd docker/
   ```
   2. Startup:
      - one  instance of each service
        ``` 
          docker  compose  up
        ```
      - scaling for several Resolvers
        ``` 
          docker  compose  up scale resolver=3
        ```
      - in detached mode
        ``` 
          docker  compose  up scale resolver=3 -d
        ```
      - while forcing a build
        ``` 
          docker  compose  up scale resolver=3  --build -d
        ```
      - from a specific file
        ``` 
          docker compose \
            -f docker-compose-v3.yml \
            up \
            --scale resolver=3 \
            --build \
            -d
        ```


Card SVGs from:
https://code.google.com/archive/p/vector-playing-cards/