package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Servant;

/**
 * 
 * The CouncilPalaceSpace is a space where any number of familiar can be placed. It extends the {@link ActionSpace} class.
 *
 */
public class CouncilPalaceSpace extends ActionSpace {

	private static final long serialVersionUID = 1L;
	private final static int ACTIONCOST = 1;

	/**
	 * It instantiate the CouncilPalaceSpace and sets the bonus of the space to 1 {@link Coin} and 1 {@link CouncilPrivilege}.
	 * The action cost for this space is 1.
	 */
	public CouncilPalaceSpace() {
		super(ACTIONCOST, true, 0);
		HashMap<String, ResourceAbstract> bonus = new HashMap<String, ResourceAbstract>();
		bonus.put("Coin", new Coin(1));
		bonus.put("CouncilPrivilege", new CouncilPrivilege(1));
		super.addBonus(bonus);
	}

	@Override
	public CouncilPalaceSpace clone(ArrayList<Family> family) {
		CouncilPalaceSpace temp = new CouncilPalaceSpace();
		if (!this.isPlayable())
			temp.setNotPlayable();
		ArrayList<Family> fam = this.getFamilies();
		// ora ho fam che sono quelli del palazzo e quelli che mi arrivano dal
		// model
		for (Family palFam : fam) {
			for (Family gameFam : family) {
				if (palFam.getPlayer().getUsername().equals(gameFam.getPlayer().getUsername()) && palFam.getColor()==gameFam.getColor())
					temp.addFamily(gameFam.clone(gameFam.getPlayer()));
			}
		}
		temp.setBonus(this.bonus.clone());
		return temp;
	}

	/**
	 * It checks whether a certain {@link Family} can be placed in the council palace or not
	 * @param numServant number of {@link Servant} to add to the action value
	 * @param family the familiar to be placed
	 * @return true if the family can be place, false otherwise
	 */
	public boolean Control(int numServant, Family family) {
		Player player = family.getPlayer();
		int actionValue = family.getValue();
		if (!(family.isPlaced()) && this.isPlayable()
				&& (this.controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& checkActionValue(numServant, family, actionValue)) {
			return true;
		}
		return false;
	}

	private boolean checkActionValue(int numServant, Family family, int actionValue) {
		Player player = family.getPlayer();
		if (numServant >= 0 && numServant <= player.getSpecificResource("Servant").getQuantity()) {
			if ((player.getSpecBonus().returnBool("DoubleServant")) && ((actionValue + numServant / 2) >= ACTIONCOST)) {
				return true;
			}
			if (!(player.getSpecBonus().returnBool("DoubleServant")) && ((actionValue + numServant) >= ACTIONCOST)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * It applies the effects of the placement to the {@link Player} (adding bonus, sub servants, etc .. )
	 * @param numServant the number of {@link Servant} to increment the action
	 * @param family the {@link Family} to be placed
	 * @param model an instance of the model of the game
	 */
	public void applyMove(int numServant, Family family, Model model) {
		Player player = family.getPlayer();
		applyServant(family, numServant);
		this.addFamily(family);
		this.applyBonus(player, model);
	}

	private void applyServant(Family family, int numServant) {
		Player player = family.getPlayer();
		if (player.getSpecBonus().returnBool("DoubleServant")) {
			player.getResources().get("Servant").subResource(new Servant((numServant / 2) * 2));
			family.incrementValue(numServant / 2);
		} else {
			player.getResources().get("Servant").subResource(new Servant(numServant));
			family.incrementValue(numServant);
		}
	}

	@Override
	public String toString() {

		return super.toString();
	}
}
