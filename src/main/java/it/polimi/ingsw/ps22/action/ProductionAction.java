package it.polimi.ingsw.ps22.action;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;

public class ProductionAction extends Action {

	public ProductionAction(int actionValue) {
		super(actionValue);
	}

	@Override
	public void applyAction(Player player, Board board) {
			//ricordarsi che i gain devono essere fatti per ultimi e che gli scambi possono essere più di uno
	}

}
