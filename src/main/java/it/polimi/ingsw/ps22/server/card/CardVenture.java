package it.polimi.ingsw.ps22.server.card;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.effect.Effect;
import it.polimi.ingsw.ps22.server.effect.EndEffect;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

/**
 * 
 * Extension of the abstract class {@link DevelopmentCard}, it implements a representation of the Venture Card. 
 * For this type of card, we may have multiple {@link RequisiteCost}, multiple {@link EndEffect} and multiple {@link ImmediateEffect}.
 * No more type of effect are allowed.
 *
 */
public class CardVenture extends DevelopmentCard {
	
	
	private static final long serialVersionUID = 1L;
	private ArrayList<RequisiteCost> requisiteCost;
	private ArrayList<EndEffect> endEffects;
	private ArrayList<ImmediateEffect> immediateEffects;
	
	/**
	 * it creates an empty Venture Card, with no {@link RequisiteCost}, no {@link EndEffect}, no {@link ImmediateEffect}
	 */
	public CardVenture(){
		this.requisiteCost=new ArrayList<>();
		this.endEffects=new ArrayList<>();
		this.immediateEffects=new ArrayList<>();
	}
	
	/**
	 * It creates a copy of the CardVenture.
	 * @return an instance of {@link CardVenture}, with the same effects as the given one.
	 */
	@Override
	public CardVenture clone() {
		CardVenture temp=new CardVenture();
		temp.setName(this.getName());
		temp.setEra(this.getEra());
		for(RequisiteCost el: requisiteCost){
			temp.requisiteCost.add(el.clone());
		}
		for(ImmediateEffect el: immediateEffects){
			temp.addImmediateEffect(el.clone());
		}
		for(EndEffect el: endEffects){
			temp.addEndEffect(el.clone());
		}
		return temp;
	}
	
