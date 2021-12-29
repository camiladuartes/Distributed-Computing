package database;

public class PoliceLocation {
	// Data of the police officer representative of the team
	private int policeID;
	private int policeRegistry;
	private int policeStatus; // 1: Free or 2:busy
	
	// UF of the vehicle team
	private String UF;
	
	// Date of the latlong post
	private String date;
	
	// Locatization data
	private double latitude;
	private double longitude;
	private String plate;
	
	// Getters & Setters
	public int getPoliceID() { return policeID; }
	
	public void setPoliceID(int policeID) { this.policeID = policeID; }
	
	public int getPoliceRegistry() { return policeRegistry; }
	
	public void setPoliceRegistry(int policeCPF) { this.policeRegistry = policeCPF; }
	
	public String getUF() { return UF; }
	
	public void setUF(String uF) { UF = uF; }
	
	public String getDate() { return date; }
	
	public void setDate(String date) { this.date = date; }
	
	public double getLatitude() { return latitude; }

	public void setLatitude(double latitude) { this.latitude = latitude; }

	public double getLongitude() { return longitude; }

	public void setLongitude(double longitude) { this.longitude = longitude; }

	public String getPlate() { return plate; }
	
	public void setPlate(String plate) { this.plate = plate; }

	public int getPoliceStatus() { return policeStatus; }

	public void setPoliceStatus(int policeStatus) { this.policeStatus = policeStatus; }
	
}