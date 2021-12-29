package loadBalance;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Pool of Service Ips
public class ServiceIpPool {
    public static Map<Integer, String> ipMap = new ConcurrentHashMap<Integer, String>();
    
    static {
        ipMap.put(8082, "127.0.0.1"); // Server: SmartSecurityA
        ipMap.put(8083, "127.0.0.1"); // Server: SmartSecurityB
    }
}
