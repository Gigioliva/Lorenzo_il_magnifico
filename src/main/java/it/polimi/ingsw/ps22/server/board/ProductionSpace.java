package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;

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
