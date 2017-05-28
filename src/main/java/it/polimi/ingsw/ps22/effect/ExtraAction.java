package it.polimi.ingsw.ps22.effect;

import it.polimi.ingsw.ps22.action.Action;
import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;

public class ExtraAction implements ImmediateEffect {
	private Action action;

	public ExtraAction(Action action) {
		this.action = action;
	}

	@Override
	public void performEffect(Player player, Board board) {
		action.applyAction(player, board);
	}
	
	@Override
	public String toString() {
		return action.toString();
	}

}
