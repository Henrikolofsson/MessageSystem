package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import Client.Message;

public class Server {
	private Message message;

	public Server(int serverPort) {

		try {
			ServerSocket serverSocket = new ServerSocket(serverPort);
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Just connected to " + clientSocket.getRemoteSocketAddress());
				new ClientHandler(clientSocket).start();
				System.out.println("Done awaiting new connections");
			}
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server stopped");
	}

	private class ClientHandler extends Thread {
		private Socket clientSocket;
		private ObjectInputStream fromClient;
		private ObjectOutputStream toClient;
		private User user;

		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
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

			

				while (true) {
					try {
						if (fromClient.readObject() instanceof Message) {
							Message msg = (Message) fromClient.readObject();
							System.out.println(msg.getMessage());
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
