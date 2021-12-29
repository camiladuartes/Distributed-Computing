package loadBalance;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//public class RoundRobin implements LoadBalance {
public class RoundRobin implements LoadBalancer {
	private static Integer position = 0; // To choose between servers
	
	public String getServer() {
		Set<Integer> servers = ServiceIpPool.ipMap.keySet(); // Server ports
		List<Integer> serverList = new ArrayList<Integer>(); // List of ports
		serverList.addAll(servers);
		Integer target = null; // Choosen Ip server
		
		synchronized (position) {
            if (position > serverList.size() - 1) {
                position = 0; // Round
            }
            target = serverList.get(position);
            position++;
        }
		System.out.println("Round Robin: /" + target.toString());
        return target.toString();
	}
}
