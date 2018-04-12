package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Klienten ska skicka en användare till servern vid uppkoppling.
 * Den ska använda sig utav TCP. 
 * @author henke
 *
 */

public class Client extends Thread {
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	
	public Client() {
		
		
	}

}
