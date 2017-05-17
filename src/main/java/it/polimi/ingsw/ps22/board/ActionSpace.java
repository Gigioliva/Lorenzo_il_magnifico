package it.polimi.ingsw.ps22.board;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public abstract class ActionSpace {
	private final int actionCost;
	private boolean multi;
	private boolean playable;
	private ArrayList<Family> family;
	private HashMap<String, ResourceAbstract> bonus;

	public ActionSpace(int actionCost, boolean multi) {
		this.actionCost = actionCost;
		this.multi = multi;
		family = new ArrayList<Family>();
		bonus = new HashMap<String, ResourceAbstract>();
	}

	public void addFamily(Family family) {
		if (controlPlacement()) {
			this.family.add(family);
		}
	}
	
	public void resetFamily(){
		this.family=new ArrayList<Family>();
	}
	
	public ArrayList<Family> getFamilies(){
		return family;
	}

	private boolean controlPlacement() {
		if ((!multi && this.family.size() == 0) || multi)
			return true;
		else
			return false;
	}

	public void addBonus(String type, ResourceAbstract resource) {
		bonus.put(type, resource);
	}

	public void applyBonus(Player player) { // accede a risorse del Player e
											// aggiunge il bonus

	}

	public int getActionCost() {
		return this.actionCost;
	}

}
