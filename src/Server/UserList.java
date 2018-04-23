package Server;

import java.util.ArrayList;

//Ska hålla en lista av användare, som ska skrivas ner av servern
public class UserList extends ArrayList<User>{
	User user;
	private boolean userNameOk = false;
	
	public synchronized void addUser(User user) {
		add(user);
	}
	
	public synchronized void removeUser(User user) {
		remove(user);
	}
	
	
}
