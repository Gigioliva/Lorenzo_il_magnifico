package it.polimi.ingsw.ps22.client.gui;

import java.util.List;

import it.polimi.ingsw.ps22.server.player.Player;

public class VictoryPointLabel extends PointLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 818894545761040781L;
	
	public VictoryPointLabel(double resizeFactor, int slot, List<Player> players){
		super(resizeFactor, slot, players, layout.getVictorySlotSpace(resizeFactor, slot));
	}

}
