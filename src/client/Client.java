package client;

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


public class Client {
	private int serverPort;
//	private User user;
	private ClientController controller;
	private DataInputStream ois;
	private DataOutputStream oos;
	private Socket socket;
	
	public Client(String ip, int serverPort) { // (int serverPort, User user) 
		this.serverPort = serverPort;
//		this.user = user;
		try {
			socket = new Socket(ip, serverPort);
			ois = new DataInputStream(socket.getInputStream());
			oos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Listener().start();
		
	}
	
	public void setClientController(ClientController controller) {
		this.controller = controller;
	}
	
	public void sendMessage() {
		String str = "Hej";
		
		try {
			oos.writeUTF(str);;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			sendMessage();
			
			
		  }
		
	}
	
	
}
