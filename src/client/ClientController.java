package client;

public class ClientController {
	private Client client;
	
	public ClientController (Client client) {
		this.client = client;
		client.setClientController(this);
	}

	
	 public static void main(String[] args) {
			Client client = new Client(4445);
			ClientController controller = new ClientController(client);
	  }
}
