package it.polimi.ingsw.ps22.server.card;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.effect.ActionEffect;
import it.polimi.ingsw.ps22.server.effect.EndEffect;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;

/**
 * 
 * Extension of the abstract class {@link DevelopmentCard}, it implements a representation of the Venture Card. 
 * For this type of card, we may have action value, multiple {@link ActionEffect}, and multiple {@link ImmediateEffect}.
 * No more type of effect are allowed.
 *
 */

public class CardTerritory extends DevelopmentCard {
	
	private static final long serialVersionUID = 1L;
	private int actionValue;
	private ArrayList<ImmediateEffect> immediateEffects;
	private ArrayList<ActionEffect> actionEffects;

	/**
	 * it creates an empty Territory Card, with no {@link ActionEffect}, no {@link ImmediateEffect}, and 0 action value
	 */
	public CardTerritory() {
		this.immediateEffects = new ArrayList<>();
		this.actionEffects = new ArrayList<>();
		this.actionValue = 0;
	}
	
	/**
	 * It creates a copy of the CardTerriotry.
	 * @return an instance of {@link CardTerritory}, with the same effects and action value as the given one.
	 */
	@Override
	public CardTerritory clone() {
		CardTerritory temp=new CardTerritory();
		temp.setEra(this.getEra());
		temp.setName(this.getName());
		temp.actionValue=this.actionValue;
		for(ImmediateEffect el: immediateEffects){
			temp.addImmediateEffect(el.clone());
		}
		for(ActionEffect el: actionEffects){
			temp.addActionEffect(el.clone());
		}
		return temp;
	}

	/**
	 * it adds the given {@link ImmediateEffect} to the card
	 * @param the effect to be added to the card
	 */
	@Override
	public void addImmediateEffect(ImmediateEffect effect) {
		this.immediateEffects.add(effect);
	}

	/**
	 * it adds the given {@link ActionEffect} to the card
	 * @param the effect to be added to the card
	 */
	@Override
	public void addActionEffect(ActionEffect effect) {
		this.actionEffects.add(effect);
	}

	/**
	 * It applies all the {@link ImmediateEffect} of the card to the player
	 * @param player the {@link Player} to which you want to apply the effects
	 * @param model an instance of {@link Model}
	 */
	@Override
	public void applyImmediateEffects(Player player, Model model) {
		for (ImmediateEffect el : immediateEffects) {
			el.performEffect(player, model);
			if (player.getSpecBonus().returnBool("DoubleGain") && el instanceof GainResource) {
				((GainResource)el).doubleGain(player);
			}
		}
	}

	/**
	 * @return an ArrayList containing all the {@link EndEffect} of the card
	 */
	@Override
	public ArrayList<ActionEffect> getActionEffects() {
		return this.actionEffects;
	}

	/**
	 * It applies the nth {@link ActionEffect} of the card to the player
	 * @param player the {@link Player} to which you want to apply the effect
	 * @param model an instance of {@link Model}
	 * @param number an integer representing the effect to apply 
	 */
	@Override
	public void applyActionEffect(Player player, int number, Model model) {
		try{
			this.actionEffects.get(number).performEffect(player, model);
		}catch (IndexOutOfBoundsException e){
			return;
		}
	}

	/**
	 * @return the action value relative to this card.
	 */
	@Override
	public int getActionValue() {
		return this.actionValue;
	}
	
	/**
	 * It sets the action value.
	 * @param actionValue the action value of the card
	 */
	public void setActionValue(int actionValue) {
		this.actionValue=actionValue;
	}
	
	/**
	 *  The method returns true if a {@link Player} can afford to take the card. 
	 *  A player can afford to take a card he has the right amount of {@link MilitaryPoint}.
	 * @param player the player for which you want to do the control
	 */
	@Override
	public boolean takeCardControl(Player player){
		int size = player.getSizeCard("Territory");
		//se il giocatore ha già raggiunto il limite di carte territorio non può prendere la carta
		if (size >= player.getPersonalBoard().getRequirementHarvest().size())
			return false;
		else{
			int requirementHarvest = player.getPersonalBoard().getRequirementHarvest().get(size+1).getQuantity();
			int harvestPoint = player.getSpecificResource("MilitaryPoint").getQuantity();
			return requirementHarvest <= harvestPoint;
		}
	}
	
	
	/**
	 * Returns a string representation of this card. 
	 * @return a string containing the most important informations about this card
	 */
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		
		str.append("nome carta = " + this.getName() + "\n");
		
		str.append("era: " + super.getEra() + "\n");
		
		str.append("valore azione = " + actionValue + "\n");
		/*
		if(immediateEffects.size() > 0){
			str.append("Immediate effects: \n");
			for(int i = 0; i < immediateEffects.size(); i++){
				str.append("  [" + (i+1) + "]" + immediateEffects.get(i).toString() + "\n");
			}
		}
		
		str.append("Action effects: \n");
		for(int i = 0; i < actionEffects.size(); i++){
			str.append("  [" + (i+1) + "]" + actionEffects.get(i).toString() + "\n");
		}*/
		return str.toString();
	}

}
