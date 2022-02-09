package br.ufrn.smartSecurityBeta2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.smartSecurityBeta2.model.Incident;
import br.ufrn.smartSecurityBeta2.service.Beta2Service;

@RestController
@RequestMapping("/beta2-incident")
public class Beta2IncidentController {
	
	private final Beta2Service beta2Service;
	
	@Autowired
	public Beta2IncidentController(Beta2Service beta2Service) {
		// Use dependency injection, and avoid: this.beta2Service = new Beta2Service();
		this.beta2Service = beta2Service;
	}
	
	// Receives an incident data, and prints the closest police team
	@PostMapping
	public void closerPolice(@RequestBody Incident incident) { // Takes the request body and maps into an Incident
		beta2Service.closerPolice(incident);
	}
	
}
