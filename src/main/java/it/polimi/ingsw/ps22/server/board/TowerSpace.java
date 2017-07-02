package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.action.CardAction;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.player.Family;

/**
 * 
 * The TowerSpace is the space where familiars can be placed
 * in order to perform a {@link CardAction}. It extends the {@link ActionSpace} class.
 *
 */
public class TowerSpace extends ActionSpace {

	private static final long serialVersionUID = 1L;

	private DevelopmentCard card;
	private int plan;

	/**
	 * It instantiate the class by setting the following parameters
	 * @param actionCost the action value of the action space
	 * @param multi a boolean that is true if more than one familiar can be placed 
	 * @param plan the position of this space inside the {@link TowerZone}
	 */
	public TowerSpace(int actionCost, boolean multi, int plan) {
		super(actionCost, multi,1);
		this.plan = plan;
	}

	@Override
	public TowerSpace clone(ArrayList<Family> family) {
		TowerSpace temp = new TowerSpace(this.getActionCost(), this.getMulti(), this.plan);
		if(this.card!=null){
			temp.addCard(this.card.clone());
		}
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
		temp.plan = this.plan;
		return temp;
	}

	/**
	 * 
	 * @param card the {@link DevelopmentCard} to be added to this space
	 */
	public void addCard(DevelopmentCard card) {
		this.card = card;
	}

	/**
	 * 
	 * @return The {@link DevelopmentCard} associated to this space
	 */
	public DevelopmentCard getCard() {
		return card;
	}

	/**
	 * It removes the card associated to this space
	 */
	public void removeCard() {
		card = null;
	}

	/**
	 * 
	 * @return the position of this space inside the {@link TowerZone}
	 */
	public int getPlan() {
		return plan;
	}

	@Override
	public String toString() {

		StringBuilder str = new StringBuilder(super.toString());

		str.append("flat: " + plan + "\n");
		str.append(card.toString() + "\n");

		return str.toString();
	}

}
