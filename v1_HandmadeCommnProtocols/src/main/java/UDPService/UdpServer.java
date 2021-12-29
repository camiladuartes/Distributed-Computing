package UDPService;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import communicationProtocol.IpPool;

public abstract class UdpServer {
	protected String port;
	protected DatagramSocket serverSocket;
	protected List<Integer> portsList;
	protected String lastPort;
	
	public UdpServer(String port) throws NumberFormatException, SocketException {
		this.port = port;
		this.serverSocket = new DatagramSocket(Integer.parseInt(this.port)); // Environment in which we will send the packages
		
		Set<Integer> ports = IpPool.ipMap.keySet(); // Ports
		this.portsList = new ArrayList<Integer>(); // List of ports
		this.portsList.addAll(ports);
	}
	
	public boolean isServerOn(String port) {
		 DatagramSocket sock = null;
	     try {
	         sock = new DatagramSocket(Integer.parseInt(port));
	         sock.close();
	         return false;
	     } catch (BindException ignored) {
//	    	 System.out.println(">> Ocupado\n");
	         return true;
	     } catch (SocketException ex) {
	         System.out.println(ex);
	         return true;
	     }
	 }
	
	public DatagramPacket readMessage() throws IOException {
		byte[] receivedMessage = new byte[1024];
		DatagramPacket receivedPacket = new DatagramPacket(receivedMessage, receivedMessage.length); // Received package
		this.serverSocket.receive(receivedPacket);

		System.out.println("Reading message: " + new String(receivedPacket.getData()) + "\n");
		
		return receivedPacket;
	}
	
	public void sendMessage(String message, String port) throws Exception {
		InetAddress address = InetAddress.getLocalHost();
		byte[] messageBytes = message.getBytes();
		
		DatagramPacket sendServicePacket = new DatagramPacket(messageBytes, messageBytes.length, address, Integer.parseInt(port));
		this.serverSocket.send(sendServicePacket); // Sending data package
		
		System.out.println("Sending message: " + new String(sendServicePacket.getData()) + " to " + sendServicePacket.getPort() + "\n");
	}
	
	public void sendMessageJMeter(String message, String port, InetAddress address) throws Exception {
		byte[] messageBytes = message.getBytes();
		
		DatagramPacket sendServicePacket = new DatagramPacket(messageBytes, messageBytes.length, address, Integer.parseInt(port));
		this.serverSocket.send(sendServicePacket); // Sending data package
				
		System.out.println("Sending message: " + new String(sendServicePacket.getData()) + " to " + sendServicePacket.getPort() + "\n");
	}
}
