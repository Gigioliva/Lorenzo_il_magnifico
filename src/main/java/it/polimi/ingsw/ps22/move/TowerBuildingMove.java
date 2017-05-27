package it.polimi.ingsw.ps22.move;

import it.polimi.ingsw.ps22.message.ErrorMove;
import it.polimi.ingsw.ps22.model.Color;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;

public class TowerBuildingMove extends TowerMove {

	public TowerBuildingMove(String username, Color color, int floor, int numServant) {
		super(username, color, floor, numServant);
	}
	
	@Override
	public void applyMove(Model model) {
		Player player=model.getPlayers().get(username);
		Family family=player.getFamily(color);
		if(model.getBoard().getTower("Building").Control(numServant, space, family)){
			model.getBoard().getTower("Building").applyMove(numServant, space, family);
			model.notifyModel();
		}else{
			ErrorMove error=new ErrorMove();
			model.notifyError(error);
		}
	}

}
