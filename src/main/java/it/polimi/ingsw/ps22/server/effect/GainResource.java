package it.polimi.ingsw.ps22.server.effect;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.message.AskCouncilPrivilege;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class GainResource implements ActionEffect, ImmediateEffect {

	private static final long serialVersionUID = 1L;
	private HashMap<String, ResourceAbstract> gain;

	public GainResource() {
		gain = new HashMap<String, ResourceAbstract>();
	}
	
	@Override
	public GainResource clone(){
		GainResource temp=new GainResource();
		for(String el: gain.keySet()){
			temp.gain.put(el, gain.get(el).clone());
		}
		return temp;
	}
	
	public void addGain(String type, ResourceAbstract resource) {
		gain.put(type, resource);
	}

	public HashMap<String, ResourceAbstract> getGain() {
		return gain;
	}

	/**
	 * It performs the effect by adding to the {@link Player} the {@link ResourceAbstract} provided by the effect. 
	 * @param player affected by the effect
	 * @param model that represent the state of the game
	 */
	@Override
	public void performEffect(Player player, Model model) {
		int cont = 0;
		for (String type : gain.keySet()) {
			if (type.equals("CouncilPrivilege")) {
				cont = gain.get(type).getQuantity();
			} else {
				player.getSpecificResource(type).addResource(gain.get(type));
			}
		}
		if (cont == 0) {
			player.applyMalusResource(new ArrayList<String>(gain.keySet()));
		}
		if (cont > 0) {
			AskCouncilPrivilege mex = new AskCouncilPrivilege(cont, player);
			model.notifyAsk(mex);
		}
	}

	/**
	 * Give to the {@link Player} a double gain of resources.
	 * @param player the player to which apply the effect
	 */
	public void doubleGain(Player player) {
		ArrayList<String> temp=new ArrayList<String>();
		for (String type : gain.keySet()) {
			if (player.isResource(type)) {
				player.getSpecificResource(type).addResource(gain.get(type));
				temp.add(type);
			}
		}
		player.applyMalusResource(temp);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String string : gain.keySet()) {
			str.append(gain.get(string).getQuantity() + " " + string + "\n");
		}
		return str.toString();
	}

	@Override
	public boolean canAffordCost(Player player) {
		return true;
	}

}
