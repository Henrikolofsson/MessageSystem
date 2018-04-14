package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Client.ClientController;

/**
 * Klienten ska skicka en användare till servern vid uppkoppling.
 * Den ska använda sig utav TCP. 
 * @author henke
 *
 */

public class Client extends Thread {
	private ClientController clientController;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Socket socket;
	private String ip;
	private int port;
	private volatile boolean running = false;
	
	
	public Client(ClientController clientController) {
		this.clientController = clientController;
		
		
	}
	
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void start() {
		running = true;
		start();
		
	}
	
	public void stopped() {
		running = false;
		
	}
	
	public void run() {
		
		while(running) {
			try {
				socket = new Socket(ip,port);
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
				
				
				oos.flush();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
