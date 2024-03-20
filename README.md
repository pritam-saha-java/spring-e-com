**Readme.md**

# Spring Boot eCommerce Project

This project is a basic eCommerce web application developed using Spring Boot framework. It provides functionalities such as user authentication, product management, shopping cart, and order history.

## Setup Instructions

### Prerequisites
- Java Development Kit (JDK) version 11 installed on your machine.
- Apache Maven installed for dependency management.
- MySQL relational database management system installed and running.

### Steps
1. Clone the GitHub repository: [repository_link_here]
2. Navigate to the project directory.
3. Configure the database connection in the `application.properties` file.
4. Build the project using Maven: `mvn clean install`
5. Run the application: `mvn spring-boot:run`
6. Access the application through the browser using the provided URL.


## Functionality

### User Authentication and Authorization
- User registration, login, and logout functionality.
- Role-based access control:
  - Regular users can browse products and add them to their cart.
  - Admin users have additional privileges to manage products.

### Product Management
- Admin users can add, update, and delete products.
- Each product has a name and price.

### Shopping Cart
- Users can add products to their cart.
- Users can remove products from their cart.

### Order History
- Users can view their order history.

### Security
- Basic security measures implemented using Spring Security.

### Persistence
- Utilizes Spring Data JPA for interacting with the database.

## API Documentation

The project exposes RESTful APIs for various functionalities. Detailed API documentation including request/response formats, endpoints, and sample usage can be found in the provided Postman collection.

**Postman Collection:** Given within the repository.

## Testing Strategy

The project includes minimal unit tests to ensure critical functionalities are working as expected. Test cases cover key scenarios such as user authentication, product management operations, and cart manipulation. Additional testing can be performed using frameworks like JUnit and Mockito.
