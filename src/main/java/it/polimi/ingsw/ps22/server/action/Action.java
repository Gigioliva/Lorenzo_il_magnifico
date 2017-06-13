package it.polimi.ingsw.ps22.server.action;

import java.io.Serializable;

import it.polimi.ingsw.ps22.server.board.Board;
import it.polimi.ingsw.ps22.server.player.Player;

public abstract class Action implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int actionValue;
	protected int servants;

	public Action(int actionValue) {
		this.actionValue = actionValue;
	}

	public int getActionValue() {
		return actionValue;
	}

	public void applyAction(Player player, int servants){
		
	}

	public void applyAction(Player player, Board board, int servants){
		applyAction(player,servants);
	}
	
	public abstract Action clone();

}