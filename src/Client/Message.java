package Client;

import java.util.ArrayList;
import java.util.Calendar;

import Server.User;

public class Message {
	private User sender;
	private ArrayList<User> receivers;
	private String message;
	private Calendar dateSent;
	private Calendar dateReceived;
	
	public Message() {
		
	}
	
	public Message(User sender, ArrayList<User> receivers) {
		this.sender = sender;
		this.receivers = receivers;
	}
	
	public void Message(User sender, ArrayList<User> receivers, String message) {
		this.sender = sender;
		this.receivers = receivers;
		this.message = message;
	}
	
	public void setSender(User user) {
		this.sender = user;
	}
	
	public void setReceiver(ArrayList<User> receivers) {
		this.receivers = receivers;
	}
	
	public User getSender() {
		return sender;
	}
	
	public ArrayList<User> getReceivers() {
		return receivers;
	}
	
	public void setDateSend(Calendar cal) {
		this.dateSent = cal;
	}
	
	public void setDateReceived(Calendar cal) {
		this.dateReceived = cal;
	}
	
	public Calendar getDateSend(Calendar cal) {
		return dateSent;
	}
	
	public Calendar getDateReceived(Calendar cal) {
		return dateReceived;
	}

}
