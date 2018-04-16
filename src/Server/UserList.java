package Server;

import java.util.ArrayList;

//Ska hålla en lista av användare, som ska skrivas ner av servern
public class UserList {
	private ArrayList<User> userList = new ArrayList<User>();
	private boolean userNameOk = false;
	
	public synchronized void addUser(User user) {
		userList.add(user);
	}
	
	public void removeUser(User user) {
		userList.remove(user);
	}
	
	
}
