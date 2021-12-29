package service;

public class Incident {
	private int incidentID = 0;
	private double latitude;
	private double longitude;
	
	public int getIncidentID() { return incidentID; }
	
	public void setIncidentID() { this.incidentID++; }
	
	public double getLatitude() { return latitude; }
	
	public void setLatitude(double latitude) { this.latitude = latitude; }
	
	public double getLongitude() { return longitude; }
	
	public void setLongitude(double longitude) { this.longitude = longitude; }
	
}
