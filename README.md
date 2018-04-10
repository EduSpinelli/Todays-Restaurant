## Example of REST API with JWT authentication using Spring Security, Spring Boot 2 and Spring Data Rest.
Java 9
Maven 3+

For Compile mvn clean package 

To run mutant tests mvn org.pitest:pitest-maven:mutationCoverage

## Examples.

POST
http://localhost:8199/sign-up
{
    "username": "maxi",
    "password": "maxi",
    "firstname": "Maximilian",
    "lastname": "Rollins",
    "email": "max@rollins.com"
}

POST
http://localhost:8199/auth
{
    "username": "maxi",
    "password": "maxi"
}

GET
http://localhost:8199/refresh

GET
http://localhost:8199/restaurants
