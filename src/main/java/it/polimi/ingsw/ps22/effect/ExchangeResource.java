package it.polimi.ingsw.ps22.effect;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.controller.Ask;
import it.polimi.ingsw.ps22.model.Model;
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
		player.applyMalusResource(new ArrayList<String>(gain.keySet()));
		if(cont > 0){
			Ask ask = Model.getAsk();
			ask.askPrivilegeChange(cont);
		}
	}
	
	@Override
	public void performEffect(Player player, Board board) {
		if(canAffordCost(player)){
			subCostFromPlayer(player);
			addGainToPlayer(player);
		}
	}
	

}
