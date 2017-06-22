package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.player.Family;

public class ProductionSpace extends ActionSpace {

	private static final long serialVersionUID = 1L;

	public ProductionSpace(int actionCost, boolean multi, int maxSize) {
		super(actionCost, multi, maxSize);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public ProductionSpace clone(ArrayList<Family> family) {
		ProductionSpace temp = new ProductionSpace(this.getActionCost(), this.getMulti(), maxSize);
		if (!this.isPlayable())
			temp.setNotPlayable();
		ArrayList<Family> fam = this.getFamilies();
		// ora ho fam che sono quelli del palazzo e quelli che mi arrivano dal
		// model
		for (Family palFam : fam) {
			for (Family gameFam : family) {
				if (palFam.getPlayer().getUsername().equals(gameFam.getPlayer().getUsername())
						&& palFam.getColor() == gameFam.getColor())
					temp.addFamily(gameFam.clone(gameFam.getPlayer()));
			}
		}
		temp.setBonus(this.bonus.clone());
		return temp;
	}

}
