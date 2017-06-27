package it.polimi.ingsw.ps22.server.effect;

import java.io.Serializable;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

public interface Effect extends Serializable{
	
	/**
	 * It performs the effect, considering the {@link Player} that is affected by it.
	 * @param player affected by the effect
	 * @param model that represent the state of the game
	 */
	public void performEffect(Player player, Model model);
	
	public Effect clone();
}
