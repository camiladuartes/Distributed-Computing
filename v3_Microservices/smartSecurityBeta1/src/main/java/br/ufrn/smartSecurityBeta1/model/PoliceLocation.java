package br.ufrn.smartSecurityBeta1.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity // For the Hibernate
@Table // To map this class for our table in database
// Model
public class PoliceLocation {
	// Data of the police officer representative of the team
	
	// Id with a sequence:
	@Id
	@SequenceGenerator(
		name = "police_location_sequence",
		sequenceName = "police_location_sequence",
		allocationSize = 1 // increments 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "police_location_sequence"
	)
		
	private Long policeID;
	private Long policeRegistry;
	private Long policeStatus; // 1: Free or 2:busy
	
	// UF of the vehicle team
	private String UF;
	
	// Date of the latlong post
	private LocalDate date;
	
	// Locatization data
	private Double latitude;
	private Double longitude;
	private String plate;
	
	public PoliceLocation() {
		super();
	}
	
	public PoliceLocation(Long policeID, 
					Long policeRegistry, 
					Long policeStatus,
					String UF,
					LocalDate date,
					Double latitude,
					Double longitude,
					String plate) {
		super();
		this.policeID = policeID;
		this.policeRegistry = policeRegistry;
		this.policeStatus = policeStatus;
		this.UF = UF;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
		this.plate = plate;
	}

	public PoliceLocation(Long policeRegistry, 
					Long policeStatus,
					String UF,
					LocalDate date,
					Double latitude,
					Double longitude,
					String plate) {
		super();
		this.policeRegistry = policeRegistry;
		this.policeStatus = policeStatus;
		this.UF = UF;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
		this.plate = plate;
	}
	
	// Getters & Setters
	public Long getPoliceID() { return policeID; }
	
	public void setPoliceID(Long policeID) { this.policeID = policeID; }
	
	public Long getPoliceRegistry() { return policeRegistry; }
	
	public void setPoliceRegistry(Long policeCPF) { this.policeRegistry = policeCPF; }
	
	public String getUF() { return UF; }
	
	public void setUF(String uF) { UF = uF; }
	
	public LocalDate getDate() { return date; }
	
	public void setDate(LocalDate date) { this.date = date; }
	
	public Double getLatitude() { return latitude; }

	public void setLatitude(Double latitude) { this.latitude = latitude; }

	public Double getLongitude() { return longitude; }

	public void setLongitude(Double longitude) { this.longitude = longitude; }

	public String getPlate() { return plate; }
	
	public void setPlate(String plate) { this.plate = plate; }

	public Long getPoliceStatus() { return policeStatus; }

	public void setPoliceStatus(Long policeStatus) { this.policeStatus = policeStatus; }
	
}