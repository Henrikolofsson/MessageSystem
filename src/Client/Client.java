package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import Server.User;

public class Client {
	private int serverPort;
	 private User user;
	private ClientController controller;
	private ObjectInputStream fromServer;
	private ObjectOutputStream toServer;
	private Socket socket;

	public Client(String ip, int serverPort) { // (int serverPort, User user)
		this.serverPort = serverPort;
		try {
			System.out.println("CP1");
			socket = new Socket(ip, serverPort);
			System.out.println("CP 2");
			// fromServer = new ObjectInputStream(socket.getInputStream());
			System.out.println("CP 3");
			toServer = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {

			System.out.println("FETT FEL");
			e.printStackTrace();
		}
		new Listener().start();

	}

	public void setClientController(ClientController controller) {
		this.controller = controller;
	}

	public void disconnectClient() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(Message msg) {
		try {
			toServer.writeObject(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private class Listener extends Thread {
		public void run() {
							
				user = new User ("Jessica", null, true);
				try {
					toServer.writeObject(user);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
//				sendMessage(new Message("bajstolle"));
			
			while (true) {									
			
					
				try {	
					toServer.writeObject(new Message("hej"));
					
					Thread.sleep(5000);
				} catch (Exception e) {
					System.out.println("FEL");
					e.printStackTrace();
				}

			}
		}

	}

}
