package Server;

import java.io.*;
import java.net.*;
import java.util.*;
import Client.Message;

public class Server {
	private Clients cl = new Clients();
	private ArrayList<Message> unsentMessages = new ArrayList<>();
	private ArrayList<String> messageReceivers = new ArrayList<>();

	/**
	 * Creates the server in the requested port and instantiates a ServerSocket.
	 * Then it listens for a client to connect to this socket and starts a
	 * ClientHandler-thread.
	 * 
	 * @param serverPort
	 *            the port to connect to
	 */
	public Server(int serverPort) {
		try {
			ServerSocket serverSocket = new ServerSocket(serverPort);
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Just connected to " + clientSocket.getRemoteSocketAddress());
				new ClientHandler(clientSocket, cl).start();
				System.out.println("Done awaiting new connections");
				
				messageReceivers.add("Kalle");
				messageReceivers.add("Balle");
				messageReceivers.add("Nalle");
			Message msg1 = new Message("Jessica", messageReceivers , "meddelandet");
				addMessageToUnsentList(msg1);
//				addMessageToList(new Message("Judy", messageReceivers , "bla"));
				checkReceiversAndOnliners();
				
			}
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
			System.out.println("Server stopped");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// testing purpose, remove after
	public Server() {
		
		messageReceivers.add("Kalle");
		messageReceivers.add("Balle");
		messageReceivers.add("Nalle");
	Message msg1 = new Message("Jessica", messageReceivers , "meddelandet");
		addMessageToUnsentList(msg1);
		cl.put(new User("Pelle"), new ClientHandler());
//		addMessageToList(new Message("Judy", messageReceivers , "bla"));
		checkReceiversAndOnliners();
	}
	
	
	
	public synchronized void addMessageToUnsentList (Message message) {
		unsentMessages.add(message);
		System.out.println(unsentMessages.size());
		System.out.println(unsentMessages.get(0).getMessage());
	}
	
	public synchronized void findReceiverInOnlineList(String onlineUser, String receiverName) {
		if(onlineUser.matches(receiverName)) {
			System.out.println(receiverName + " found in the onlineList");
			// anropa metod som skickar meddelandet till receiver
			// sendMessageToOnlineUser(receiverName);
		} else {
			System.out.println(receiverName + " not found in the onlineList");
			// gör ingenting?
		}
	}
	
	public synchronized void checkReceiversAndOnliners() {
		// läsa vilka users som är online
		ArrayList<String> onlineUsers = cl.getAllOnlineUsers();
		ArrayList<String> listOfReceivers = new ArrayList<String>();
		int index=0;
		
		for (Message message : unsentMessages) {
			listOfReceivers = message.getReceivers();	
			for(String receiverOnList : listOfReceivers) {
				String onlineUser = onlineUsers.get(index);
				findReceiverInOnlineList(onlineUser, receiverOnList);
				index++;
				if(index==listOfReceivers.size() || index==onlineUsers.size()) {
					break;
				}
			}
		}
	}
	
	/* loop? */
	public synchronized void sendMessageToOnlineUser() {
		// skicka till HashMap key?
	}

	

	/**
	 * Inner-class which handles the list of online users.
	 * 
	 * @author Jessica
	 *
	 */
	private class Clients {

		/* HashMap för att en användare ska associeras till en klient */
		private HashMap<User, ClientHandler> onlineUsers = new HashMap<User, ClientHandler>();

		/**
		 * Associates the specified user with the specified clientHandler-thread in this
		 * map.
		 * 
		 * @param user
		 *            key with which the specified value is to be associated
		 * @param clientHandler
		 *            value to be associated with the specified key
		 */
		public synchronized void put(User user, ClientHandler clientHandler) {
			onlineUsers.put(user, clientHandler);
		}

		/**
		 * behövs ej??
		 * 
		 * @param user
		 * @return
		 */
		public synchronized ClientHandler get(User user) {
			return get(user);
		}

		/**
		 * Removes the user from the list of online users.
		 * 
		 * @param user
		 *            whose mapping is to be removed from the map
		 */
		public synchronized void remove(User user) {
			onlineUsers.remove(user);
		}

		/**
		 * Returns a String-arraylist of all online users.
		 * 
		 * @return an ArrayList<String> usersOnline
		 */
		public synchronized ArrayList<String> getAllOnlineUsers() {
			ArrayList<String> usersOnline = new ArrayList<>();

			Iterator it = onlineUsers.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				// System.out.println(pair.getKey() + " = " + pair.getValue()); // testing
				// purpose
				User tempUser = (User) pair.getKey();
				usersOnline.add(tempUser.getName());
				it.remove(); // avoids a ConcurrentModificationException

			}

			return usersOnline;
		}

	}

	
	/**
	 * Thread which listens for and responds to a client's requests. This
	 * inner-class updates the connected clients...
	 * 
	 * @author Jessica
	 *
	 */
	private class ClientHandler extends Thread {
		private Socket clientSocket;
		private Clients cl;
		private ObjectInputStream fromClient;
		private ObjectOutputStream toClient;
		private User user;

		/**
		 * Creates the client's streams which are used to connect and communicate with
		 * the server.
		 * 
		 * @param socket
		 *            the new socket provided by the sever
		 * @param cl
		 */
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

		public ClientHandler() {
			// TODO Auto-generated constructor stub
		}

		/**
		 * Reads a message from a client and adds all receivers in a stored list.
		 * 
		 * @param obj
		 *            the message-object
		 * @throws ClassNotFoundException
		 * @throws IOException
		 */
		public void readMessage(Object obj) throws ClassNotFoundException, IOException {
			Message msg = (Message) fromClient.readObject();
			/*
			 * anropa metod/lägg till receivers i en lista. Borde dela på de som är online
			 * och de som är offline?
			 */
			System.out.println(msg.getMessage());

		}
		
		public void sendToClients() {
			
		}

		public void run() {

			try {

				/*
				 * det första som görs är att användaren läggs till i hashmap för att indikera
				 * att den användare är online
				 */
				user = (User) fromClient.readObject();
				System.out.println(user.getName());
				cl.put(user, this);
				System.out.println(cl.getAllOnlineUsers().toString());

				// String mishmashed = cl.toString();
				while (true) {
					Object obj = fromClient.readObject();
					try {
						if (obj instanceof Message) {
							readMessage(obj);
							// anropa metod som läser av & skickar till online receivers
							// anropa metod som loggar?
							// anropa metod som sparar meddelande till offline receivers

						}

					} catch (Exception e) {
						System.err.println(e);
					}
					// System.out.println(message.toString());

				}

			} catch (Exception e1) {
				if (e1 instanceof SocketException) {
					disconnectClient();
					System.out.println("controllern stoppad");
				} else {
					System.err.println(e1);
				}

			}

		}

		private void disconnectClient() {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
//		Server srv = new Server(4447);
		Server srv = new Server();
	}

}
