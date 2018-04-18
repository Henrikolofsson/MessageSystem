package Server;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import Client.Message;
//Skriv gärna vad klassen gör och vem som skrivit den
public class User implements Serializable{
	private String name;
	private ImageIcon picture;
	private boolean online=false;
	private Queue<Message> messages = new LinkedList<Message>();

	public User() {
		
	}

	public User(String name, ImageIcon pic, Boolean online) {
		this.name = name;
		this.picture = pic;
		this.online=online;
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

	public void setPicture(ImageIcon picture) {
		this.picture = picture;
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

	public void setLoggedIn(boolean online) {
		this.online = online;
	}
	
	public Boolean isLoggedIn(){
		return this.online;
	}



}



