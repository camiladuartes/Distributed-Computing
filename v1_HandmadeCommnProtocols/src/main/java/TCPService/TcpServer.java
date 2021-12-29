package TCPService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import service.SmartSecurity;

public class TcpServer implements Runnable {
	private Socket fromClient = null;
    private Socket toServer = null;
    private String serverPort;
	private Boolean isDatabase;
	private Boolean haveServer;
	
	public TcpServer() { }

    public TcpServer(Socket fromClient, String serverPort, Boolean isDatabase) throws NumberFormatException, UnknownHostException, IOException {
        this.fromClient = fromClient;
        this.serverPort = serverPort;
        if(serverPort != null) {
        	this.toServer = new Socket(InetAddress.getLocalHost(), Integer.parseInt(serverPort));
        	this.haveServer = true;
        }
        else {
        	this.haveServer = false;
        }
        this.isDatabase = isDatabase;
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream inStream = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(inStream));
    }
    
    protected Socket connect(String port) {
    	Socket socket = null;
    	try {
			socket = new Socket(InetAddress.getLocalHost(), Integer.parseInt(port));
		} catch (IOException e) {
			System.out.println(">> Conexão falhou");
		}
    	return socket;
    }

    public void run() {
    	BufferedReader bfClient = null;
        PrintWriter outClient = null;
        BufferedReader bfServer = null;
        PrintWriter outServer = null;

//        System.out.println("\n>> New connection accepted " + fromClient.getInetAddress()
//                + ":" + fromClient.getPort());

        try {
        	// Receiving message
        	bfClient = getReader(fromClient);
            String receivedMessage = bfClient.readLine();

            System.out.println("Reading message: " + receivedMessage + "\n");
            
            // Sending confirmation message to JMeter
            if(this.serverPort == "8082" || this.serverPort == "8083") {
	            outClient = new PrintWriter(fromClient.getOutputStream());
	            outClient.write("CONFIRMATION MESSAGE");
	            outClient.flush();
            }
            
            // If it got into the last layer, runs intelligence
            if(isDatabase && receivedMessage != null) {
            	SmartSecurity smartSecurity = new SmartSecurity();
    			smartSecurity.insidentOrDataManipulation(receivedMessage);
            }
            
            // Sending message to next layer
            if(haveServer) {
            	bfServer = getReader(toServer);
                outServer = new PrintWriter(toServer.getOutputStream());
                
                outServer.write(receivedMessage);
                outServer.flush();
                
                System.out.println("Sending message: " + receivedMessage + " to " + toServer.getPort() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bfClient != null) {
                	bfClient.close();
                }
                if (outClient != null) {
                	outClient.close();
                }
                if (fromClient != null) {
                	fromClient.close();
                }
                if (bfServer != null) {
                    bfServer.close();
                }
                if (outServer != null) {
                    outServer.close();
                }
                if (toServer != null) {
                    toServer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
