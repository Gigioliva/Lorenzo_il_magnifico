package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Servant;

public class CouncilPalaceSpace extends ActionSpace {

	private static final long serialVersionUID = 1L;
	private transient final static int ACTIONCOST = 1;

	public CouncilPalaceSpace() {
		super(ACTIONCOST, true);
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

	public boolean cantPlaceCouncilPalace(Player player) {
		HashMap<Color, Family> family = player.getAllFamily();
		if (Control(player.getSpecificResource("Servant").getQuantity(), family.get(Color.NEUTRAL)))
			return false;
		for (Color color : family.keySet()) {
			if (!family.get(color).isPlaced() && Color.NEUTRAL != color)
				return false;
		}
		return true;
	}

	@Override
	public String toString() {

		return super.toString();
	}
}
