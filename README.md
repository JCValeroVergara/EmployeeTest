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
   Configurar una base de datos en MySQL o MariaDB. Por defecto, se utiliza employees_test_db.

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
## Project Structure

```planintext
EmployeeTest/
├── src/
│   ├── main/
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

- ```Spring Boot: Desarrollo REST.```

- ```JPA: Gestión de datos.```

- ```Lombok: Reducción de boilerplate.```

- ```MySQL/MariaDB: Data Base.```

- ```Java 21: Versión del lenguaje.```

## Author

```plaintext
Juan Carlos Valero Vergara
Developer
```