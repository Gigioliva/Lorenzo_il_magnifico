package it.polimi.ingsw.ps22.effect;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.BonusAbstract;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.resource.VictoryPoint;

public class SubVictoryPoint implements EndEffect {
	private HashMap<String, BonusAbstract> weights;
	private String loc; // rappresenta se Player o tipo carta
	
	

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
			sum = sum + player.getSpecificResource(type).getQuantity() / weights.get(type).getQuantity();
		}
		return sum;
	}
	
	/*private HashMap<String,ResourceAbstract> getVentureCost(Player player){
		for(DevelopmentCard card: player.getDevelopmentCard("CardVenture")){
			
		}
			
	}
	
	//ritorna il numero totale di punti vittoria da sottrarre (caso carta)
	private int weightedSum(Player player, String cardType){
		ArrayList<DevelopmentCard> cards =  player.getDevelopmentCard(cardType);
		int sum = 0;
		if(!cardType.equals("CardVenture")){
			for(DevelopmentCard card: cards){
				for(String cost: card.getCost().keySet()){
					if(weights.containsKey(cost))
						sum = sum + player.getSpecificResource(cost).getQuantity() / weights.get(cost).getQuantity();
				}
			}
			return sum;
		}
		else{
			
		}
	}*/
	
	@Override
	public void performEffect(Player player, Board board) {
		/*if (player.isCard(loc)){
			VictoryPoint subPoints = new VictoryPoint(-weightedSum(player,loc));
			player.getSpecificResource("VictoryPoint").addResource(subPoints);
		}
		else {
			VictoryPoint subPoints = new VictoryPoint(-weightedSum(player));
			player.getSpecificResource("VictoryPoint").addResource(subPoints);
		}*/
	}

}
