package br.ufrn.smartSecurityBeta1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.smartSecurityBeta1.model.PoliceLocation;
import br.ufrn.smartSecurityBeta1.repository.Beta1Repository;

@Service
public class Beta1Service {
	
	private final Beta1Repository beta1Repository;

	@Autowired
	public Beta1Service(Beta1Repository beta1Repository) {
		// Use dependency injection, and avoid: this.beta1Repository = new Beta1Repository();
		this.beta1Repository = beta1Repository;
	}
	
	public List<PoliceLocation> readAll() {
		// With Spring Data JPA we hadn't to implement the database methods
		return beta1Repository.findAll();
	}

	public void create(PoliceLocation policeLocation) {
		// Business Logic
		Optional<PoliceLocation> policeLocationOptional = beta1Repository.findTeamByPoliceRegistry(policeLocation.getPoliceRegistry());
		if(policeLocationOptional.isPresent()) {
			// To get this message error in http response, go to application.properties and add:
			// "server.error.include-message=always"
			throw new IllegalStateException("Police registry taken");
		}
		// If the police team is not present, we want to save it
		beta1Repository.save(policeLocation);
	}
	
	public void delete(Long policeID) {
		boolean exists = beta1Repository.existsById(policeID);
		if(!exists) {
			throw new IllegalStateException("Police Team with id " + policeID + " does not exists");
		}
		beta1Repository.deleteById(policeID);
	}
	
	// Transactional: Entity goes to a manage state
	@Transactional // says that I don't have to implement any jpql query, so I can use the setters from my entity to update the entitys in my database when possible
	// Example: "PUT http://localhost:8080/api/v1/student/1?name=Maria&email=maria@gmail.com"
	public void update(Long policeID, 
					Long policeRegistry, 
					Long policeStatus,
					String UF,
					LocalDate date,
					Double latitude,
					Double longitude,
					String plate) {
		PoliceLocation policeLocation = beta1Repository.findById(policeID).orElseThrow(() -> new IllegalStateException("Police Team with id " + policeID + " does not exists"));
		
		if(!Objects.equals(policeLocation.getPoliceRegistry(), policeRegistry)) {
			Optional<PoliceLocation> policeLocationOptional = beta1Repository.findTeamByPoliceRegistry(policeRegistry);
			if(policeLocationOptional.isPresent()) {
				throw new IllegalStateException("Police registry taken");
			}
			policeLocation.setPoliceRegistry(policeRegistry);
		}
		
		if(!Objects.equals(policeLocation.getPoliceStatus(), policeStatus)) {
			policeLocation.setPoliceStatus(policeStatus);
		}
		
		if(UF != null && 
				UF.length() > 0 &&
			!Objects.equals(policeLocation.getUF(), UF)) {
			policeLocation.setUF(UF);
		}
		
		if(date != null &&
			!Objects.equals(policeLocation.getDate(), date)) {
			policeLocation.setDate(date);
		}
		
		if(!Objects.equals(policeLocation.getLatitude(), latitude)) {
			policeLocation.setLatitude(latitude);
		}
		
		if(!Objects.equals(policeLocation.getLongitude(), longitude)) {
			policeLocation.setLongitude(longitude);
		}
		
		if(plate != null && 
			plate.length() > 0 &&
			!Objects.equals(policeLocation.getPlate(), plate)) {
			policeLocation.setPlate(plate);
		}
	}
}
