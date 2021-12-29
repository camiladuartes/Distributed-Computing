package communicationProtocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IpPool {
	public static Map<Integer, String> ipMap = new ConcurrentHashMap<Integer, String>();
    
    static {
    	ipMap.put(8080, "127.0.0.1"); // JMeter
    	ipMap.put(8081, "127.0.0.1"); // Load Balance
        ipMap.put(8082, "127.0.0.1"); // ServerA: SmartSecurityA
        ipMap.put(8083, "127.0.0.1"); // ServerB: SmartSecurityB
        ipMap.put(8084, "127.0.0.1"); // Database: PoliceLocation
        ipMap.put(8085, "127.0.0.1"); // Backup Database: BackupPoliceLocation
    }
}
