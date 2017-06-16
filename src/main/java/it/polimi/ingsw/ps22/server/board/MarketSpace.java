package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

public class MarketSpace extends ActionSpace{
	
	private static final long serialVersionUID = 1L;
	
	public MarketSpace(int actionCost, boolean multi){
		super(actionCost,multi);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public MarketSpace clone(ArrayList<Player> player) {
		MarketSpace temp = new MarketSpace(this.getActionCost(),this.getMulti()); 
		if (!this.isPlayable())
			temp.setNotPlayable();
		ArrayList<Family> fam = this.getFamilies();
		for (Player pl : player) {
			for (Family el : fam) {
				if(pl.getUsername().equals(el.getPlayer().getUsername()))
				temp.addFamily(el.clone(pl));
			}
		}
		temp.setBonus(this.bonus.clone());
		return temp;
	}
	
}
