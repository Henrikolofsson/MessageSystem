package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	
	public Server(int serverPort) {
		
		try {
			ServerSocket serverSocket = new ServerSocket(serverPort);
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			while(true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Just connected to " + clientSocket.getRemoteSocketAddress()); 
				new ClientHandler(clientSocket).start();
			}
		}
		catch(UnknownHostException ex) {
			ex.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("Server stopped");
	}
	 
	 private class ClientHandler extends Thread{
		 private Socket clientSocket;
		 public ClientHandler(Socket socket) {
			 this.clientSocket = socket;
		 }
		 
		 public void run() {
			
			 
			 
		 }
	 }
			
		  public static void main(String[] args) {
			  Server srv = new Server(4445);
		  }
}
