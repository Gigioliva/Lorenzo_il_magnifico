package it.polimi.ingsw.ps22.server.action;

import java.io.Serializable;

import it.polimi.ingsw.ps22.server.board.Board;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Servant;

public abstract class Action implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int actionValue;
	protected int servants;

	public Action(int actionValue) {
		this.actionValue = actionValue;
	}

	/**
	 * 
	 * @return an int representing the action value of the action
	 */
	public int getActionValue() {
		return actionValue;
	}

	/**
	 * It applies the action according to the specific action .
	 * @param player the {@link Player} that performs the action
	 * @param servants the number of {@link Servant} to increment the action value
	 * @param model that represent the state of the game.
	 */
	public void applyAction(Player player, int servants, Model model){
		
	}

	/**
	 * It applies the action according to the specific action .
	 * @param player the {@link Player} that performs the action
	 * @param board 
	 * @param servants the number of {@link Servant} to increment the action value
	 * @param model that represent the state of the game.
	 */
	public void applyAction(Player player, Board board, int servants, Model model){
		applyAction(player,servants,model);
	}
	
	public abstract Action clone();

}