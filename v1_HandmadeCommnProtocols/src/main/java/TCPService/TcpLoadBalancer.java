package TCPService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import loadBalance.RoundRobin;

public class TcpLoadBalancer {
	private ServerSocket serverSocket;
    private ExecutorService executorService;
    private final int POOL_SIZE = 20;
    private TcpServer tcpServer;

    public TcpLoadBalancer(String port) throws IOException {
        this.serverSocket = new ServerSocket(Integer.parseInt(port));
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                .availableProcessors() * POOL_SIZE);
        this.tcpServer = new TcpServer();
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
            	
                socket = this.serverSocket.accept();
                
                // Sending data package to Service
    			// Load Balance Algorithm:
    			RoundRobin roundRobin = new RoundRobin();
    			String servicePort = roundRobin.getServer(); // Service: SmartSecurityA or B
    			Socket serviceSocket = this.tcpServer.connect(servicePort);
    			if(serviceSocket == null) {
    				servicePort = roundRobin.getServer();
    			}
    			// To check if it is the last layer to run intelligence
    			Boolean isDatabase = false;
	
                this.executorService.execute(new TcpServer(socket, servicePort, isDatabase));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	public static void main(String[] args) throws IOException {
		System.out.println(">>> Load Balance: TCP Server Started");
        new TcpLoadBalancer(args[0]).service();
        System.out.println("\n>>> Load Balance: TCP Server Terminating...");
    }
}