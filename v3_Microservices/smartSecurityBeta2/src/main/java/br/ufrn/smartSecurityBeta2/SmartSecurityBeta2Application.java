package br.ufrn.smartSecurityBeta2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableDiscoveryClient
@RestController // Make this class to serve rest endpoints
public class SmartSecurityBeta2Application {

	public static void main(String[] args) {
		SpringApplication.run(SmartSecurityBeta2Application.class, args);
	}

	@Bean
	public Sampler alwaysSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}

/*
 * http://localhost:8091/beta2
 * http://localhost:8080/smart-security-beta2-server/beta2
 * 
 * Zipkin:
 * java -jar zipkin.jar
 * http://localhost:9411/zipkin/
 */