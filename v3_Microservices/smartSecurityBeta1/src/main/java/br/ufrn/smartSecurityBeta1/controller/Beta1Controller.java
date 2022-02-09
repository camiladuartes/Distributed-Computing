package br.ufrn.smartSecurityBeta1.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.smartSecurityBeta1.model.PoliceLocation;
import br.ufrn.smartSecurityBeta1.service.Beta1Service;

@RestController
@RequestMapping("/beta")
public class Beta1Controller {
	
	private final Beta1Service beta1Service;
	
	@Autowired
	public Beta1Controller(Beta1Service beta1Service) {
		// Use dependency injection, and avoid: this.beta1Service = new Beta1Service();
		this.beta1Service = beta1Service;
	}

	@Value("${server.port}")
	private int serverPort;
	
	@GetMapping
	public ResponseEntity listServers() {
		return ResponseEntity.ok("OK from " + serverPort + "\n");
	}
}
