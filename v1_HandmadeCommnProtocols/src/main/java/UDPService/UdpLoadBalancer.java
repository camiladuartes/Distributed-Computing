package UDPService;

import java.net.DatagramPacket;
import java.net.SocketException;

import loadBalance.RoundRobin;

public class UdpLoadBalancer extends UdpServer {

	public UdpLoadBalancer(String port) throws NumberFormatException, SocketException {
		super(port);
	}

	public void serve() throws Exception {
		System.out.println(">>> Load Balance: UDP Server Started");
		while (true) {
			// Received message
			DatagramPacket jmeterPacket = readMessage();
			String message = new String(jmeterPacket.getData());
			String jmeterPort = String.valueOf(jmeterPacket.getPort());
			// Confirming message to JMeter
			sendMessageJMeter("CONFIRMATION MESSAGE", String.valueOf(jmeterPacket.getPort()), jmeterPacket.getAddress());
			
			// Sending data package to Service
			// Load Balance Algorithm:
			RoundRobin roundRobin = new RoundRobin();
			String servicePort = roundRobin.getServer(); // Service: SmartSecurityA or B
			
			if(isServerOn(servicePort)) {
				sendMessage(message, servicePort);
			}
			else {
				// ESPERANDO RETORNO DO SERVIÇO
				// CASO NAO RECEBA: MANDAR PRA OUTRO
				servicePort = roundRobin.getServer();
				sendMessage(message, servicePort);
			}
		}
	}


	public static void main(String[] args) throws Exception {
		UdpLoadBalancer loadBalancer = new UdpLoadBalancer(args[0]);
		loadBalancer.serve();
		System.out.println("\n>>> Load Balance: UDP Server Terminating...");
	}
}