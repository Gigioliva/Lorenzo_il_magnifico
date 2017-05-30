package it.polimi.ingsw.ps22.board;

import java.util.HashMap;

import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class TowerCharacterZone extends TowerZone {

	public TowerCharacterZone(Board board) {
		super(board);
	}

	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		int actionValue = family.getValue() + player.getBonusAcc().getBonus("IncrementCharacter").getQuantity();
		if (!(family.isPlaced())
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
	}
	
	public void takeCard(int actionSpace, Player player){
		towerSpaces[actionSpace].getCard().applyCostToPlayer(player);
		towerSpaces[actionSpace].getCard().applyImmediateEffects(player, board);
		player.getDevelopmentCard("Character").add(towerSpaces[actionSpace].getCard());
		towerSpaces[actionSpace].removeCard();
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
