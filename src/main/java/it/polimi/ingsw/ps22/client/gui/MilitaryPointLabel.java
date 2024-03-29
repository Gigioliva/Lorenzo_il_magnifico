package it.polimi.ingsw.ps22.client.gui;

import java.util.List;

import it.polimi.ingsw.ps22.server.player.Player;

public class MilitaryPointLabel extends PointLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4779104419050097282L;

	public MilitaryPointLabel(double resizeFactor, int slot, List<Player> players){
		super(resizeFactor, slot, players, layout.getMilitarySlotSpace(resizeFactor, slot));
	}
}
