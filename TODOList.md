# TO DO List

### Various notes and tasks

#### Active:

- [ ] Gateway
- [ ] Know if my service is running
  - [x] Health check - is an individual service running?
  - [x] Discovery - which services are running - overview
  - [ ] Running multiple instances of the same service
  - [x] Routing
  - [ ] Load balancing
  - [ ] Gateway for frontend


- [ ] Better handling of startup
  - [ ] Automated startup

- [ ] Access control
- [ ] Smarter handling of logs
  - [ ] Get access to logs
  - [ ] Merge logs into one
- [ ] Docker
  - [ ] Startup procedure
  - [x] manual building of containers
- [ ] mvn spring-boot:build-image - debug ?
- 
#### TODO for later:
- [ ] docker compose



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

