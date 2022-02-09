package br.ufrn.smartSecurityBeta2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.smartSecurityBeta2.model.Incident;
import br.ufrn.smartSecurityBeta2.model.PoliceLocation;
import br.ufrn.smartSecurityBeta2.repository.Beta2Repository;

@Service
public class Beta2Service {
	
	private final Beta2Repository beta2Repository;

	@Autowired
	public Beta2Service(Beta2Repository beta2Repository) {
		// Use dependency injection, and avoid: this.beta2Repository = new Beta2Repository();
		this.beta2Repository = beta2Repository;
	}
	
	public double latLongDistance(double incidentLat, double incidentLong, double policeLat, double policeLong) {
		double pi = Math.PI;
		
		double radIncidentLat = incidentLat*pi/180;
		double radIncidentLong = incidentLong*pi/180;
		double radPoliceLat = policeLat*pi/180;
		double radPoliceLong = policeLong*pi/180;
		
		double distance = (Math.acos(Math.cos(radPoliceLat)*Math.cos(radPoliceLong)
							*Math.cos(radIncidentLat)*Math.cos(radIncidentLong)+Math.cos(radPoliceLat)
							*Math.sin(radPoliceLong)*Math.cos(radIncidentLat)*Math.sin(radIncidentLong)
							+Math.sin(radPoliceLat)*Math.sin(radIncidentLat))*6371)*1.15;
		
	    return distance;
	}
	
	// Find police team closest to the incident
	public void closerPolice(Incident incident) {
		
		if(beta2Repository.count() > 0) {
			Boolean freePolice = false; // checks if there is a free police team
			double minDistance = Integer.MAX_VALUE;
			PoliceLocation closerPolice = new PoliceLocation();
			
			for(PoliceLocation policeLocation : beta2Repository.findAll()) {
				// Checks if current police team is free
				if(policeLocation.getPoliceStatus() == 1) {
					freePolice = true;
					// Calculate distance
					double currDistance = latLongDistance(incident.getLatitude(), incident.getLongitude(), policeLocation.getLatitude(), policeLocation.getLongitude());
					if(currDistance < minDistance) {
						minDistance = currDistance;
						
						// Database row format: policeID/policeRegistry/policeStatus/UF/...
						closerPolice.setPoliceID(policeLocation.getPoliceID());
						closerPolice.setPoliceRegistry(policeLocation.getPoliceRegistry());
						closerPolice.setPoliceStatus(policeLocation.getPoliceStatus());
						closerPolice.setUF(policeLocation.getUF());
						closerPolice.setDate(policeLocation.getDate());
						closerPolice.setLatitude(policeLocation.getLatitude());
						closerPolice.setLongitude(policeLocation.getLongitude());
						closerPolice.setPlate(policeLocation.getPlate());
					}
				}
			}
			
			if(freePolice) {
				System.out.println(">> Smaller distance: " + minDistance + "km");
				System.out.println(">> Police Team data with this minimum distance: ");
				System.out.println("\t[Police Team ID: " + closerPolice.getPoliceID() + "]");
				System.out.println("\t[Police Team representative registry: " + closerPolice.getPoliceRegistry() + "]");
				System.out.println("\t[Police Team Status: " + closerPolice.getPoliceStatus() + "]");
				System.out.println("\t[Police Team UF: " + closerPolice.getUF() + "]");
				System.out.println("\t[Date: " + closerPolice.getDate() + "]");
				System.out.println("\t[Police Team Latitude: " + closerPolice.getLatitude() + "]");
				System.out.println("\t[Police Team Longitude: " + closerPolice.getLongitude() + "]");
				System.out.println("\t[Police Team Vehicle Plate: " + closerPolice.getPlate() + "]\n");
			}
			else {
				System.out.println(">> 	There are no police teams registered with free status");
			}
			
		}
		else {
			System.out.println(">> 	There are no police teams registered at the moment");
		}
		
	}
}
