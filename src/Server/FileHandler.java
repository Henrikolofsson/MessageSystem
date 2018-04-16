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
	
	
	public FileHandler() {}
	
	public FileHandler(User user) {
		this.user = user;
	}
	
	public void writeUserList(User user) {
		this.fileName = "files/ListOfUsers";
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(user);
			
			oos.flush();
			oos.close();
		} catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public UserList getUserList() {
		this.fileName = "files/ListOfUsers";
		Object object;
		int counter = 0;
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
			object = ois.readObject();
			counter++;
			
			while(object != null) {
				object = ois.readObject();
				counter++;
			}
			
			ois.close();
		} catch(IOException e) {
			e.getStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addUserToList(User user) {
		userList.addUser(user);
//		writeUserList(user);
	}
	
	public void removeUserFromList(User user) {
		userList.removeUser(user);
		
	}


}
