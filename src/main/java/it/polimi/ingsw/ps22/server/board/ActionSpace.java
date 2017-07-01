package it.polimi.ingsw.ps22.server.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.effect.Effect;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

/**
 * 
 *This abstract class represents the abstract concept of space where a player can place familiars.
 *Every action space has an cost in terms of action value, can be playable or not depending on
 *{@link Effect} of card and number of {@link Player}s, can have one or more {@link Family} placed and 
 *can have a bonus for the {@link Player} that place his familiars there.
 *
 */
public abstract class ActionSpace implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final int actionCost;
	private boolean multi;
	private boolean playable;
	private ArrayList<Family> family;
	protected GainResource bonus;
	protected int maxSize;

	/**
	 * this constructor creates an action space with 0 familiars, that is playable, with no bonus, with a certain action cost
	 * and max size, and with possible multiple familiars or with one possible familiar
	 * @param actionCost the action value of the action space
	 * @param multi a boolean that is true if more than one familiar can be placed 
	 * @param maxSize the maximum number of familiars that can be placed here
	 */
	public ActionSpace(int actionCost, boolean multi, int maxSize) {
		this.actionCost = actionCost;
		this.multi = multi;
		playable = true;
		family = new ArrayList<Family>();
		bonus = new GainResource();
		this.maxSize=maxSize;
	}

	/**
	 * It adds a {@link Family} to the space
	 * @param family to be placed in this action space
	 */
	public void addFamily(Family family) {
		this.family.add(family);
		family.setPlaced(true);
	}
	
	/**
	 * It sets this action space as not occupied, removing all the placed familiars
	 */
	public void resetFamily(){
		for(Family el: family){
			el.setPlaced(false);
		}
		this.family=new ArrayList<Family>();
	}
	
	/**
	 * 
	 * @return an {@link ArrayList} containing all the {@link Family} placed.
	 */
	public ArrayList<Family> getFamilies(){
		return family;
	}

	/**
	 * Requiring that the space is playable, It controls if familiars can be placed here or not
	 * @return
	 */
	public boolean controlPlacement() {
		if ((!multi && this.family.size() < maxSize) || multi){
			return true;
		}else
			return false;
	}
	
	/**
	 * If we the players are 5, we allow an action space to contain more than one {@link Family}, 
	 * in particular we set this size to 2
	 */
	public void setFivePlayer(){
		maxSize=2;
	}

	/**
	 * this method adds a bonus to the action space.
	 * @param other an {@link HashMap} containing the bonus relative to this action space
	 */
	public void addBonus(HashMap<String,ResourceAbstract> other) {
		for(String type: other.keySet()){
			bonus.addGain(type, other.get(type));
		}
	}

	/**
	 * It sets the bonus of the action space to a {@link GainResource} effect
	 * @param bonus The {@link Effect} you want to set
	 */
	public void setBonus(GainResource bonus) {
		this.bonus=bonus;
	}
	
	/**
	 * It applies the bonus of the action space to a {@link Player}
	 * @param player that receive the bonus
	 * @param model an instance of model, the state of the game
	 */
	public void applyBonus(Player player, Model model) {
		bonus.performEffect(player, model);
	}
	
	/**
	 * This method remove from a {@link Player} the resources previously given by the bonus of this action space
	 * @param player affected by this method
	 */
	public void deapplyBonus(Player player) {
		HashMap<String,ResourceAbstract> gain=bonus.getGain();
		ArrayList<String> temp=new ArrayList<String>(gain.keySet());
		for(String el: temp){
			player.getSpecificResource(el).subResource(gain.get(el));;
		}
	}

	/**
	 *
	 * @return the action value of this action space
	 */
	public int getActionCost() {
		return this.actionCost;
	}
	
	/**
	 * 
	 * @return a boolean that is true if this action space can contain more than one {@link Family}
	 */
	public boolean getMulti(){
		return this.multi;
	}
	
	/**
	 * after this method is called, the action space won't be playable
	 */
	public void setNotPlayable(){
		this.playable=false;
	}
	
	/**
	 * 
	 * @return a boolean that is true if the action space is playable. 
	 */
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
	
	public abstract ActionSpace clone(ArrayList<Family> family);

}
