package it.polimi.ingsw.ps22.effect;

import it.polimi.ingsw.ps22.player.Player;

public interface ActionEffect extends Effect {

	public boolean canAffordCost(Player player);
}
