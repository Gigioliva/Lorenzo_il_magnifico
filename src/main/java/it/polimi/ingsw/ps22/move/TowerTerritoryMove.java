package it.polimi.ingsw.ps22.move;

import it.polimi.ingsw.ps22.model.Color;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;

public class TowerTerritoryMove extends TowerMove {

	public TowerTerritoryMove(String username, Color color, int floor, int numServant) {
		super(username, color, floor, numServant);
	}
	
	@Override
	public void applyMove(Model model) {
		Player player=model.getPlayers().get(username);
		Family family=player.getFamily(color);
		if(model.getBoard().getTower("Territory").Control(numServant, space, family)){
			model.getBoard().getTower("Territory").applyMove(numServant, space, family);
			model.notifyModel();
		}else{
			//genera messaggio di errore e notifica il model per mandarlo
		}
	}

}
