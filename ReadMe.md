# Account Opening Enterprise Test

### Overview
This is an API test project providing a set of endpoints and functionality to be used in an enterprise environment. The 
project provisions a list of endpoints and functionalities including but not limited to:
- Endpoint for customer profile creation
- Endpoint for current account creation
- Endpoint to obtain the list of transactions related to an account

### Project Structure
The structure of the project is mainly in three parts:
- `doc/adrs` folder - discussions around the decisions taken in the implementation of this project is discussed
- `AccountService` module - containing the Accounts service for customer account/profile management
- `TransactionService` module - containing the Transactions service for transaction management

### Install/Usage
#### Installation Requirements
- Java 17
- Maven
- SpringBoot v3.1.1

#### Run
Testing and running the application. You need the `AccountService` and `TransactionService` running to 
experience the full functionality of the project.

#### Starting and testing the `AccountService`

Navigate to the service module/folder in a different terminal:
```bash
cd ./AccountService
mvn clean install
mvn spring-boot:run
```
- Runs on port 1010
- The service can then be accessed through OpenAPI/Swagger using in http://localhost:1010/swagger-ui/index.html

#### Starting and testing the `TransactionService`

Navigate to the service module/folder in a different terminal:
```bash
cd ./TransactionService
mvn clean install
mvn spring-boot:run
```
- Runs on port 1011
- The service can then be accessed through OpenAPI/Swagger using in http://localhost:1011/swagger-ui/index.html

### Considerations/Issues
A number of considerations and issues have been covered in the system design adr already 
[here](doc/adrs/0002-system-design-assumptions-and-decisions.md), below other assumptions and considerations are covered:

- It'd have been best to have the modules of this project in separate repository but for the sake of accessibility, 
a multimodule project structure is used
- H2 (in-memory) is used as the database for both services
- OpenAPI is used for API documentation and testing
- TDD (use-case perspective) was used for implementation of all business logic
- No security mechanism was implemented for this project but options to explore include 
but not limited to JWT Authorization/Authentication with RBAC, API keys or BASIC authentication
- Containerization mechanisms were considered but not implemented. In this case Docker or Kubernetes could be used to 
individually
- The services are loosely coupled. Read about loosely coupled system [here](https://nordicapis.com/how-to-design-loosely-coupled-microservices/)
- Lastly, REST is used for communication between the two services. There are other good solutions to replace 
this "infrastructurally" covered in the system design ADR 