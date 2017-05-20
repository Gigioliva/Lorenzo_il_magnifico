package it.polimi.ingsw.ps22.board;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.effect.GainResource;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public abstract class ActionSpace {
	private final int actionCost;
	private boolean multi;
	private boolean playable;
	private ArrayList<Family> family;
	private GainResource bonus;

	public ActionSpace(int actionCost, boolean multi) {
		this.actionCost = actionCost;
		this.multi = multi;
		playable=true;
		family = new ArrayList<Family>();
		bonus = new GainResource();
	}

	public void addFamily(Family family) {
			this.family.add(family);
	}
	
	public void resetFamily(){
		this.family=new ArrayList<Family>();
	}
	
	public ArrayList<Family> getFamilies(){
		return family;
	}

	public boolean controlPlacement() {
		if ((!multi && this.family.size() == 0) || multi)
			return true;
		else
			return false;
	}

	public void addBonus(HashMap<String,ResourceAbstract> other) {
		for(String type: other.keySet()){
			bonus.addGain(type, other.get(type));
		}
	}

	public void applyBonus(Player player) {
		bonus.performEffect(player, null);
	}
	
	public void deapplyBonus(Player player) {
		HashMap<String,ResourceAbstract> gain=bonus.getGain();
		ArrayList<String> temp=new ArrayList<String>(gain.keySet());
		for(String el: temp){
			player.getSpecificResource(el).subResource(gain.get(el));;
		}
	}

	public int getActionCost() {
		return this.actionCost;
	}
	
	public boolean getMulti(){
		return this.multi;
	}
	
	public void setNotPlayable(){
		this.playable=false;
	}
	public boolean isPlayable(){
		return playable;
	}

}
