package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.player.Family;

public class TowerSpace extends ActionSpace {

	private static final long serialVersionUID = 1L;

	private DevelopmentCard card;
	private int plan;

	public TowerSpace(int actionCost, boolean multi, int plan) {
		super(actionCost, multi);
		this.plan = plan;
	}

	@Override
	public TowerSpace clone(ArrayList<Family> family) {
		TowerSpace temp = new TowerSpace(this.getActionCost(), this.getMulti(), this.plan);
		temp.addCard(this.card.clone());
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

	public void addCard(DevelopmentCard card) {
		this.card = card;
	}

	public DevelopmentCard getCard() {
		return card;
	}

	public void removeCard() {
		card = null;
	}

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
