package Client;

import java.io.*;
import java.net.Socket;

import Server.Client;


public class ClientController {
	private Client client;
	
	public void ClientController(Client client) {
		this.client = client;
		
	}
	
	public void connect() {
		client.started();
	}
	
	public void disconnect() {
		client.stopped();
		
	}
	
}
