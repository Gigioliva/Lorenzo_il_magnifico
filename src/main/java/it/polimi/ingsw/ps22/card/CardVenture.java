package it.polimi.ingsw.ps22.card;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.effect.EndEffect;
import it.polimi.ingsw.ps22.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Resource;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class CardVenture extends DevelopmentCard {

	private ArrayList<RequisiteCost> requisiteCost;
	private ArrayList<EndEffect> endEffects;
	private ArrayList<ImmediateEffect> immediateEffects;
	
	public CardVenture(){
		this.requisiteCost=new ArrayList<RequisiteCost>();
		this.endEffects=new ArrayList<EndEffect>();
		this.immediateEffects=new ArrayList<ImmediateEffect>();
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
	
	public void applyImmediateEffects(Player player, Board board) {
		for (ImmediateEffect el : immediateEffects) {
			el.performEffect(player, board);
		}
	}
	
	public void loadEndEffects(Player player, Board board) {
		//Accedi a player e caricali nel EndEffect Arraylist
	}
	
	@Override
	public ArrayList<Integer> getAffordableCosts(Player player){
		ArrayList<Integer> affordableCosts = new ArrayList<Integer>();
		for(RequisiteCost item: requisiteCost){
			if (canAffordCostRequisite(item,player))
				affordableCosts.add(requisiteCost.indexOf(item));
		}
		return affordableCosts;
	}
	
	
	private boolean controlType(ResourceAbstract type){
		return (type instanceof Resource);
	}
	
	//controlla che un giocaotore possa soddisfare un singolo costo
	private boolean controlSingleCost(RequisiteCost cost, String type, Player player){
		if (controlType(cost.getCost().get(type))){
			int playerResource = player.getResources().get(type).getQuantity();
			int costResource = cost.getSpecificCost(type);
			if (costResource>playerResource)
				return false;
		}
		else{
			int playerPoints= player.getPoints().get(type).getQuantity();
			int costResource = cost.getSpecificCost(type);
			if (costResource>playerPoints)
				return false;
		}
		return true;
	}
	
	//controlla che un certo requisito sia soddisfatto dal giocatore
	private boolean controlSingleRequisite(RequisiteCost requisite, String type, Player player){
		if (controlType(requisite.getRequisite().get(type))){
			int playerResource = player.getResources().get(type).getQuantity();
			int requisiteResource = requisite.getSpecificCost(type);
			if (requisiteResource>playerResource)
				return false;
		}
		else{
			int playerPoints= player.getPoints().get(type).getQuantity();
			int costResource = requisite.getSpecificRequisite(type);
			if (costResource>playerPoints)
				return false;
		}
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
	
	//ritorna vero se esiste un costo-requisito che il player si può permettere
	@Override
	public boolean takeCardControl(Player player){
		for(RequisiteCost item: requisiteCost){
			if (canAffordCostRequisite(item,player))
				return true;
		}
		return false;
	}
	
	/*
	public void applyAllEffects(Player player, Board board){
		loadEndEffects(player,board);
		applyImmediateEffects(player, board);
	}*/

}