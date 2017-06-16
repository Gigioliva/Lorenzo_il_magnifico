package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.effect.GainResource;
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
		for(Family el: fam)
			temp.addFamily(el.clone(el.getPlayer()));
			GainResource bonus = this.bonus.clone();
			temp.setBonus(bonus);
		return temp;
	}
	
}
