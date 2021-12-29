package UDPService;

import java.net.SocketException;

public class UdpServiceB extends UdpServer {
	
	public UdpServiceB(String port) throws NumberFormatException, SocketException {
		super(port);
	}

	public void serve() throws Exception {
		System.out.println(">>> ServiceB: UDP Server Service Started");
		while (true) {
			// Received message
			String message = new String(readMessage().getData());
			
			// Sending data package to Database
			String databasePort = "8084"; // Database: PoliceLocation
			
			if(isServerOn(databasePort)) {
				sendMessage(message, databasePort);
			}
			else {
				// ESPERANDO RETORNO DO SERVIÇO
				// CASO NAO RECEBA: MANDAR PRA OUTRO
				databasePort = "8085";
				sendMessage(message, databasePort);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		UdpServiceB service = new UdpServiceB(args[0]);
		service.serve();
		System.out.println("\n>>> ServiceB: UDP Server Terminating...");
	}
	
}
