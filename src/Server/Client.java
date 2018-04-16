package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Client.ClientController;
import Client.UserMessage;

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
	private ClientListener clientListener;
	private UserMessage userMessage = new UserMessage();
	
	
	public Client(ClientController clientController) {
		this.clientController = clientController;
	}
	
	public Client(String ip, int port) {		//Detta ska finnas i kontrollern
		this.ip = ip;
		this.port = port;
	}
	
	public void setController(ClientController clientController) {
		this.clientController = clientController;
	}
	
	public void setClientListener(ClientListener clientListener) {
		this.clientListener = clientListener;
	}
	
	public void started() {
		running = true;
		start();
	}
	
	public void stopped() {
		running = false;
		
	}
	
	public void send(Object obj) {
		
	}
	
	public void run() {
		
		while(running) {
			try {
				socket = new Socket(ip,port);
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
				System.out.println(socket.getInputStream().toString());
				
				
				
				oos.flush();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", 5000);
		client.started();
		
	}

}
