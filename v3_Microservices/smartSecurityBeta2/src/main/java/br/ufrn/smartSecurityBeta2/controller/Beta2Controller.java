package br.ufrn.smartSecurityBeta2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.smartSecurityBeta2.service.Beta2Service;

@RestController
@RequestMapping("/beta2")
public class Beta2Controller {
	
	private final Beta2Service beta2Service;
	
	@Autowired
	public Beta2Controller(Beta2Service beta2Service) {
		// Use dependency injection, and avoid: this.beta2Service = new Beta2Service();
		this.beta2Service = beta2Service;
	}

	@Value("${server.port}")
	private int serverPort;
	
	@GetMapping
	public ResponseEntity listServers() {
		return ResponseEntity.ok("OK from " + serverPort + "\n");
	}
}
