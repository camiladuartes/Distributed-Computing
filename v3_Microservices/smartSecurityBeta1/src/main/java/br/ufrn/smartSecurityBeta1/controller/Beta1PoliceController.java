package br.ufrn.smartSecurityBeta1.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/beta-police")
public class Beta1PoliceController {
	
	private final Beta1Service beta1Service;
	
	@Autowired
	public Beta1PoliceController(Beta1Service beta1Service) {
		// Use dependency injection, and avoid: this.beta1Service = new Beta1Service();
		this.beta1Service = beta1Service;
	}

	@Value("${server.port}")
	private int serverPort;

	/// Will have the resources of Police Location
	// Implements an endpoint that will give a json back to stop the 404 Not Found error in localgost:8080/smart_security/police_location
	@GetMapping // Rest endpoint that returns an array of PoliceLocation (Method served as a restfull endpoint)
	public List<PoliceLocation> readAll() {
		return beta1Service.readAll();
	}
	
	// Implements the API that will take a payload and store that in our system
	// Add new resourses (new police location)
	@PostMapping
	public void create(@RequestBody PoliceLocation policeLocation) { // Takes the request body and maps into a PoliceLocation
		beta1Service.create(policeLocation);
	}
	
	@DeleteMapping(path = "{policeId}") // id: last element of URI(path)
	public void deleteStudent(@PathVariable("policeId") Long policeId) {
		beta1Service.delete(policeId);
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
		beta1Service.update(policeId, policeRegistry, policeStatus, UF, date, latitude, longitude, plate);
	}
}
