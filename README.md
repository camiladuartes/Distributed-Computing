# Distributed-Computing

SmartSecurity is a Police team request system upon receipt of incident lat long, that returns the police team closest to the occurrence. 

This project was developed in a university subject called Distributed Computing, and has three levels:

1. Java handmade implementation of UDP and TDP communication protocols using sockets in order to deepen knowledge. Basicaly, the JMeter client communicates with a Load Balancer that balances between two similar services responsible for the business rule which communicate with two local banks stored in a csv file.

2. Development, still in Java, but using Spring framework to take care of the infrastructure and add patternization to the project. Spring Boot, Spring Web MVC and Spring Data JPA for the data manipulation with the PostgreSQL database were the Spring enviromnent frameworks used.

3. Still using Spring frameworks, but splitting the project into two microservices: one for PoliceLocation (Beta1) and another for Incident (Beta2), using Resilience4j. In addition to the use of services: Alpha (working as a Load Balancer), Discovery (to register the services), Gateway (so that the system is not blocked) and Zipkin (for tracing the distributed system).
  
 Here are some specifications and examples for SmartSecurity v3:
 
 <img src="https://user-images.githubusercontent.com/44905597/153313616-1e25ee0e-2d9b-44f1-8a5f-12ec752326e3.jpeg" width="300" height="300" />
 
 URLS:
1. Config: http://localhost:8888/limits-service/dev
2. Eureka: http://localhost:8761/
3. Alpha getData: http://localhost:8080/smart-security-alpha-server/alpha/data
4. Alpha getData2: http://localhost:8080/smart-security-alpha-server/alpha/data2
5. Zipkin: http://localhost:9411/zipkin/ (Antes: ```$java -jar zipkin.jar```)

JMeter Examples:
1. Gateway GET: http://localhost:8080/actuator/gateway/routes
2. PoliceLocation POST: http://localhost:8080/smart-security-beta-server/beta-police
```
{

	"policeRegistry": 201800608,
  
	"policeStatus": 1,
  
	"uf": "RN",
  
	"data": "2021-11-19",
  
	"latitude": -5.8783,
  
	"longitude": -4.3232,
  
	"plate": "OJK-9999"
  
}
```
3. PoliceLocation GET: http://localhost:8080/smart-security-beta-server/beta-police
4. Incident POST: http://localhost:8080/smart-security-beta2-server/beta2-incident
```
{

	"latitude": -5.8783,
  
	"longitude": -4.3232
  
}
```
