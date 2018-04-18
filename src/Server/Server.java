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
		private String userName;

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

		public void run() {
			try {
	
				while (true) {
					try {
						System.out.println("Jag är ful");						
						if(fromClient.readObject() != null)
							message = (Message) fromClient.readObject();
						
						System.out.println("Jag är cool " + message.getMessage());
					} catch (ClassNotFoundException e) {
						//clientSocket.close();
					}
					//System.out.println(message.toString());
					
				}
			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		Server srv = new Server(4447);
	}

}
