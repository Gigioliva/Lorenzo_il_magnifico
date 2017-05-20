package it.polimi.ingsw.ps22.effect;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class ExchangeResource implements ActionEffect{
	private HashMap<String,ResourceAbstract> cost;
	private HashMap<String,ResourceAbstract> gain;
	
	public ExchangeResource(){
		cost=new HashMap<String, ResourceAbstract>();
		gain=new HashMap<String, ResourceAbstract>();
	}
	
	public void addCost(String typeCost, ResourceAbstract costResource){
		cost.put(typeCost, costResource);
	}
	
	public void addGain(String typeCost, ResourceAbstract costResource){
		gain.put(typeCost, costResource);
	}
	
	//Controlla che il giocatore abbia suff risorse per lo scambio
	private boolean canAffordCost(Player player){
		for(String type: cost.keySet())
			if(player.getResources().get(type).getQuantity() < cost.get(type).getQuantity())
				return false;
		return true;
	}
	
	private void subCostFromPlayer(Player player){
		for(String type: cost.keySet()){
			player.getSpecificResource(type).subResource(cost.get(type));
		}
	}
	
	private void addGainToPlayer(Player player){
		for(String type: gain.keySet()){
			player.getSpecificResource(type).addResource(gain.get(type));
		}
		player.addResources(new ArrayList<String>(gain.keySet()));
	}
	
	@Override
	public void performEffect(Player player, Board board) {
		if(canAffordCost(player)){
			subCostFromPlayer(player);
			addGainToPlayer(player);
		}
	}
	

}
