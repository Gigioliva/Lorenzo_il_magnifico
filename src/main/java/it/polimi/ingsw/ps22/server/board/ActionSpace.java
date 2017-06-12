package it.polimi.ingsw.ps22.server.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public abstract class ActionSpace implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final int actionCost;
	private transient boolean multi;
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
		family.setPlaced(true);
	}
	
	public void resetFamily(){
		for(Family el: family){
			el.setPlaced(false);
		}
		this.family=new ArrayList<Family>();
	}
	
	public ArrayList<Family> getFamilies(){
		return family;
	}

	public boolean controlPlacement() {
		if ((!multi && this.family.size() == 0) || multi){
			return true;
		}else
			return false;
	}

	public void addBonus(HashMap<String,ResourceAbstract> other) {
		for(String type: other.keySet()){
			bonus.addGain(type, other.get(type));
		}
	}

	public void applyBonus(Player player) {
		bonus.performEffect(player);
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
	
	private String familyString() {
		
		StringBuilder str = new StringBuilder();
		
		if(family.size() == 0)
			str.append("no family members \n");
		
		else{
			str.append("Family memebers here: \n");
			for(Family fam: family){
				str.append("  Player: " + fam.getPlayer().getUsername());
				str.append("  " + fam.toString() + "\n");
			}
				
		}
			
		return str.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("Action cost: " + actionCost);
		
		str.append("\n"+ familyString() + "\n");
		
		str.append(bonus.toString() + "\n");
		
		return str.toString();
	}

}
