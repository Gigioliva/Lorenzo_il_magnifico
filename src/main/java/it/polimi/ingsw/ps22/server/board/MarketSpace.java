package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.resource.Resource;

/**
 * 
 * This class implements the MarketSpace, where familiars can be placed to gain some bonus in {@link Resource}
 *
 */
public class MarketSpace extends ActionSpace {

	private static final long serialVersionUID = 1L;

	/**
	 * It instantiate the class by setting the following parameters
	 * @param actionCost the action value of the action space
	 * @param multi a boolean that is true if more than one familiar can be placed 
	 * @param maxSize the maximum number of familiars that can be placed here
	 */
	public MarketSpace(int actionCost, boolean multi, int maxSize) {
		super(actionCost, multi, maxSize);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public MarketSpace clone(ArrayList<Family> family) {
		MarketSpace temp = new MarketSpace(this.getActionCost(), this.getMulti(), maxSize);
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
