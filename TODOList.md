# TO DO List

### Various notes and tasks

#### Active:

- [ ] mvn spring-boot:build-image - debug ?

#### TODO for later:

- [ ] Access control
- [ ] Smarter handling of logs
    - [ ] Get access to logs
    - [ ] Merge logs into one



#### Solved
- [x] Starting up new services
- [x] Synchronous communication

#### Lecture 6
- [x] Asynchronous communication
    - [x] Sending (Simple) RabbitMQ messsages
    - [x] Receiving RabbitMQ messages
    - [x] Checking message flow in the RabbitMQ interface
    - [x] What if Receiver is offline?
    - [x] Sending objects via RabbitMQ
- [x] What this means for (software) architecture 

#### Lecture 10
- [x] Docker build for the frontend
- [x] Docker with Async communication
    - [x] connect rabbitmq to my resolver
    - [x] connect rabbitmq to round
- [x] Docker with Sync communication
    - [x] Handling host
    - [x] handling ports

#### Lecture 16
- [x] Gateway
- [x] Know if my service is running
    - [x] Health check - is an individual service running?
    - [x] Discovery - which services are running - overview
    - [x] Running multiple instances of the same service
    - [x] Routing
    - [x] Load balancing
    - [x] Gateway for frontend
    - [x] Fixed routing to allow DB checking
    - [x] Consul sees  instances with  different  names
- [x] Adjust gateway to handle resolver calls
- [x] Docker
    - [x] Startup procedure
    - [x] manual building of containers
    - [x] docker compose

- [x] Better handling of startup
    - [x] Automated startup


Code 1: 2242
Code 2: 6283