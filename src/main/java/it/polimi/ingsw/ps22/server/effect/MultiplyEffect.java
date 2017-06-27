package it.polimi.ingsw.ps22.server.effect;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
//  factor1								//factor2
//guadagna multiplicand multiplicandType per ogni multiplierQty di tipo multiplier
public class MultiplyEffect implements ActionEffect, ImmediateEffect {
	
	private static final long serialVersionUID = 1L;
	private String multiplier;
	private int multiplierQty = 1;
	private ResourceAbstract multiplicand;
	private String multiplicandType;
						//Territory	(factor2)		Coin	(factor1)				// Coin come 
	//public MultiplyEffect(String multiplier, ResourceAbstract multiplicand, String multiplicandType){

	/**
	 * It performs the effect by adding to the player the right amount of {@link ResourceAbstract}. This is calculated in the following way:
	 * The player gains multiplicand multiplicandType every multiplierQty multiplier. 
	 * @param player affected by the effect
	 * @param model that represent the state of the game
	 */
	@Override
	public void performEffect(Player player, Model model) {
		ArrayList<String> resources = new ArrayList<String>();
		resources.add(multiplicandType);
		if (player.isCard(multiplier)){
			int numeroCarte = player.getDevelopmentCard(multiplier).size();
			ResourceAbstract addingqty = new ResourceAbstract(numeroCarte / multiplierQty * multiplicand.getQuantity());
			player.getSpecificResource(multiplicandType).addResource(addingqty);
		}
		else{
			ResourceAbstract playerResource = player.getSpecificResource(multiplicandType);
			ResourceAbstract playerMultiplier = player.getSpecificResource(multiplier);
			playerResource.addResource(new ResourceAbstract((playerMultiplier.getQuantity() / multiplierQty) * multiplicand.getQuantity() ));
		}
		player.applyMalusResource(resources);
	}
	
	@Override
	public MultiplyEffect clone(){
		MultiplyEffect temp=new MultiplyEffect();
		temp.multiplier=this.multiplier;
		temp.multiplierQty=this.multiplierQty;
		temp.multiplicand=this.multiplicand.clone();
		temp.multiplicandType=this.multiplicandType;
		return temp;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append(multiplicand.getQuantity() + " " +  multiplicandType + " X " + multiplierQty + " "+ multiplier  + "\n");
		return str.toString();
		
	}

	@Override
	public boolean canAffordCost(Player player) {
		return true;
	}

	
	/**
	 * it sets the quantity of the multiplier
	 * @param qty
	 */
	public void setMultiplierQty(int qty){
		this.multiplierQty = qty;
	}
	
	/**
	 * It sets the type of the multiplier
	 * @param multiplier
	 */
	public void setMultiplier(String multiplier) {
		this.multiplier = multiplier;
	}

	/**
	 * It sets the {@link ResourceAbstract} of the multiplicand
	 * @param multiplicand
	 */
	public void setMultiplicand(ResourceAbstract multiplicand) {
		this.multiplicand = multiplicand;
	}

	/**
	 * It sets the type of the multiplicand
	 * @param multiplicandType
	 */
	public void setMultiplicandType(String multiplicandType) {
		this.multiplicandType = multiplicandType;
	}

}
