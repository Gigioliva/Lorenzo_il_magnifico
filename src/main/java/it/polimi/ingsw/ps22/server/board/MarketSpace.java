package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.player.Family;

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
	public MarketSpace clone(ArrayList<Family> family) {
		MarketSpace temp = new MarketSpace(this.getActionCost(),this.getMulti()); 
		if (!this.isPlayable())
			temp.setNotPlayable();
		ArrayList<Family> fam = this.getFamilies();
		// ora ho fam che sono quelli del palazzo e quelli che mi arrivano dal model
		for (Family palFam : fam) {
			for (Family gameFam : family) {
				if(palFam.getPlayer().getUsername().equals(gameFam.getPlayer().getUsername()))
				temp.addFamily(gameFam.clone(gameFam.getPlayer()));
			}
		}
		temp.setBonus(this.bonus.clone());
		return temp;
	}
	
}
