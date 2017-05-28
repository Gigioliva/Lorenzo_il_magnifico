package it.polimi.ingsw.ps22.effect;

import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Resource;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;


//riduce costo di acquisto di una carta
public class ReduceCostEffect implements PermanentEffect {
	 private HashMap<String,ResourceAbstract> bonus;  //accedere alla classe bonus di player e aggiungere
	 private String cardType;
	 
	 public ReduceCostEffect(String cardType){
		 bonus=new HashMap<String,ResourceAbstract>();
		 this.cardType = cardType;
	 }
	 
	 public void addBonus(String type,Resource value){
		 bonus.put(type, value);
	 }
	 
	@Override
	public void performEffect(Player player, Board board) {
		player.getBonusAcc().addSales(bonus,cardType);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Reduce cost of " + cardType + " by: \n");
		for(String type: bonus.keySet()){
			str.append(bonus.get(type).getQuantity() + " " + type + "\n");
		}
		return str.toString();
	}

}
