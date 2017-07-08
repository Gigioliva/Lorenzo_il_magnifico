package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * This message is used to ask to the player
 * which of his familiars he wants to assign an action value of 6
 *
 */
public class AskFamily extends MessageAsk {

	private static final long serialVersionUID = 1L;
	private Player player;
	
	/**
	 * It creates a new {@link AskFamily} message
	 * @param player the target player
	 */
	public AskFamily(Player player){
		super(player.getUsername());
		this.player=player;
		String ask="A quale famigliare assegni valore 6?";
		setString(ask);
	}
	
	/**
	 * 
	 * It creates a new {@link AskFamily} message
	 * @param str the text of the message
	 * @param id the id of the message
	 */
	public AskFamily(String str, int id){
		super(str,id);
	}
	
	/**
	 * 
	 * @return the target player
	 */
	public Player getPlayer() {
		return player;
	}
	
	public AskFamily accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
