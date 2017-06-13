package it.polimi.ingsw.ps22.server.effect;

import it.polimi.ingsw.ps22.server.player.Player;

public interface ActionEffect extends Effect {

	public boolean canAffordCost(Player player);
	
	public ActionEffect clone();
}
