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

import javax.sound.midi.SysexMessage;


public class Client {
	private int serverPort;
//	private User user;
	private ClientController controller;
	private DataInputStream fromServer;
	private DataOutputStream toServer;
	private Socket socket;
	private Message message = new Message("TEST");
	
	public Client(String ip, int serverPort) { // (int serverPort, User user) 
		this.serverPort = serverPort;
//		this.user = user;
		try {
			socket = new Socket(ip, serverPort);
			fromServer = new DataInputStream(socket.getInputStream());
			toServer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		new Listener().start();
		sendMessage();
		
	}
	
	public void setClientController(ClientController controller) {
		this.controller = controller;
	}
	
	public void sendMessage() {
		String str = "Hej";
		
		try {
			toServer.writeUTF(str);;
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
		
	
		  }
		
	}
	
	
}
