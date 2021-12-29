package UDPService;

import java.net.SocketException;

import service.SmartSecurity;

public class UdpDatabaseBackup extends UdpServer {
	
	public UdpDatabaseBackup(String port) throws NumberFormatException, SocketException {
		super(port);
	}

	public void serve() throws Exception {
		
		System.out.println(">>> Backup Database: UDP Server Service Started");
		while (true) {
			// Received message
			String message = new String(readMessage().getData());
			
			// Checking if main Database is on
			if(!isServerOn("8084")) {
				SmartSecurity smartSecurity = new SmartSecurity();
				smartSecurity.insidentOrDataManipulation(message);
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		UdpDatabaseBackup service = new UdpDatabaseBackup(args[0]);
		service.serve();
		System.out.println("\n>>> Backup Database: UDP Server Terminating...");
	}
}
