package br.ufrn.smartSecuritySpring.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.smartSecuritySpring.model.PoliceLocation;
import br.ufrn.smartSecuritySpring.service.PoliceLocationService;

@RestController // Make this class to serve rest endpoints
@RequestMapping(path="smart_security/police_location") // Now goes to: localhost:8080/smart_security/police_location
//Will have all of the resources of police location
public class PoliceLocationController {
	private final PoliceLocationService policeLocationService;
	
	// Autowiring: *Dependency Injection* (between two beans, to instanciate one bean in anoter automaticaly without new())
	@Autowired // Magically instanciate policeLocationService, and inject in the constructor. For this, PoliceLocationService has to be a Spring Bean
	public PoliceLocationController(PoliceLocationService policeLocationService) {
		// Use dependency injection, and avoid: this.policeLocationService = new PoliceLocationService();
		this.policeLocationService = policeLocationService;
	}
	
	// Implements an endpoint that will give a json back to stop the 404 Not Found error in localgost:8080/smart_security/police_location
	@GetMapping // Rest endpoint that returns an array of PoliceLocation (Method served as a restfull endpoint)
	public List<PoliceLocation> readAll() {
		return policeLocationService.readAll();
	}
	
	// Implements the API that will take a payload and store that in our system
	// Add new resourses (new police location)
	@PostMapping
	public void create(@RequestBody PoliceLocation policeLocation) { // Takes the request body and maps into a PoliceLocation
		policeLocationService.create(policeLocation);
	}
	
	@DeleteMapping(path = "{policeId}") // id: last element of URI(path)
	public void deleteStudent(@PathVariable("policeId") Long policeId) {
		policeLocationService.delete(policeId);
	}
	
	@PutMapping(path = "{policeId}")
	public void update(@PathVariable("policeId") Long policeId,
								@RequestParam(required = false) Long policeRegistry,
								@RequestParam(required = false) Long policeStatus,
								@RequestParam(required = false) String UF,
								@RequestParam(required = false) LocalDate date,
								@RequestParam(required = false) Double latitude,
								@RequestParam(required = false) Double longitude,
								@RequestParam(required = false) String plate){
		policeLocationService.update(policeId, policeRegistry, policeStatus, UF, date, latitude, longitude, plate);
	}
}
