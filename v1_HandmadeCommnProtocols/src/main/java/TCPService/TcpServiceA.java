package TCPService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServiceA {
	private ServerSocket serverSocket;
    private ExecutorService executorService;
    private final int POOL_SIZE = 20;
    private TcpServer tcpServer;

    public TcpServiceA(String port) throws IOException {
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
                
                // Sending data package to Database
    			String databasePort = "8084"; // Database: PoliceLocation
    			Socket databaseSocket = this.tcpServer.connect(databasePort);
    			if(databaseSocket == null) {
    				databasePort = "8085";
    			}
    			
    			// To check if it is the last layer to run intelligence
    			Boolean isDatabase = false;
    			
                this.executorService.execute(new TcpServer(socket, databasePort, isDatabase));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
	public static void main(String[] args) throws IOException {
		System.out.println(">>> ServiceA: TDP Server Service Started");
        new TcpServiceA(args[0]).service();
        System.out.println("\n>>> ServiceA: TDP Server Terminating...");
    }
}