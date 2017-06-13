package it.polimi.ingsw.ps22.server.card;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.effect.EndEffect;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class CardVenture extends DevelopmentCard {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<RequisiteCost> requisiteCost;
	private ArrayList<EndEffect> endEffects;
	private ArrayList<ImmediateEffect> immediateEffects;
	
	public CardVenture(){
		this.requisiteCost=new ArrayList<RequisiteCost>();
		this.endEffects=new ArrayList<EndEffect>();
		this.immediateEffects=new ArrayList<ImmediateEffect>();
	}
	
	@Override
	public CardVenture clone() {
		CardVenture temp=new CardVenture();
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
	
	public ArrayList<RequisiteCost> getRequisiteCost(){
		return this.requisiteCost;
	}
	
	public void addRequisiteCost(HashMap<String, ResourceAbstract> cost, HashMap<String, ResourceAbstract> requisite){
		RequisiteCost temp=new RequisiteCost();
		temp.addCost(cost);
		temp.addRequisite(requisite);
		requisiteCost.add(temp);
	}
	
	public void addImmediateEffect(ImmediateEffect effect) {
		this.immediateEffects.add(effect);
	}
	
	public void addEndEffect(EndEffect effect) {
		this.endEffects.add(effect);
	}
	
	public void applyImmediateEffects(Player player) {
		for (ImmediateEffect el : immediateEffects) {
			el.performEffect(player);
			if (player.getSpecBonus().returnBool("DoubleGain") && el instanceof GainResource) {
				((GainResource)el).doubleGain(player);
			}
		}
	}
	
	public void loadEndEffects(Player player) {
		//Accedi a player e caricali nel EndEffect Arraylist
		try{
			player.getEndEffects().addAll(endEffects);
		}
		catch (NullPointerException e){ //lancia eccezione se endEffect è vuota, in tal caso semplicemente ritorno al chiamante
			return;
		}
	}
	
	@Override
	public ArrayList<RequisiteCost> getAffordableCosts(Player player){
		ArrayList<RequisiteCost> affordableCosts = new ArrayList<RequisiteCost>();
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

		ArrayList<String> costkeys = new ArrayList<String>(item.getCost().keySet());
		ArrayList<String> requisitekeys = new ArrayList<String>(item.getRequisite().keySet());
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
	
	public ArrayList<RequisiteCost> getActualCost(Player player){
		ArrayList<RequisiteCost> actualCosts = new ArrayList<RequisiteCost>();
		for(RequisiteCost requisiteCost: requisiteCost){
			HashMap<String,ResourceAbstract> newCost = new HashMap<String,ResourceAbstract>();
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
	
	@Override
	public void applyCostToPlayer(Player player, RequisiteCost chosenCost){
		 for(String type: chosenCost.getCost().keySet()){
			 player.getSpecificResource(type).subResource(chosenCost.getCost().get(type));
		 }
	}
	
	
	//ritorna vero se esiste un costo-requisito che il player si può permettere
	@Override
	public boolean takeCardControl(Player player){
		ArrayList<RequisiteCost> actualCosts = getActualCost(player);
		for(RequisiteCost item: actualCosts){
			if (canAffordCostRequisite(item,player))
				return true;
		}
		return false;
	}
	
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
			}
			i++;
		}
		
		return str.toString();
	}
	
	
	/*
	public void applyAllEffects(Player player, Board board){
		loadEndEffects(player,board);
		applyImmediateEffects(player, board);
	}*/

}