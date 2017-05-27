package it.polimi.ingsw.ps22.move;

import it.polimi.ingsw.ps22.message.ErrorMove;
import it.polimi.ingsw.ps22.model.Color;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;

public class MarketMove extends FamilyMove {
	
	public MarketMove(String username,Color color,int space, int numServant){
		super(username, color, space, numServant);
	}

	@Override
	public void applyMove(Model model) {
		Player player=model.getPlayers().get(username);
		Family family=player.getFamily(color);
		if(model.getBoard().getMarket().Control(numServant, space, family)){
			model.getBoard().getMarket().applyMove(numServant, space, family);
			model.notifyModel();
		} else{
			ErrorMove error=new ErrorMove();
			model.notifyError(error);
		}
	}
	
	
}
