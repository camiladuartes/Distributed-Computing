package service;

import java.util.Collection;

import database.Database;
import database.IDatabase;
import database.PoliceLocation;

public class SmartSecurity implements ISmartSecurity {
	
	// Sligleton Pattern to Database
	private static ISmartSecurity instance;
	protected IDatabase database;
	
	// Constructors
	public SmartSecurity() {
		this.database = new Database();
	}

	public static ISmartSecurity getInstance() {
		if (instance == null) {
			instance = new SmartSecurity();
		}  
		return instance;
	}
	
	// Check if it is an incident or a police data storage
	public void insidentOrDataManipulation(String data) {
		System.out.println("\n>> Checking if it is an incident or a police data manipulation...");
		String dataList[] = data.split("/");
		
		// Received message format: "POST/incident/latidude/longitude"
		if(dataList[1].equals("incident")) {
			System.out.println(">> It is an Incident...\n");
			closerPolice(dataList);
		}
		// Database Activity
		else if (dataList[1].equals("police")) {
			System.out.println(">> It is a Polide data manipulation...\n");
			policeManipulation(dataList);
		}		
	}	
	
	// Find police team closest to the incident
	public void closerPolice(String incidentData[]) {
		
		Incident incident = new Incident();
		incident.setIncidentID();
		incident.setLatitude(Double.parseDouble(incidentData[2]));
		incident.setLongitude(Double.parseDouble(incidentData[3]));
		
		Collection<String[]> policeData = this.database.readAll();
		
		if(policeData != null) {
			Boolean freePolice = false; // checks if there is a free police team
			double minDistance = Integer.MAX_VALUE;
			PoliceLocation closerPolice = new PoliceLocation();
			
			for(String[] databaseRow : policeData) {
				// Checks if current police team is free
				if(databaseRow[2].equals("1")) {
					freePolice = true;
					// Calculate distance
					double currDistance = latLongDistance(incident.getLatitude(), incident.getLongitude(), Double.parseDouble(databaseRow[5]), Double.parseDouble(databaseRow[6]));
					if(currDistance < minDistance) {
						minDistance = currDistance;
						
						// Database row format: policeID/policeRegistry/policeStatus/UF/...
						closerPolice.setPoliceID(Integer.parseInt(databaseRow[0]));
						closerPolice.setPoliceRegistry(Integer.parseInt(databaseRow[1]));
						closerPolice.setPoliceStatus(Integer.parseInt(databaseRow[2]));
						closerPolice.setUF(databaseRow[3]);
						closerPolice.setDate(databaseRow[4]);
						closerPolice.setLatitude(Double.parseDouble(databaseRow[5]));
						closerPolice.setLongitude(Double.parseDouble(databaseRow[6]));
						closerPolice.setPlate(databaseRow[7]);
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
	
	// Database manipulation of a certain police team data
	public void policeManipulation(String dataList[]) {
		// Message example: "POST/police/policeRegistry/policeStatus/UF/..."
		
		// Read data:
		if(dataList[0].equals("GET")) {
			System.out.println(">> Initializing Database GET...\n");
			// Removing endline
			String policeRegistry = dataList[2];
			policeRegistry = policeRegistry.replace(policeRegistry.substring(policeRegistry.length()-1), "");
			this.database.read(policeRegistry);
		}
		// Create data:
		else if (dataList[0].equals("POST")) {
			System.out.println(">> Initializing Database POST...\n");
			this.database.create(dataList);
		}
		// Update data:
		else if (dataList[0].equals("PUT")) {
			System.out.println(">> Initializing Database PUT...\n");
			this.database.update(dataList[2], dataList);
		}
		// Delete data:
		else {
			System.out.println(">> Initializing Database DELETE...\n");
			// Removing endline
			String policeRegistry = dataList[2];
			policeRegistry = policeRegistry.replace(policeRegistry.substring(policeRegistry.length()-1), "");
			this.database.delete(policeRegistry);
		}
	}
}
