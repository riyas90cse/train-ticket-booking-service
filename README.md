# Train Ticket Booking Application

### A Coding Challenge 

##### Problem Statement On Challenge

##### App to be coded:

As a User I want to board a train from London to France. The train ticket will cost $20.

* Create API where you can submit a purchase for a ticket. 
* Details included in the receipt are: From, To, User , price paid.
* User should include first and last name, email address
* The user is allocated a seat in the train. Assume the train has only 2 sections, section A and section B.
* An API that shows the details of the receipt for the user
* An API that lets you view the users and seat they are allocated by the requested section
* An API to remove a user from the train
* An API to modify a user's seat


### Build And Run Application [Local / Production(Docker)] Environment
* To Build and Run Application Go To Root Folder Run the below Command based on your env
* To Run Local the Default Profile Should be active as ${SPRING_PROFILES_ACTIVE} = local
* To Run Local the Docker Profile Should be active as ${SPRING_PROFILES_ACTIVE} = docker (Which can be used for production / Stage)
* Not required to Install Gradle Locally in You System nor in docker it's been used by wrapper itself by application
* To Build Application Locally
    - Gradle Build Windows command `.\gradlew clean build`
    - Gradle Build Linux / Mac command `./gradlew clean build`
* To Run Application Locally
    - Gradle Boot Run Linux / Mac command `./gradlew bootRun`
    - Gradle Boot Run Windows command: `.\gradlew bootRun`
    -  `./gradlew bootRun --spring.profiles.active='local' or 'docker'`
* To Run Locally Don't Need to Install any DB we used H2 In-memory DB for Local Env
* To Build Application Docker(Production or Stage) - `TODO Fix is Pending to Run Smoothly`
    - From Root Folder run the command `docker-compose build` && `docker-compose up -d`
    - Once the command finishes application is deployed in docker with dependent db, liquibase in docker compose itself.
* For both local or docker we are using liquibase migration for ddl no need to create db. Our Application will be taken care for those.
* When we run clean build on gradle it will run test as well, but if we want to run test separately we can use below command to run the entire test or by individual tests
* Commands Are Below for running all the tests
    - Gradle Command Windows command `.\gradlew test`
    - Gradle Command Linux / Mac command `./gradlew test` 

## Application Documentations Details

- API Contract Document Available in [Api-contract.md](./ApiContract.md)
- Tools and Tech Stacks Used to Develop [Help.md](./HELP.md)
- Feature Discussion Details Proposal of Enhancement Information [Proposal.md](./Proposal.md)

## Useful Links

- Local Swagger UI URL : [Swagger-UI](http://localhost:8090/swagger-ui/index.html#/)
- Docker Swagger UI URL : [Swagger-UI](http://localhost:8090/swagger-ui/index.html#/)
- DB-H2 URI: [H2-DB](http://localhost:8090/h2-console)
- For Docker DB is PostgresSQL that needs to be connected with Any DB-Client details available in [docker-compose.yaml](./docker-compose.yaml)
### Database Configuration Available [application.yml](./src/main/resources/application.yml)