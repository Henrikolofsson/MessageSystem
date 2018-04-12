package Client;

import java.io.*;
import java.net.Socket;

//??
public class ClientController extends Thread{
	private Socket socket;
	private String ipAddress;
	private int serverPort;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public ClientController (Socket socket, String ipAddress, int serverPort) {
		this.ipAddress=ipAddress;
		this.serverPort=serverPort;
	}
	public void run() {
		try{
		socket = new Socket(ipAddress, serverPort);
		ois = new ObjectInputStream (socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
		} catch(IOException e) {
			
		}
	}
	
}
