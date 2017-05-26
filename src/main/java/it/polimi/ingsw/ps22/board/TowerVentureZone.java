package it.polimi.ingsw.ps22.board;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.card.RequisiteCost;
import it.polimi.ingsw.ps22.message.askCosts;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class TowerVentureZone extends TowerZone {
	
	//salvo i valori per sospendere il model finchè il giocatore non risponde
	private ArrayList<RequisiteCost> possibleCost;
	private Player player;
	private int actionSpace;

	public TowerVentureZone(Board board) {
		super(board);
		// leggi da file e carica usando addCards le carte nelle torri

	}

	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		int actionValue = family.getValue() + player.getBonusAcc().getBonus("IncrementVenture").getQuantity();
		if (!(family.isPlaced())
				&& (towerSpaces[actionSpace].controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& checkAllSpace(player) && checkResources(player, towerSpaces[actionSpace])
				&& checkActionValue(numServant, towerSpaces[actionSpace], family, actionValue)) {
			towerSpaces[actionSpace].addFamily(family);
			if (!(player.getSpecBonus().returnBool("NoGainTowers")
					&& (towerSpaces[actionSpace].getPlan() == 3 || towerSpaces[actionSpace].getPlan() == 4))) {
				towerSpaces[actionSpace].applyBonus(player);
			}
			if (occupied && !(player.getSpecBonus().returnBool("NoCostTower"))) {
				player.getSpecificResource("Coin").subResource(new Coin(3));
			}
			ArrayList<RequisiteCost> possibleCost = towerSpaces[actionSpace].getCard().getAffordableCosts(player);
			ArrayList<HashMap<String, ResourceAbstract>> cost = new ArrayList<HashMap<String, ResourceAbstract>>();
			for (RequisiteCost el : possibleCost) {
				cost.add(el.getCost());
			}
			this.possibleCost=possibleCost;
			this.player=player;
			this.actionSpace=actionSpace;
			if(cost.size()==1){
				takeCard(0);
			} else{
				askCost(cost);
			}
			return true;
		}
		return false;
	}
	
	private void  askCost(ArrayList<HashMap<String, ResourceAbstract>> cost){
		askCosts ask=new askCosts(cost);
		ask.applyAsk();
	}
	
	public void takeCard(int choice){
		towerSpaces[actionSpace].getCard().applyCostToPlayer(player, possibleCost.get(choice));
		towerSpaces[actionSpace].getCard().applyImmediateEffects(player, board);
		towerSpaces[actionSpace].getCard().loadEndEffects(player, board);
		player.getDevelopmentCard("Venture").add(towerSpaces[actionSpace].getCard());
		towerSpaces[actionSpace].removeCard();
	}

}
