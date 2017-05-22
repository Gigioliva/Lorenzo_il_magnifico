package it.polimi.ingsw.ps22.action;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;

public abstract class Action {
	private int actionValue;

	public Action(int actionValue) {
		this.actionValue = actionValue;
	}

	public int getActionValue(){
		return actionValue;
	}
	
	public void applyAction(Player player, Board board) {

	}

	public void applyAction(Player player) {

	}

}