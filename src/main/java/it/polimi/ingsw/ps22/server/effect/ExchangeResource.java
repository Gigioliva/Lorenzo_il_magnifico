package it.polimi.ingsw.ps22.server.effect;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.message.AskCouncilPrivilege;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

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
	
	//Controlla che il giocatore abbia suff risorse per lo scambio (suppongo che il costo non sia in privilegi del consiglio)
	@Override
	public boolean canAffordCost(Player player){
		for(String type: cost.keySet())
			if(player.isResource(type)){
				if(player.getResources().get(type).getQuantity() < cost.get(type).getQuantity())
					return false;
			}
			else{
				if(player.getPoints().get(type).getQuantity() < cost.get(type).getQuantity())
					return false;
			}
		return true;
	}
	
	private void subCostFromPlayer(Player player){
		for(String type: cost.keySet()){
			player.getSpecificResource(type).subResource(cost.get(type));
		}
	}
	
	private void addGainToPlayer(Player player){
		int cont = 0;
		for(String type: gain.keySet()){
			if(type.equals("CouncilPrivilege")){
				cont = gain.get(type).getQuantity();
			}
			else{
				player.getSpecificResource(type).addResource(gain.get(type));
			}
		}
		if(cont == 0){
			player.applyMalusResource(new ArrayList<String>(gain.keySet()));
		}
		if(cont>0){
			AskCouncilPrivilege mex = new AskCouncilPrivilege(cont, player);
			mex.applyAsk();
		}
	}
	
	@Override
	public void performEffect(Player player) {
		if(canAffordCost(player)){
			subCostFromPlayer(player);
			addGainToPlayer(player);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("You can exchange: \n");
		for(String type: cost.keySet()){
			str.append("  " + cost.get(type).getQuantity() + " " + type + "\n");
		}
		str.append("with: \n");
		for(String type: gain.keySet()){
			str.append("  " + gain.get(type).getQuantity() + " " + type + "\n");
		}
		return str.toString();
	}

	public HashMap<String, ResourceAbstract> getCost() {
		return cost;
	}

}
