package Client;

import Server.User;

public class ClientController {
	private Client client;
	
	public ClientController (Client client) {
		this.client = client;
		client.setClientController(this);
	}

	
	 public static void main(String[] args) {
		 System.out.println("CP controller1");
			Client client = new Client("127.0.0.1",4447);
			System.out.println("CP controller2");
			ClientController controller = new ClientController(client);
			System.out.println("CP controller3");
	  }
}
