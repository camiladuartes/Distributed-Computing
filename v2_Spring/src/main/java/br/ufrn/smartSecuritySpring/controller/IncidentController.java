package br.ufrn.smartSecuritySpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.smartSecuritySpring.model.Incident;
import br.ufrn.smartSecuritySpring.service.IncidentService;

@RestController // Make this class to serve rest endpoints
@RequestMapping(path="smart_security/incident") // Now goes to: localhost:8080/smart_security/incident
//Will have all of the resources of incident
public class IncidentController {
	private final IncidentService incidentService;
	
	// Autowiring: *Dependency Injection* (between two beans, to instanciate one bean in anoter automaticaly without new())
	@Autowired // Magically instanciate incidentService, and inject in the constructor. For this, IncidentService has to be a Spring Bean
	public IncidentController(IncidentService incidentService) {
		// Use dependency injection, and avoid: this.incidentService = new IncidentService();
		this.incidentService = incidentService;
	}

	// Receives an incident data, and prints the closest police team
	@PostMapping
	public void closerPolice(@RequestBody Incident incident) { // Takes the request body and maps into an Incident
		incidentService.closerPolice(incident);
	}
	
}
