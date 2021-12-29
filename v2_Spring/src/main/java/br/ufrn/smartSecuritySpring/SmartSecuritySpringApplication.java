package br.ufrn.smartSecuritySpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController // Make this class to serve rest endpoints
public class SmartSecuritySpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartSecuritySpringApplication.class, args);
	}

}

/*
 * Packaging and Running the application from a .jar
 * Maven Clean Install
 * java -jar demo-0.0.1-SNAPSHOT.jar --server.port=8080
 */
