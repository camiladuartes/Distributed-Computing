package br.ufrn.smartSecurityEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SmartSecurityEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartSecurityEurekaApplication.class, args);
	}

}

/*
 * http://localhost:8761
 */