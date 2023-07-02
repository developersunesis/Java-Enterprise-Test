# Account Opening Enterprise Test

### Overview
This is an API test project providing a set of endpoints and functionality to be used in an enterprise environment. The 
project provisions a list of endpoints and functionalities including but not limited to:
- Endpoint for customer profile creation
- Endpoint for current account creation
- Endpoint to obtain the list of transactions related to an account

### Project Structure
The structure of the project is mainly in three parts:
- `AccountService` module - containing the Accounts service for customer account/profile management
- `TransactionService` module - containing the Transactions service for transaction management

### Install/Usage
#### Installation Requirements
- Java 17
- Maven
- SpringBoot

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

#### Starting and testing the `TransactionService`

Navigate to the service module/folder in a different terminal:
```bash
cd ./TransactionService
mvn clean install
mvn spring-boot:run
```

### Considerations/Issues
- It'd have been best to have the modules of this project in separate repository but for the sake of accessibility, 
a multimodule project structure is used.