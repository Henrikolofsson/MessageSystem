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
			socket = new Socket(ip, serverPort);
//			System.out.println("Test innan inputström");
//			fromServer = new ObjectInputStream(socket.getInputStream()); // problem här
//			System.out.println("fromServer stream established");
			toServer = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("toServer stream established");
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

	/**
	 * Sends a Message-object to the server.
	 * @param msg the message 
	 */
	public void sendMessage(Message msg) {
		try {
			toServer.writeObject(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class Listener extends Thread {
		public void run() {

			/*
			 * så fort en klient skapas, skickar den sin User-info till servern så att
			 * serven vet vilken user som loggar in. Detta ska bara ske en gång.
			 */
			user = new User("Jessica", null, true);
			try {
				toServer.writeObject(user);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			/**
			 * här bör klienten lyssna efter uppdateringar av listor & inkommande
			 * meddelanden från server
			 */
			while (true) {

				try {
					sendMessage(new Message("my message to server"));
					Thread.sleep(5000);
				} catch (Exception e) {
					System.err.println(e);
					e.printStackTrace();
				}
				disconnectClient();
			}
		}

	}

}
