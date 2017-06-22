package it.polimi.ingsw.ps22.client.gui;

import java.util.List;

import it.polimi.ingsw.ps22.server.player.Player;

public class FaithTrackLabel extends PointLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3045902773102095037L;
	
	
	public FaithTrackLabel(double resizeFactor, int slot, List<Player> players){
		super(resizeFactor, slot, players, layout.getFaithSlotSpace(resizeFactor, slot));
	}
}
