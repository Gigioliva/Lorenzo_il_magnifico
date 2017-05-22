package it.polimi.ingsw.ps22.action;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class CardAction extends Action {
	private ArrayList<String> types; // inventato da noi per possibilità di
										// prendere più carte
	private HashMap<String, ResourceAbstract> sconto; // da sottrarre al costo
														// della carta e
														// riaggiungerlo dopo
														// averla presa o se non
														// può prenderla

	public CardAction(int actionValue) {
		super(actionValue);
		types = new ArrayList<String>();
	}

	public void addType(String type) {
		types.add(type);
	}

	@Override
	public void applyAction(Player player, Board board) {
	}

}
