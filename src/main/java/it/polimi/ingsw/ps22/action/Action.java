package it.polimi.ingsw.ps22.action;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;

public abstract class Action {
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

}