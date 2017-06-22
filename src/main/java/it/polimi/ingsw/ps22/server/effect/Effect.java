package it.polimi.ingsw.ps22.server.effect;

import java.io.Serializable;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

public interface Effect extends Serializable{
	
	public void performEffect(Player player, Model model);
	
	public Effect clone();
}
