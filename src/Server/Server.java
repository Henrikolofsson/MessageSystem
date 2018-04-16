package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import Client.ClientController;
import Client.Message;
import Client.UserMessage;

public class Server extends Thread {
	private volatile boolean running = false;
	private ServerSocket serverSocket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private int port;
	private TrafficLogger logger;
	private HashMap<Client, User> hashMap;
	
	
	public Server(int port) {
		this.port = port;
	}
	
	public void started() {
		running = true;
		start();
		
	}
	
	public void stopped() {
		running = false;
		
	}
	
	public void run() {
		
		while(running) {
			try {
				Socket socket = serverSocket.accept();
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
				
				if(ois.readObject() instanceof UserMessage) {
					System.out.println(ois.readObject().toString());
					
				}
				
				if(ois.readObject() instanceof Message) {
					
				}
				
				oos.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		Server server = new Server(6200);
		server.start();
	}

}
