package it.polimi.ingsw.ps22.server.message;

/**
 * 
 * When a game will be closed, a message will be sent to signal it
 *
 */
public class CloseGame extends GenericMessage {

	private static final long serialVersionUID = 1L;
	
	/**
	 * new closed game
	 */
	public CloseGame(){
		setString("Disconnected");
	}

}
