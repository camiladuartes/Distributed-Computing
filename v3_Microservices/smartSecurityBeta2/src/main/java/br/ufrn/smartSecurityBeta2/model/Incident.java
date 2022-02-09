package br.ufrn.smartSecurityBeta2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Incident {
	// Id with a sequence:
	@Id
	@SequenceGenerator(
		name = "incident_sequence",
		sequenceName = "incident_sequence",
		allocationSize = 1 // increments 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "incident_sequence"
	)
	private Long incidentID;
	private double latitude;
	private double longitude;
	
	public Incident() {
		super();
	}
	
	public Incident(Long id, 
					double latitude, 
					double longitude) {
		super();
		this.incidentID = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Incident(double latitude, 
			double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Long getIncidentID() { return incidentID; }
	
	public void setIncidentID(Long incidentID) { this.incidentID = incidentID; }
		
	public double getLatitude() { return latitude; }
	
	public void setLatitude(double latitude) { this.latitude = latitude; }
	
	public double getLongitude() { return longitude; }
	
	public void setLongitude(double longitude) { this.longitude = longitude; }
	
}
