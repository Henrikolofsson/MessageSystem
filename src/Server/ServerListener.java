package Server;


import javax.sql.ConnectionEventListener;

import Server.Client.Connectionhandler;
import Client.Message;

public interface ServerListener {

	void MessageReciver(Message m);

	void userConnected(User user,ConnectionEventListener connectionhandler);

	void userDisconnected(User user);

	
	

	
	

}
