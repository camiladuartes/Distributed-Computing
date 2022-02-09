package br.ufrn.smartSecurityGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableEurekaClient
public class SmartSecurityGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartSecurityGatewayApplication.class, args);
	}
	
	@Bean
	public Sampler alwaysSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}

/*
 * http://localhost:8080/actuator/gateway/routes
 * 
 * Zipkin:
 * java -jar zipkin.jar
 * http://localhost:9411/zipkin/
 */
