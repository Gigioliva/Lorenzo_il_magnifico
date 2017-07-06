package it.polimi.ingsw.ps22.server.move;

import it.polimi.ingsw.ps22.server.card.CardLeader;

/**
 * Abstraction of the class which represent the moves that concern {@link LeaderCard}
 */
public class LeaderMove extends Move {
	
	private static final long serialVersionUID = 1L;
	protected String nameCard;
	
	/**
	 * @param username
	 *            is the username of the player which is creating the move
	 * @param nameCard
	 *            is the name of the {@link CardLeader} the player want to move (generic move)
	 */
	public LeaderMove(String username,String nameCard){
		super(username);
		this.nameCard=nameCard;
	}

}
