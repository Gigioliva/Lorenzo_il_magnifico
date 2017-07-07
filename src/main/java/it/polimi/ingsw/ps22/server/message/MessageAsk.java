package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.server.model.Model;

/**
 * 
 * This method implements a generic message that has to ask something tothe players
 *
 */
public class MessageAsk extends GenericMessage{
	
	private static final long serialVersionUID = 1L;
	private static int id;
	protected int id_ask;
	private String user;

	/**
	 * It instantiates a new message given a user
	 * @param user the receiver of the message
	 */
	public MessageAsk(String user) {
		if (id == 0) {
			id_ask = 1;
		} else {
			id_ask = id + 1;
		}
		id = id_ask;
		this.user=user;
	}
	
	/**
	 * It creates a new messageAsk with specified text message and id
	 * @param ask the text message
	 * @param id the id of the message
	 */
	public MessageAsk(String ask, int id){
		setString(ask);
		id_ask=id;
	}
	
	/**
	 * 
	 * @return the id of the message
	 */
	public int getId(){
		return id_ask;
	}
	
	/**
	 * 
	 * @return the target user
	 */
	public String getUser(){
		return user;
	}
	
	/**
	 * This method will be used to apply default answers to messages which 
	 * dont't have answer by the player in a given time clock
	 * @param model the represents the game
	 */
	public void applyDefault(Model model){
		return;
	}

}
