package Client;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Message packet in a Message object with other informations needed like String senders name, String receivers name list,
 * and actual String message  
 * @author lukas
 *
 */
public class Message implements Serializable{
	String sender;		// name of a sender
	List<String> receivers;	// list with receivers names 
	Calendar dateReceived;	// server adds
	Calendar dateSend;	// server adds
	String message; // actual message;
	/**
	 * 
	 */
	public Message() {
	}
	/**
	 * 
	 * @param sender String
	 */
	public Message(String sender) {
		this.sender=sender;
	}
	/**
	 * 
	 * @param sender String
	 * @param receivers List<String>
	 */
	public Message(String sender, List<String> receivers) {
		this(sender);
		this.receivers=receivers;	
	}
	/**
	 * 
	 * @param sender	String
	 * @param receivers	List<String>
	 * @param message	String
	 */
	private Message(String sender, List<String> receivers, String message) {
		this(sender,receivers);
		this.message=message;
	}
	/**
	 * 
	 * @param sender	String
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	/**
	 * 
	 * @param receivers List<String>
	 */
	public void setReceivers(List<String> receivers) {
		this.receivers = receivers;
	}
	/**
	 * Fills by server
	 * @param dateReceived Calendar
	 */
	public void setDateReceived(Calendar dateReceived) {
		this.dateReceived = dateReceived;
	}
	/**
	 * Fills by server
	 * @param dateSend Calendar
	 */
	public void setDateSend(Calendar dateSend) {
		this.dateSend = dateSend;
	}
	/**
	 * 
	 * @param message String
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 
	 * @return String sender
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * 
	 * @return List<String> receivers
	 */
	public List<String> getReceivers() {
		return receivers;
	}
	/**
	 * 
	 * @return 	dateSend Calendar
	 */
	public Calendar getDateSend() {
		return dateSend;
	}
	/**
	 * 
	 * @return dateReceived Calendar
	 */
	public Calendar getDateReceived() {
		return dateReceived;
	}
	/**
	 * 
	 * @return message String
	 */
	public String getMessage() {
		return message;
	}
}
