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

public class Client {
	private int serverPort;
	// private User user;
	private ClientController controller;
	private ObjectInputStream fromServer;
	private ObjectOutputStream toServer;
	private Socket socket;
	private Message message = new Message("TEST");

	public Client(String ip, int serverPort) { // (int serverPort, User user)
		this.serverPort = serverPort;
		// this.user = user;
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

	// public void sendMessage() {
	//
	//
	//
	// }

	public void disconnectClient() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class Listener extends Thread {
		public void run() {
			while (true) {
				try {
					//System.out.println("innan skrivning");
					toServer.writeObject(message);
					//System.out.println("Efter skrivning");
				} catch (IOException e) {
					System.out.println("FEL");
					e.printStackTrace();
				}

			}
		}

	}

}
