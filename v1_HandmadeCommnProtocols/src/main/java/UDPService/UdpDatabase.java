package UDPService;

import java.net.SocketException;
import java.util.Arrays;
import java.util.List;

import service.SmartSecurity;

public class UdpDatabase extends UdpServer {
	private List<String> backups;
	
	public UdpDatabase(String port, String... backups) throws NumberFormatException, SocketException {
		super(port);
		this.backups = Arrays.asList(backups);
	}

	public void serve() throws Exception {
		
		System.out.println(">>> Database: UDP Server Service Started");
		while (true) {
			// Received message
			String message = new String(readMessage().getData());
			
			SmartSecurity smartSecurity = new SmartSecurity();
			smartSecurity.insidentOrDataManipulation(message);
			
			// Sending data package to Backup Database
			for(String port : backups) {
				if(isServerOn(port)) {
					sendMessage(message, port);
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
//		UdpDatabase service = new UdpDatabase(args[0]);
		UdpDatabase service = new UdpDatabase(args[0], "8085");
		service.serve();
		System.out.println("\n>>> Database: UDP Server Terminating...");
	}

}

