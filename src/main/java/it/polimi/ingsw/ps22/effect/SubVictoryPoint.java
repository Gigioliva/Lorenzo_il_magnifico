package it.polimi.ingsw.ps22.effect;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.card.RequisiteCost;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.BonusAbstract;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.resource.VictoryPoint;

public class SubVictoryPoint implements EndEffect {
	private HashMap<String, BonusAbstract> weights;
	private String loc; // rappresenta se Player o tipo carta, è uguale a "player" se si riferisce ad un giocatore
	
	

	public SubVictoryPoint(String loc) {
		weights=new HashMap<String, BonusAbstract>();
		this.loc = loc;
	}
	
	public void addBonus(String type,BonusAbstract value){
		 weights.put(type, value);
	 }


	//ritorna il numero totale di punti vittoria da sottrarre (caso giocatore)
	private int weightedSum(Player player){
		int sum = 0;
		for(String type: weights.keySet()){
			sum += player.getSpecificResource(type).getQuantity() / weights.get(type).getQuantity();
		}
		return sum;
	}
	
	private HashMap<String,ResourceAbstract> getVentureCost(Player player){
		
		HashMap<String,ResourceAbstract> costMap = new HashMap<String, ResourceAbstract>();
		ArrayList<DevelopmentCard> playerCards = player.getDevelopmentCard("Venture");
		
		for(DevelopmentCard card: playerCards){
			ArrayList<RequisiteCost> costs = card.getRequisiteCost();
			for(RequisiteCost cost: costs)
				for(String type: cost.getCost().keySet())
					if (!costMap.containsKey(type))
						costMap.put(type, cost.getCost().get(type));
						else 
							costMap.get(type).addResource(cost.getCost().get(type));
		}
		
		return costMap;
			
	}
	
	//ritorna il numero totale di punti vittoria da sottrarre (caso carta)
	private int weightedSum(Player player, String cardType){
		int sum = 0;
		if(!cardType.equals("Venture")){
			ArrayList<DevelopmentCard> cards =  player.getDevelopmentCard(cardType);
			for(DevelopmentCard card: cards){
				for(String cost: card.getCost().keySet()){
					if(weights.containsKey(cost))
						sum = sum + card.getCost().get(cost).getQuantity() / weights.get(cost).getQuantity();
				}
			}
			return sum;
		}
		else{
			HashMap<String,ResourceAbstract> ventureCosts = getVentureCost(player);
				for(String cost: ventureCosts.keySet()){
					if(weights.containsKey(cost))
						sum = sum + ventureCosts.get(cost).getQuantity() / weights.get(cost).getQuantity();
				}
			return sum;
			}
	}
	
	@Override
	public void performEffect(Player player, Board board) {
		if (player.isCard(loc)){
			VictoryPoint subPoints = new VictoryPoint(-weightedSum(player,loc));
			player.getSpecificResource("VictoryPoint").addResource(subPoints);
		}
		else {
			VictoryPoint subPoints = new VictoryPoint(-weightedSum(player));
			player.getSpecificResource("VictoryPoint").addResource(subPoints);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("You lose 1 victory point for every: \n");
		for(String type: weights.keySet()){
				str.append("  " + weights.get(type).getQuantity() + " " + type + "\n");
		}
		
		if(!loc.equals("player")){
			str.append("in the cost of every " + loc + " that you have \n");
		}
		else{
			str.append("in your resources \n");
		}
		return str.toString();
	}

}
