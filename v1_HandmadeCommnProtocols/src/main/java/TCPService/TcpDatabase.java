package TCPService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpDatabase {
	private ServerSocket serverSocket;
    private ExecutorService executorService;
    private final int POOL_SIZE = 20;
    private TcpServer tcpServer;

    public TcpDatabase(String port) throws IOException {
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
                
                // Sending data package to Backup Database
    			String bckpDatabasePort = "8085"; // Database: PoliceLocation
    			Socket databaseSocket = this.tcpServer.connect(bckpDatabasePort);
    			if(databaseSocket == null) {
    				bckpDatabasePort = null;
    			}
    			
    			// To check if it is the last layer to run intelligence
    			Boolean isDatabase = true;
    			
                this.executorService.execute(new TcpServer(socket, bckpDatabasePort, isDatabase));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
	public static void main(String[] args) throws IOException {
		System.out.println(">>> Database: TDP Server Service Started");
        new TcpDatabase(args[0]).service();
        System.out.println("\n>>> Database: TDP Server Terminating...");
    }
}