	/**
	 * 
	 * @return It returns an {@link ArrayList} containing all the {@link RequisiteCost} of the card
	 */
	@Override
	public ArrayList<RequisiteCost> getRequisiteCost(){
		return this.requisiteCost;
	}
	
	
	/**It adds to the card a new {@link RequisiteCost}.
	 * @param cost : {@link HashMap} containing a possible cost of the card. 
	 * @param requisite : {@link HashMap} containing the corresponding requisite. 
	 */
	@Override
	public void addRequisiteCost(HashMap<String, ResourceAbstract> cost, HashMap<String, ResourceAbstract> requisite){
		RequisiteCost temp=new RequisiteCost();
		temp.addCost(cost);
		temp.addRequisite(requisite);
		requisiteCost.add(temp);
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
	 * it adds the given {@link EndEffect} to the card
	 * @param the effect to be added to the card
	 */
	@Override
	public void addEndEffect(EndEffect effect) {
		this.endEffects.add(effect);
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
	 * It adds to {@link EndEffect} of the player all the {@link EndEffect} of the card
	 * @parm player the {@link Player} to which you want to add the effects
	 */
	@Override
	public void loadEndEffects(Player player) {
		//Accedi a player e caricali nel EndEffect Arraylist
		try{
			player.getEndEffects().addAll(endEffects);
		}
		catch (NullPointerException e){ 
			return;
		}
	}
	
	/**
	 * It calculates all the {@link Effect} that the player can afford to pay for the given card.
	 * In particular a player can afford a cost if 
	 * he has in his resources all the resources required by the requisite and by the cost. 
	 * @param player the {@link Player} for which you want to get the possible costs
	 * @return an ArrayList containing all the affordable {@link RequisiteCost}
	 */
	@Override
	public ArrayList<RequisiteCost> getAffordableCosts(Player player){
		ArrayList<RequisiteCost> affordableCosts = new ArrayList<>();
		for(RequisiteCost item: requisiteCost){
			if (canAffordCostRequisite(item,player))
				affordableCosts.add(item);
		}
		return affordableCosts;
	}
	
	
	//controlla che un giocaotore possa soddisfare un singolo costo
	private boolean controlSingleCost(RequisiteCost cost, String type, Player player){
		int playerResource = player.getSpecificResource(type).getQuantity();
		int costResource = cost.getSpecificCost(type);
		if (costResource>playerResource)
			return false;
		return true;
	}
	
	//controlla che un certo requisito sia soddisfatto dal giocatore
	private boolean controlSingleRequisite(RequisiteCost requisite, String type, Player player){

		int playerResource = player.getSpecificResource(type).getQuantity();
		int requisiteResource = requisite.getSpecificCost(type);
		if (requisiteResource>playerResource)
			return false;
		return true;
	}
	
	//controlla singolo requisiteCost, ritorna vero se il giocatore può permetterselo
	private boolean canAffordCostRequisite(RequisiteCost item, Player player){

		ArrayList<String> costkeys = new ArrayList<>(item.getCost().keySet());
		ArrayList<String> requisitekeys = new ArrayList<>(item.getRequisite().keySet());
		for (String type: costkeys){
			//se il giocatore non si può permettere il costo della carta, ritorna false
			if (!controlSingleCost(item, type, player))
				return false;
		}
		for(String type: requisitekeys){
			// se il giocatore non può permettersi il requisito della carta, ritorna false
			if(!controlSingleRequisite(item,type,player))
				return false;
		}
		return true;

	}
	
	/**
	 * It returns the cost that the {@link Player} has to pay considering the malus and bonus 
	 * that the player accumulated during the game
	 * @param player for which you want to get the actual costs
	 * @return an ArrayList containing the {@link RequisiteCost} updated according to bonus and malus
	 */
	private ArrayList<RequisiteCost> getActualCost(Player player){
		
		ArrayList<RequisiteCost> actualCosts = new ArrayList<>();
		
		for(RequisiteCost requisiteCost: requisiteCost){
			HashMap<String,ResourceAbstract> newCost = new HashMap<>();
			for(String type: requisiteCost.getCost().keySet()){
				ResourceAbstract specificCost = requisiteCost.getCost().get(type);
				ResourceAbstract reduction = player.getBonusAcc().getSaleVenture().get(type);
				if(reduction.getQuantity() > specificCost.getQuantity()){
					newCost.put(type, new ResourceAbstract(0));
				}
				else{
					newCost.put(type, new ResourceAbstract(specificCost.getQuantity() - reduction.getQuantity()));
				}
			}
			RequisiteCost newRequisiteCost = new RequisiteCost();
			newRequisiteCost.addCost(newCost);
			newRequisiteCost.addRequisite(requisiteCost.getRequisite());
			actualCosts.add(newRequisiteCost);
		}
		return actualCosts;
	}
	
	/**
	 * It applies the cost given in input to the given {@link Player}.
	 * @requires that the player can afford the card
	 * @param chosenCost the {@link RequisiteCost} to apply to the player
	 * @param player the player to which you want to apply the cost
	 */
	@Override
	public void applyCostToPlayer(Player player, RequisiteCost chosenCost){
		 for(String type: chosenCost.getCost().keySet()){
			 player.getSpecificResource(type).subResource(chosenCost.getCost().get(type));
		 }
	}
	
	
	/**
	 *  The method returns true if a {@link Player} can afford to take the card. 
	 *  A player can afford to take a card if exists at least one {@link RequisiteCost} that he can afford.
	 * @param player the player for which you want to do the control
	 */
	@Override
	public boolean takeCardControl(Player player){
		ArrayList<RequisiteCost> actualCosts = getActualCost(player);
		for(RequisiteCost item: actualCosts){
			if (canAffordCostRequisite(item,player))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns a string representation of this card. 
	 * @return a string containing information about the name, the era, the costs and the effects of the given card
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("nome carta: " + super.getName()+"\n");
		
		str.append("era: " + super.getEra() + "\n");
		
		for(RequisiteCost cost: requisiteCost){
			str.append(cost.toString());
			str.append("or\n");
		}
		
		if(immediateEffects.size() > 0){
			str.append("immediate effects: \n");
			int i = 1;
			for(ImmediateEffect effect: immediateEffects){
				str.append("  " + "[" + i +"] " + effect.toString());
				i++;
			}
		}
		
		if(endEffects.size() > 0){
			str.append("end effects: \n");
			int i = 1;
			for(EndEffect effect: endEffects){
				str.append("  " + "[" + i +"] "  + effect.toString());
				i++;
			}
		}
		
		return str.toString();
	}
	
	/**
	 * @return an ArrayList containing all the {@link EndEffect} of the card
	 */
	public ArrayList<EndEffect> getEndEffect(){
		return endEffects;
	}

}