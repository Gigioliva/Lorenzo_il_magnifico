package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.card.CardVenture;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.card.RequisiteCost;
import it.polimi.ingsw.ps22.server.message.AskCosts;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.parser.CardSort;
import it.polimi.ingsw.ps22.server.parser.ZoneBonusSaxParser;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

/**
 * 
 * This class extends the {@link TowerZone} implementing the specific
 * characteristics that the {@link CardVenture} require.
 *
 */
public class TowerVentureZone extends TowerZone {

	private static final long serialVersionUID = 1L;

	/**
	 * It instantiates a new Tower Venture
	 * @param model
	 */
	public TowerVentureZone(Model model) {
		super(model);
		HashMap<Integer, ArrayList<CardVenture>> temp = CardSort.ventureSortByEra();
		for (int i = 0; i < 6; i++) {
			ArrayList<DevelopmentCard> card = new ArrayList<DevelopmentCard>();
			for (int j = 0; j < 4; j++) {
				card.add(temp.get((i / 2) + 1).remove(0));
			}
			cards.put(i + 1, card);
		}
		ArrayList<HashMap<String, ResourceAbstract>> bonus = new ArrayList<HashMap<String, ResourceAbstract>>();
		ZoneBonusSaxParser.BonusRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/TowerVenture.xml",
				bonus);
		for (int i = 0; i < NUM_SPACES; i++) {
			if (bonus.get(i) != null) {
				towerSpaces[i].addBonus(bonus.get(i));
			}
		}

	}

	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		int actionValue = family.getValue() + player.getBonusAcc().getBonus("IncrementVenture").getQuantity();
		if (0 <= actionSpace && actionSpace < NUM_SPACES && !(family.isPlaced())
				&& (towerSpaces[actionSpace].controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& (family.getColor() == Color.NEUTRAL || checkAllSpace(player))
				&& checkResources(player, towerSpaces[actionSpace])
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
			towerSpaces[actionSpace].applyBonus(player, model);
		}
		if (occupied && !(player.getSpecBonus().returnBool("NoCostTower"))) {
			player.getSpecificResource("Coin").subResource(new Coin(3));
		}
		setOccupied();
	}

	public void takeCard(int actionSpace, Player player) {
		if (towerSpaces[actionSpace].getCard() != null) {
			ArrayList<RequisiteCost> possibleCost = towerSpaces[actionSpace].getCard().getAffordableCosts(player);
			if (possibleCost.size() == 1) {
				payCard(0, possibleCost, player, towerSpaces[actionSpace]);
			} else {
				AskCosts ask = new AskCosts(possibleCost, player, towerSpaces[actionSpace]);
				model.notifyAsk(ask);
			}
		}
	}

	public void takeCard(int actionSpace, Player player, HashMap<String, ResourceAbstract> discount) {
		if (towerSpaces[actionSpace].getCard() != null) {
			applyDiscount(player, "Venture", discount);
			ArrayList<RequisiteCost> possibleCost = towerSpaces[actionSpace].getCard().getAffordableCosts(player);
			if (possibleCost.size() == 1) {
				payCard(0, possibleCost, player, towerSpaces[actionSpace], discount);
			} else {
				AskCosts ask = new AskCosts(possibleCost, player, towerSpaces[actionSpace], discount);
				model.notifyAsk(ask);
			}
		}
	}

	/**
	 * This method is called to pay the {@link CardVenture} previously chosen. In particular
	 * it applies the cost of the card to the {@link Player}. 
	 * Finally it applies all the effects of the card
	 * @param choice an int representing the choice of the player among the possible costs
	 * @param possibleCost an ArrayList containing the costs that the player can afford
	 * @param player the target {@link Player}
	 * @param towerSpace the specific {@link TowerSpace}
	 */
	public void payCard(int choice, ArrayList<RequisiteCost> possibleCost, Player player, TowerSpace towerSpace) {
		towerSpace.getCard().applyCostToPlayer(player, possibleCost.get(choice));
		towerSpace.getCard().applyImmediateEffects(player, model);
		towerSpace.getCard().loadEndEffects(player);
		player.getDevelopmentCard("Venture").add(towerSpace.getCard());
		towerSpace.removeCard();
	}

	/**
	 * This method is called to pay the {@link CardVenture} previously chosen. In particular
	 * it applies the cost of the card to the {@link Player}, considering eventual discount. 
	 * Finally it applies all the effects of the card
	 * @param choice an int representing the choice of the player among the possible costs
	 * @param possibleCost an ArrayList containing the costs that the player can afford
	 * @param player the target {@link Player}
	 * @param towerSpace the specific {@link TowerSpace}
	 * @param discount eventual discount for the card
	 */
	public void payCard(int choice, ArrayList<RequisiteCost> possibleCost, Player player, TowerSpace towerSpace,
			HashMap<String, ResourceAbstract> discount) {
		payCard(choice, possibleCost, player, towerSpace);
		deApplyDiscount(player, "Venture", discount);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder(super.toString());
		return str.toString();
	}
}
