package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

public class HarvestSpace extends ActionSpace{
	
	private static final long serialVersionUID = 1L;
	
	public HarvestSpace(int actionCost, boolean multi){
		super(actionCost,multi);
	}
	
	@Override
	public String toString() {
		
		return super.toString();
	}

	@Override
	public HarvestSpace clone(ArrayList<Player> player) {
		HarvestSpace temp = new HarvestSpace(this.getActionCost(),this.getMulti()); 
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
