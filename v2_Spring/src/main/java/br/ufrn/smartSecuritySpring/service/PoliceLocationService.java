package br.ufrn.smartSecuritySpring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.smartSecuritySpring.model.PoliceLocation;
import br.ufrn.smartSecuritySpring.repository.SmartSecurityRepository;

@Service // For this class to be a Spring Bean, and the PoliceLocationController have the knowledge of where policeLocationService is
public class PoliceLocationService {
	private final SmartSecurityRepository smartSecurityRepository;
	
	@Autowired
	public PoliceLocationService(SmartSecurityRepository smartSecurityRepository) {
		this.smartSecurityRepository = smartSecurityRepository;
	}
	
	public List<PoliceLocation> readAll() {
		// With Spring Data JPA we hadn't to implement the database methods
		return smartSecurityRepository.findAll();
	}
	
	/*
	public Optional<PoliceLocation> read(int policeRegistry) {
		return smartSecurityRepository.findTeamByPoliceRegistry(policeRegistry);
	}
	*/

	public void create(PoliceLocation policeLocation) {
		// Business Logic
		Optional<PoliceLocation> policeLocationOptional = smartSecurityRepository.findTeamByPoliceRegistry(policeLocation.getPoliceRegistry());
		if(policeLocationOptional.isPresent()) {
			// To get this message error in http response, go to application.properties and add:
			// "server.error.include-message=always"
			throw new IllegalStateException("Police registry taken");
		}
		// If the police team is not present, we want to save it
		smartSecurityRepository.save(policeLocation);
	}
	
	public void delete(Long policeID) {
		boolean exists = smartSecurityRepository.existsById(policeID);
		if(!exists) {
			throw new IllegalStateException("Police Team with id " + policeID + " does not exists");
		}
		smartSecurityRepository.deleteById(policeID);
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
		PoliceLocation policeLocation = smartSecurityRepository.findById(policeID).orElseThrow(() -> new IllegalStateException("Police Team with id " + policeID + " does not exists"));
		
		if(!Objects.equals(policeLocation.getPoliceRegistry(), policeRegistry)) {
			Optional<PoliceLocation> policeLocationOptional = smartSecurityRepository.findTeamByPoliceRegistry(policeRegistry);
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
