package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Server.User;

public class Client {
	private int serverPort;
	private User user;
	private ClientController controller;
	private ObjectInputStream fromServer;
	private ObjectOutputStream toServer;
	private Socket socket;

	public Client(String ip, int serverPort, User user) { // (int serverPort, User user)
		this.serverPort = serverPort;
		 this.user = user;
		try {
			socket = new Socket(ip, serverPort);
			toServer = new ObjectOutputStream(socket.getOutputStream());
			fromServer = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {

			System.out.println("FETT FEL");
			e.printStackTrace();
		}
		controller = new ClientController(this);
		new Listener().start();

	}


	public void disconnect() {
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
	
	public void readMessage() {
		try {
			Message msg = (Message) fromServer.readObject();
			System.out.println(msg.getMessage() + " client received msg");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class Listener extends Thread {
		public void run() {
			ArrayList<String> messageReceivers = new ArrayList<>();
			messageReceivers.add("Kalle");
			messageReceivers.add("Balle");
			messageReceivers.add("Nalle");
			Message msg1 = new Message("Sender", messageReceivers, "meddelandet");

			
			/*
			 * s� fort en klient skapas, skickar den sin User-info till servern s� att
			 * serven vet vilken user som loggar in. Detta ska bara ske en g�ng.
			 */
			try {
				toServer.writeObject(user);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			


			/**
			 * h�r b�r klienten lyssna efter uppdateringar av listor & inkommande
			 * meddelanden fr�n server
			 */
			while (true) {
				sendMessage(msg1);
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					System.err.println(e);
					e.printStackTrace();
				}
//				disconnectClient();
			}
		}

	}

}
