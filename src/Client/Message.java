package Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import Server.User;

public class Message implements Serializable {
	private User sender;
	private ArrayList<String> receivers;
	private String message;
	private Calendar dateSent;
	private Calendar dateReceived;
	
	// remove after test
	public Message(String str,  ArrayList<String> receivers, String message) {
		this.message = message;
		this.receivers = receivers;
	}
	
	public Message(String str) {
		this.message = str;
	}
	
	public Message(User sender, ArrayList<String> receivers) {
		this.sender = sender;
		this.receivers = receivers;
	}
	
	public Message(User sender, ArrayList<String> receivers, String message) {
		this.sender = sender;
		this.receivers = receivers;
		this.message = message;
	}
	
	public void setSender(User user) {
		this.sender = user;
	}
	
	public void setReceiver(ArrayList<String> receivers) {
		this.receivers = receivers;
	}
	
	public User getSender() {
		return sender;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public ArrayList<String> getReceivers() {
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
	
	public void remove(String name) {
		receivers.remove(name);
	}

}
