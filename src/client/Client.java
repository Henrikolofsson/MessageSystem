package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	private int serverPort;
//	private User user;
	private ClientController controller;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Socket socket;
	
	public Client(int serverPort) { // (int serverPort, User user) 
		this.serverPort = serverPort;
//		this.user = user; 
		new Listener().start();
		
	}
	
	public void setClientController(ClientController controller) {
		this.controller = controller;
	}
	
	public void sendMessage() {
		
	}
	
	public void disconnectClient() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class Listener extends Thread{
		public void run() {
			try {
				
				InetAddress host = InetAddress.getByName("localhost"); 
				System.out.println("Connecting to server on port " + serverPort); 

				socket = new Socket(host,serverPort); 
				System.out.println("Just connected to " + socket.getRemoteSocketAddress()); 
				
				
				
			}
			catch(UnknownHostException ex) {
				ex.printStackTrace();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		  }
		
	}
	
	
}
