package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.card.CardBuilding;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.parser.CardSortByEra;
import it.polimi.ingsw.ps22.server.parser.ZoneBonusSaxParser;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class TowerBuildingZone extends TowerZone {

	private static final long serialVersionUID = 1L;
	
	public TowerBuildingZone() {
		super();
		HashMap<Integer, ArrayList<CardBuilding>> temp=CardSortByEra.buildingSortByEra();
		for(int i=0; i<6;i++){
			ArrayList<DevelopmentCard> card=new ArrayList<DevelopmentCard>();
			for(int j=0;j<4;j++){
				card.add(temp.get((i/2)+1).remove(0));
			}
			cards.put(i+1,card);
		}
		ArrayList<HashMap<String, ResourceAbstract>> bonus=new ArrayList<HashMap<String, ResourceAbstract>>();
		ZoneBonusSaxParser.BonusRead("",bonus);
		for (int i = 0; i < NUM_SPACES; i++) {
			towerSpaces[i].addBonus(bonus.get(i));
		}
	}

	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		int actionValue = family.getValue() + player.getBonusAcc().getBonus("IncrementBuilding").getQuantity();
		if (0<=actionSpace && actionSpace<=NUM_SPACES && !(family.isPlaced())
				&& (towerSpaces[actionSpace].controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& checkAllSpace(player) && checkResources(player, towerSpaces[actionSpace])
				&& checkActionValue(numServant, towerSpaces[actionSpace], family, actionValue)) {
			return true;
		}
		return false;
	}
	
	public void placeFamily(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		applyServant(family, numServant);
		towerSpaces[actionSpace].addFamily(family);
		if (!(player.getSpecBonus().returnBool("NoGainTowers")
				&& (towerSpaces[actionSpace].getPlan() == 3 || towerSpaces[actionSpace].getPlan() == 4))) {
			towerSpaces[actionSpace].applyBonus(player);
		}
		if (occupied && !(player.getSpecBonus().returnBool("NoCostTower"))) {
			player.getSpecificResource("Coin").subResource(new Coin(3));
		}
		setOccupied();
	}
	
	public void takeCard(int actionSpace, Player player){
		towerSpaces[actionSpace].getCard().applyCostToPlayer(player);
		towerSpaces[actionSpace].getCard().applyImmediateEffects(player);
		player.getDevelopmentCard("Building").add(towerSpaces[actionSpace].getCard());
		towerSpaces[actionSpace].removeCard();
	}
	
	public void takeCard(int actionSpace, Player player, HashMap<String, ResourceAbstract> discount){
		applyDiscount(player, "Building", discount);
		takeCard(actionSpace, player);
		deApplyDiscount(player, "Building", discount);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Building Tower \n" +  super.toString());
		return str.toString();
	}

}
