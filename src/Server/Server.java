package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	private volatile boolean running = false;
	private ServerSocket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private int port;
	private TrafficLogger logger;
	
	
	public Server(int port) {
		this.port = port;
		try {
			socket = new ServerSocket(port);
//			new ClientHandler();
			started();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void started() {
		running = true;
		start();
		
		
	}
	
	public void stopped() {
		running = false;
		
	}
	
	public void run() {
		
		while(running) {
			try {
				socket.accept();
				
				
				
				oos.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private class ClientHandler extends Thread {
		private Socket socket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		
		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			start();
		}
		
		public void run() {
			
		}
		
	}

}
