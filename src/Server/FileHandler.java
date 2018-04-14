package Server;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Client.Message;

public class FileHandler {
	private User user;
	private Message message;
	private UserList userList;
	private String fileName;
	
	
	public FileHandler(User user) {
		this.user = user;
	}
	
	
	public void writeFile(String fileName) {
		this.fileName = fileName;
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			
			
			
			oos.flush();
		} catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public void readFile(String fileName) throws ClassNotFoundException {
		this.fileName = fileName;
		Object object;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
			object = ois.readObject();
			
			if(object != null) {
				if(object instanceof UserList) {
					
				}
			}
			
		} catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public void addUserToList(User user) {
		userList.addUser(user);
	}
	
	public void removeUserFromList(User user) {
		userList.removeUser(user);
	}


}
