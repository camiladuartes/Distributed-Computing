package br.ufrn.smartSecurityAlpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableDiscoveryClient
public class SmartSecurityAlphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartSecurityAlphaApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public Sampler alwaysSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}

/*
 * http://localhost:8080/smart-security-alpha-server/alpha/data
 * http://localhost:8080/smart-security-alpha-server/alpha/data2
 * http://localhost:8081/alpha/data
 * http://localhost:8081/alpha/data2
 * 
 * Zipkin:
 * java -jar zipkin.jar
 * http://localhost:9411/zipkin/
 */