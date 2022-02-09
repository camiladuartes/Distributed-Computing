package br.ufrn.smartSecurityAlpha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class AlphaService {
	
	private final RestTemplate restTemplate;

	@Autowired
	public AlphaService(RestTemplate restTemplate) {
		// Use dependency injection, and avoid: this.restTemplate = new RestTemplate();
		this.restTemplate = restTemplate;
	}
	
	/// Communication with Beta Server
	@CircuitBreaker(name = "smart-security-beta-server",
					fallbackMethod = "buildFallbackGetData")
	@Bulkhead(name = "bulkhead-smart-security-beta-server",
				fallbackMethod = "buildFallbackGetData")
	@Retry(name = "retry-smart-security-beta-server",
			fallbackMethod = "retryFallbackGetData")
	public ResponseEntity getData() {
		return restTemplate.getForEntity("http://smart-security-beta-server/beta", String.class);
	}

	/// Communication with Beta2 Server
	@CircuitBreaker(name = "smart-security-beta2-server",
					fallbackMethod = "buildFallbackGetData")
	@Bulkhead(name = "bulkhead-smart-security-beta2-server",
				fallbackMethod = "buildFallbackGetData")
	@Retry(name = "retry-smart-security-beta2-server",
			fallbackMethod = "retryFallbackGetData")
	public ResponseEntity getData2() {
		return restTemplate.getForEntity("http://smart-security-beta2-server/beta2", String.class);
	}
	
	public ResponseEntity buildFallbackGetData(Throwable t) {
		return ResponseEntity.ok("Fallback in action: " + t.getMessage() + "\n");
	}
	
	public ResponseEntity retryFallbackGetData(Throwable t) {
		return ResponseEntity.ok("Fallback in action for retry\n");
	}
}
