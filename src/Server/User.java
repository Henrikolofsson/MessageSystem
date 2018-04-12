package Server;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import Client.Message;
//Skriv gärna vad klassen gör och vem som skrivit den
public class User {
	private String name;
	private ImageIcon picture;
	private String password;
	private boolean isLoggedIn=false;
	private Queue<Message> messages = new LinkedList<Message>();


	public User(String name,String pass, ImageIcon pic, Boolean isLoggedIn) {
	this.isLoggedIn=isLoggedIn;
	}

	public void addMessage(Message message) {
		messages.add(message);
	}
	
	public Message getMessage() {
		return messages.peek();
	}
	public void destroyNext() {
		if(messages !=null) {
		messages.remove();
		}
	}

	public void setPic(ImageIcon pic) {
		this.picture = pic;
	}
	public ImageIcon getPicture() {
		return picture;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public String getPass() {
		return password;
	}
	public void setPass(String pass) {
		this.password = pass;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public Boolean isLoggedIn(){
		return this.isLoggedIn;
	}



}



