# Distributed-Computing

SmartSecurity is a Police team request system upon receipt of incident lat long, that returns the police team closest to the occurrence. 

This project was developed in a university subject called Distributed Computing, and has two levels:

1. Java handmade implementation of UDP and TDP communication protocols using sockets in order to deepen knowledge. Basicaly, the JMeter client communicates with a Load Balancer that balances between two similar services responsible for the business rule which communicate with two local banks stored in a csv file.

2. Development, still in Java, but using Spring framework to take care of the infrastructure and add patternization to the project. Spring Boot, Spring Web MVC and Spring Data JPA for the data manipulation with the PostgreSQL database were the Spring enviromnent frameworks used.
