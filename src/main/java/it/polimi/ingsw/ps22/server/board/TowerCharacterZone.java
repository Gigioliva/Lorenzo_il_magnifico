package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.card.CardCharacter;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.parser.CardSort;
import it.polimi.ingsw.ps22.server.parser.ZoneBonusSaxParser;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class TowerCharacterZone extends TowerZone {
	
	private static final long serialVersionUID = 1L;

	public TowerCharacterZone() {
		super();
		HashMap<Integer, ArrayList<CardCharacter>> temp=CardSort.characterSortByEra();
		for(int i=0; i<6;i++){
			ArrayList<DevelopmentCard> card=new ArrayList<DevelopmentCard>();
			for(int j=0;j<4;j++){
				card.add(temp.get((i/2)+1).remove(0));
			}
			cards.put(i+1,card);
		}
		ArrayList<HashMap<String, ResourceAbstract>> bonus=new ArrayList<HashMap<String, ResourceAbstract>>();
		ZoneBonusSaxParser.BonusRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/TowerCharacter.xml",bonus);
		for (int i = 0; i < NUM_SPACES; i++) {
			towerSpaces[i].addBonus(bonus.get(i));
		}
	}

	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		int actionValue = family.getValue() + player.getBonusAcc().getBonus("IncrementCharacter").getQuantity();
		if (0<=actionSpace && actionSpace<=NUM_SPACES && !(family.isPlaced())
				&& (towerSpaces[actionSpace].controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& (family.getColor()==Color.NEUTRAL || checkAllSpace(player)) && checkResources(player, towerSpaces[actionSpace])
				&& checkActionValue(numServant, towerSpaces[actionSpace], family, actionValue)) {
			return true;
		}
		return false;
	}

	public void placeFamily(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		applyServant(family, numServant);
		towerSpaces[actionSpace].addFamily(family);
		if (!(player.getSpecBonus().returnBool("GainTowers")
				&& (towerSpaces[actionSpace].getPlan() == 3 || towerSpaces[actionSpace].getPlan() == 4))) {
			towerSpaces[actionSpace].applyBonus(player);
		}
		if (occupied && !(player.getSpecBonus().returnBool("NoCostTower"))) {
			player.getSpecificResource("Coin").subResource(new Coin(3));
		}
		setOccupied();
	}
	
	public void takeCard(int actionSpace, Player player){
		if(towerSpaces[actionSpace].getCard()!=null){
			towerSpaces[actionSpace].getCard().applyCostToPlayer(player);
			towerSpaces[actionSpace].getCard().applyImmediateEffects(player);
			towerSpaces[actionSpace].getCard().applyPermanentEffects(player);
			player.getDevelopmentCard("Character").add(towerSpaces[actionSpace].getCard());
			towerSpaces[actionSpace].removeCard();
		}
	}
	
	public void takeCard(int actionSpace, Player player, HashMap<String, ResourceAbstract> discount){
		applyDiscount(player, "Character", discount);
		takeCard(actionSpace, player);
		deApplyDiscount(player, "Character", discount);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Character Tower \n" +  super.toString());
		return str.toString();
	}

}
