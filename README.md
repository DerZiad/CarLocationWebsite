# Car Location Project

This project represents my work during a coding day at UMI Morocco for my 5th semester of Bachelor's degree. It is a web application developed using the Spring framework, aimed at providing efficient car management for a rental agency.

## Requirements
Make sure you have the following prerequisites installed on your system before running the application:

- **JDK 17:** Java Development Kit version 17 or later.
- **Maven:** Dependency management and build tool for Java projects.
- **MySQL:** Database management system. Ensure you have a MySQL server set up.

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/DerZiad/CarLocationWebsite.git
```

### 2. Navigate to the Project Directory
```bash
cd CarLocation
```
### 3. Configure MySQL Database
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```
### 4. Launch the Application
Use the following Maven command to run the application:
```bash
mvn spring-boot:run
```
The application will be accessible at http://localhost:8080.
### 5. Features
 - **Car Management:** Efficiently manage cars available for rent.
 - **Reservation System:** Reserve cars for specific dates.
 - **Client Interface:** User-friendly interface for clients to rent cars.
