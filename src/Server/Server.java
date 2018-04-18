package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import com.sun.xml.internal.ws.api.config.management.policy.ManagedServiceAssertion.ImplementationRecord;

import Client.Message;

public class Server {

	Clients cl = new Clients();
	public Server(int serverPort) {
		
		try {
			ServerSocket serverSocket = new ServerSocket(serverPort);
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Just connected to " + clientSocket.getRemoteSocketAddress());
				new ClientHandler(clientSocket, cl).start();
				System.out.println("Done awaiting new connections");
			}
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server stopped");
	}
	
	private class Clients {
		
		private HashMap<User,ClientHandler> onlineUsers = new HashMap<User,ClientHandler>();
		
		public synchronized void put(User user, ClientHandler clientHandler) {
			System.out.println("put");
			onlineUsers.put(user, clientHandler);
			System.out.println("put2");
		}
		
		public synchronized ClientHandler get(User user) {
			return get(user);
		}
		public synchronized void remove(User user) {
			onlineUsers.remove(user);
		}
		
		public synchronized ArrayList<String> getAllOnlineUsers()
		{
			ArrayList<String> usersOnline = new ArrayList<>();			
			
			Iterator it = onlineUsers.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		        User tempUser = (User) pair.getKey();
		        usersOnline.add(tempUser.getName());
		        it.remove(); // avoids a ConcurrentModificationException
		        
		    }
			
			return usersOnline;
		}
		
		
	}

	private class ClientHandler extends Thread {
		private Socket clientSocket;
		private Clients cl;
		private ObjectInputStream fromClient;
		private ObjectOutputStream toClient;
		private User user;

		public ClientHandler(Socket socket, Clients cl) {
			this.clientSocket = socket;
			this.cl = cl;
			
			try {
				fromClient = new ObjectInputStream(clientSocket.getInputStream());
				System.out.println("fromClient stream established");
				toClient = new ObjectOutputStream(clientSocket.getOutputStream());
				System.out.println("toClient stream established");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public void readMessage(Message msg) {
			System.out.println(msg.getMessage());
		}



		public void run() {

			try {
	
					user =(User) fromClient.readObject();
					System.out.println(user.getName());
					cl.put(user, this);
					System.out.println(cl.getAllOnlineUsers().toString());
					
					//String mishmashed = cl.toString();

				while (true) {
					try {
						if (fromClient.readObject() instanceof Message) {
							Message msg = (Message) fromClient.readObject();
//							System.out.println(msg.getMessage());
						}
					
						
						
					} catch (Exception e) {
						System.err.println(e);
					}
					// System.out.println(message.toString());

				}
			} catch (Exception e1) {
				System.err.println(e1);

			}

		}
	}

	public static void main(String[] args) {
		Server srv = new Server(4447);
	}

}
