package database;

import java.util.Collection;

public interface IDatabase {
	// CRUD
	public int create(String[] dataList);
	
	public double[] read(String dataList);
	
	public Collection<String[]> readAll();

	public int update(String policeRegistry, String[] dataList);
	
	public void delete(String policeRegistry);
}
