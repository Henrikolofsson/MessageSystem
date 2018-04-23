package Server;

import java.io.BufferedInputStream;
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
	private UserList userList = new UserList();
	private String fileName;
	
	
	public FileHandler() {}
	
	public FileHandler(Object object) {
		
	}
	
	public void writeUserToUserList(User user) {
		this.fileName = "files/ListOfUsers";
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			userList.add(user);
			oos.writeObject(userList);
			
			oos.flush();
			oos.close();
		} catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public UserList getUsersFromUserList() {
		this.fileName = "files/ListOfUsers";
		
		try {
			ObjectInputStream ois = new ObjectInputStream(
									new BufferedInputStream(
									new FileInputStream(fileName)));
			Object object = ois.readObject();
			
			
			while(object != null) {	// && object instanceof User
				User user = (User)object;
				userList.addUser(user);
				
				object = ois.readObject();
				userList.addUser(user);
				
				if(object == null) break;
//				System.out.println(list.get(0).getName());
			} 
			
			ois.close();
		} catch(IOException e) {
			e.getStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public void addUserToList(User user) {
		userList.addUser(user);
//		writeUserList(user);
	}
	
	public void removeUserFromList(User user) {
		userList.removeUser(user);
		
	}
	
	public static void main(String[] args) {
		FileHandler fh = new FileHandler();
		User user = new User("Henrik", null, false);
		User user2 = new User("Jessica", null, false);
		User user3 = new User("Pellemannen", null, false);
		User user4 = new User("Henkomannen", null, false);
		fh.writeUserToUserList(user2);
		fh.writeUserToUserList(user4);
		fh.writeUserToUserList(user3);
		
//		UserList ul = fh.getUsersFromUserList();
//		for(int i = 0; i < ul.size(); i++) {
//			System.out.println(ul.get(i).getName());
//		}
		
//		System.out.println(ul.size());
		
		
		
	}


}
