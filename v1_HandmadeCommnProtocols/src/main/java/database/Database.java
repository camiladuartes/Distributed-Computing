package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Database implements IDatabase {
	private String DATASET_FILENAME = "/home/camiladuartes_/Documentos/UFRN-TI/8semestre/Programacao Distribuida/SmartSecurity/src/main/java/database/database.csv";
	
	public int create(String[] dataList) {
		
		// Received message format: "GET/police/policeRegistry/policeStatus/UF/..."
		PoliceLocation policeLocation = new PoliceLocation();
		
		try {
			FileWriter csvWriter = new FileWriter(DATASET_FILENAME, true);
			
			// New ID
			BufferedReader csvReader = new BufferedReader(new FileReader(DATASET_FILENAME));
			String row;
			int newID = 0;
			while ((row = csvReader.readLine()) != null) {
			    String[] data = row.split(",");
			    newID = Integer.parseInt(data[0])+1;
			}
			policeLocation.setPoliceID(newID);

			policeLocation.setPoliceRegistry(Integer.parseInt(dataList[2]));
			policeLocation.setPoliceStatus(Integer.parseInt(dataList[3]));
			policeLocation.setUF(dataList[4]);
			policeLocation.setDate(dataList[5]);
			policeLocation.setLatitude(Double.parseDouble(dataList[6]));
			policeLocation.setLongitude(Double.parseDouble(dataList[7]));
			policeLocation.setPlate(dataList[8]);

			csvWriter.append(String.valueOf(policeLocation.getPoliceID()) + ",");
			csvWriter.append(String.valueOf(policeLocation.getPoliceRegistry()) + ",");
			csvWriter.append(String.valueOf(policeLocation.getPoliceStatus()) + ",");
			csvWriter.append(String.valueOf(policeLocation.getUF()) + ",");
			csvWriter.append(String.valueOf(policeLocation.getDate()) + ",");
			csvWriter.append(String.valueOf(policeLocation.getLatitude()) + ",");
			csvWriter.append(String.valueOf(policeLocation.getLongitude()) + ",");
			csvWriter.append(String.valueOf(policeLocation.getPlate()) + ",\n");
			
			csvWriter.flush();
			csvWriter.close();
			
			System.out.println(">> Sucessfully created data with [policeRegistry = " + policeLocation.getPoliceRegistry()+ "] in database");
			
			return policeLocation.getPoliceID();
			
		} catch (IOException e) {
			System.out.println(">> Couldn't create data with [policeID = " + policeLocation.getPoliceID()+ "] in database");
			e.printStackTrace();
		}
		
		return -1;
	}

	public double[] read(String policeRegistry) {
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(DATASET_FILENAME));
			String row;
			while ((row = csvReader.readLine()) != null) {
			    String[] data = row.split(",");
			    if(data[1].equals(policeRegistry)) {
			    	System.out.println(">> Data [policeRegistry = " + policeRegistry + "] found in database");
			    	
			    	double latLong[] = new double[] {Double.parseDouble(data[5]), Double.parseDouble(data[6])};
			    	return latLong;
			    }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(">> [policeRegistry = " + policeRegistry + "] is not present in database");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Collection<String[]> readAll() {
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(DATASET_FILENAME));
			Collection<String[]> csvList = new ArrayList<String[]>();
			
			String row;
			while ((row = csvReader.readLine()) != null) {
			    String[] data = row.split(",");
				csvList.add(data);
			}
			return csvList;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int update(String policeRegistry, String[] dataList) {
		delete(policeRegistry);
		int policeID = create(dataList);
		
		return policeID;
	}

	public void delete(String policeRegistry) {
		try {			
			BufferedReader csvReader = new BufferedReader(new FileReader(DATASET_FILENAME));
			Collection<String[]> myEntries = new ArrayList<String[]>();
			String row;
			while ((row = csvReader.readLine()) != null) {
			    String[] data = row.split(",");
			    myEntries.add(data);
			}
			
			if(myEntries.size() != 0) {
				List<String[]> filtered = myEntries.stream().filter(entry -> !entry[1].equals(policeRegistry)).collect(Collectors.toList());
				
				FileWriter csvWriter = new FileWriter(DATASET_FILENAME, false);
				for(String[] line : filtered) {
					for(String data : line) {
						csvWriter.append(data + ",");
					}
					csvWriter.append("\n");
				}
				
				csvWriter.flush();
				csvWriter.close();
				
				System.out.println(">> Sucessfully deleted [policeRegistry = " + policeRegistry + "] from database");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(">> [policeRegistry = " + policeRegistry + "] is not present in database");
			e.printStackTrace();
		}
	}

}
