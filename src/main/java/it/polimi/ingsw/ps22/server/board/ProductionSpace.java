package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

public class ProductionSpace extends ActionSpace{
	
	private static final long serialVersionUID = 1L;
	
	public ProductionSpace(int actionCost, boolean multi){
		super(actionCost,multi);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public ProductionSpace clone(ArrayList<Player> player) {
		ProductionSpace temp = new ProductionSpace(this.getActionCost(),this.getMulti()); 
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
