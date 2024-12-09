# Technical Test - Developer - Parameta S.A.S

This project is a REST service developed in Java using Spring Boot, JPA, and Lombok, designed to manage employee information. It includes validations, age and tenure calculations, and integration with a SOAP service to store data in a MySQL database.

## Features

- Receives employee data through a REST service.
- Validates input data:
    - Date formats.
    - Non-empty attributes.
    - Ensure that the employee is of legal age.
- Integrates with a SOAP web service to store data in MySQL.
- Calculates:
    - Employee's current age (years, months, and days).
    - Time of employment with the company (years, months, and days).
- Responds in JSON format with the calculated data.

---

## Requirements

- **Java**: Versión 21.
- **Database**: MySQL o MariaDB.
- **Additional tools**:
    - Maven.
    - Postman or Insomnia.

---

## Project Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/JCValeroVergara/EmployeeTest
   cd EmployeeTest

2. **Create the database**:
    ```sql
   
   Set up a database in MySQL or MariaDB. By default, employees_test_db is used.

3. **Edit database configuration**:

    Update the File ```src/main/resources/application.properties``` with your database configuration.
    ```properties
   spring.datasource.url=jdbc:mariadb://localhost:3306/employees_test_db
    spring.datasource.username=root
    spring.datasource.password=
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

4. **Project dependencies**:

    Make sure the ```pom.xml``` file includes the necessary dependencies, such as ```mariadb-java-client``` or the driver corresponding to your database.
  
---
## Using the project
### Running the project

1. **Compile the project**:
   ```bash
   mvn clean install
   
2. **Start the server**:
   ```bash
    mvn spring-boot:run
   
3. **Send a request**:
    
    The server will be available at http://localhost:8080.

### Endpoints

**Send a request testing the endpoints**:
- The server will be available at http://localhost:8080.

Processing an employee:
1. **GET method**: http://localhost:8080/employees/process

2. **Getting all employees**:
    - **GET method**: http://localhost:8080/employees/all


---
## External API Configuration

This project uses random data generated using ```RandomAPI.com```. If you need to modify the data, edit the ```ExternalApiResponse.java``` class to adjust it to the schema of the new API.

Example of data generated in ```RandomAPI.com```:
```javaScript
api.firstName = faker.name.firstName();
api.lastName = faker.name.lastName();
api.documentType = list(['CC', 'TI', 'Pasaporte', 'NIT']);
api.documentNumber = random.numeric(10000000, 999999999); // Entre 8 y 12 dígitos.
api.dateBirth = list(['1967-01-15', '1978-09-17', '1981-06-23']);
api.dateJoining = list(['2000-01-15', '2007-08-17']);
api.position = list(['Analista', 'Desarrollador', 'Gerente']);
api.salary = random.numeric(1000000, 10000000);
```
---
## Testing the project
It is recommended to use Postman or Insomnia to test the endpoints. For example:

### Processing an employee:

    URL: http://localhost:8080/employees/process
    Method: GET.

### Getting all employees:

    URL: http://localhost:8080/employees/all
    Method: GET


---
## SOAP Service Configuration

This project integrates with a SOAP web service to store employee data. The SOAP service is set up to handle requests for employee information and persist it into the database.

### SOAP Configuration

1. **Web Service Configuration**: The SOAP web service is configured in `com.soap.config.WebServiceConfig`. This configuration exposes the SOAP endpoint at `/ws/employee`.

2. **WSDL (Web Service Definition Language)**: The WSDL file is located at `/ws/employee.wsdl`, and it defines the structure and operations for the SOAP service. You can access it by visiting:
    ```
    http://localhost:8080/ws/employee.wsdl
    ```

3. **SOAP Service**: The service that processes the SOAP requests is implemented in the class `com.soap.service.EmployeeServiceSoap`. It provides operations such as `addEmployee` to persist employee data.


### Sending SOAP Requests

        To interact with the SOAP service, you can use SOAP clients like SoapUI or Postman:

#### **SoapUI**:

        1. **Open SoapUI** and import the WSDL file:
        - You can import the WSDL file by providing the URL `http://localhost:8080/ws/employee.wsdl` or by downloading the file and opening it locally in SoapUI.

        2. **Send a `addEmployee` request** to add an employee:
        - Once the WSDL is imported, you can create a new request to the `addEmployee` operation.
        - Fill in the necessary employee data like `employeeId`, `employeeName`, `dateBirth`, `dateJoining`, `position`, and `salary`.

#### **Postman**:

        1. **Set the request type to POST**.
        2. **Set the URL to `http://localhost:8080/ws/employee`** (or wherever your SOAP service is running).
        3. **In the body of the request, add the SOAP XML** as shown below:



---
## Project Structure

```planintext
EmployeeTest/
├── src/
│   ├── main/
│   │   ├── java/com/sopa/
│   │   │   ├── client/         # Cliente SOAP.
│   │   │   ├── config/         # Configurations.
│   │   │   ├── service/        # Services SOAP.
│   │   │   ├── wsdl/           # WSDL SOAP.
│   │   ├── java/jcv/employee_test/
│   │   │   ├── controllers/    # Controllers REST.
│   │   │   ├── dto/            # Data Transfer Objects.
│   │   │   ├── models/         # Models Data Base.
│   │   │   ├── repositories/   # Repositorios JPA.
│   │   │   ├── service/        # Services.
│   │   │   ├── validations/    # Validations.
│   │   ├── resources/
│   │   │   ├── application.properties  # Properties Project.
├── pom.xml                     # Maven dependencies.

```

## Technologies used

- ```Spring Boot: REST development.```

- ```JPA: Data management.```

- ```Lombok:Boilerplate reductione.```

- ```MySQL/MariaDB: Database.```

- ```Java 21:Language version.```

## Author

```plaintext
Juan Carlos Valero Vergara
Developer
```