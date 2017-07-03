package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.action.ProductionAction;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.parser.ZoneBonusSaxParser;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Servant;

public class MarketZone extends Zone {
	
	private static final long serialVersionUID = 1L;
	private static final int NUM_SPACES = 4;
	private MarketSpace[] marketSpace;

	/**
	 * It instantiates the MarketZone with his {@link MarketSpace}s. The bonus is read from file. 
	 * @param model
	 */
	public MarketZone(Model model) {
		super(model);
		marketSpace = new MarketSpace[NUM_SPACES];
		ArrayList<HashMap<String, ResourceAbstract>> bonus=new ArrayList<HashMap<String, ResourceAbstract>>();
		ZoneBonusSaxParser.BonusRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/MarketSpace.xml",bonus);
		for (int i = 0; i < NUM_SPACES; i++) {
			marketSpace[i] = new MarketSpace(1, false,1);
			marketSpace[i].addBonus(bonus.get(i));
		}
	}

	public MarketZone(MarketSpace[] marketSp, ArrayList<Family> family) {
		super(null);
		marketSpace = new MarketSpace[NUM_SPACES];
		for (int i=0;i<NUM_SPACES;i++) {
			this.marketSpace[i] = marketSp[i].clone(family);
		}
	}
	
	@Override
	public MarketZone clone(ArrayList<Family> family) {
		return new MarketZone(this.marketSpace, family);
	}
	
	/**
	 * It controls that the {@link Player} has not placed any colorful {@link Family} in this zone,
	 * the action value, permanent effects, etc ...
	 * @param numServant the number of {@link Servant} to increase the action value
	 * @param actionSpace an integer representing the target specific {@link ActionSpace}
	 * @param family the family to be placed.
	 * @return true if the family can be placed, false otherwise
	 */
	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		int actionValue = family.getValue();
		if (0<=actionSpace && actionSpace<=NUM_SPACES && !(family.isPlaced()) && marketSpace[actionSpace].isPlayable()
				&& (marketSpace[actionSpace].controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& !player.getSpecBonus().returnBool("NoMarket")
				&& checkActionValue(numServant, marketSpace[actionSpace], family, actionValue)) {
			return true;
		}
		return false;
	}

	/**
	 * It applies the move to the player by decrementing the used {@link Servant}s, adding the family to the space,
	 * applying eventual specific effects and applying the bonus of the market to the {@link Player}
	 * @param numServant the number of {@link Servant} to increase the action value
	 * @param actionSpace an integer representing the target specific {@link ActionSpace}
	 * @param family the family to be placed.
	 */
	public void applyMove(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		applyServant(family, numServant);
		marketSpace[actionSpace].addFamily(family);
		marketSpace[actionSpace].applyBonus(player, model);
	}

	@Override
	public void reset() {
		for (int i = 0; i < NUM_SPACES; i++) {
			marketSpace[i].resetFamily();
		}
	}

	@Override
	public void setZone(int num) {
		if (num < 4) {
			marketSpace[2].setNotPlayable();
			marketSpace[3].setNotPlayable();
		}
		if(num==5){
			marketSpace[0].setFivePlayer();
			marketSpace[1].setFivePlayer();
			marketSpace[2].setFivePlayer();
			marketSpace[3].setFivePlayer();
		}

	}
	
	/**
	 * 
	 * @return an array containing the {@link MarketSpace}s
	 */
	public MarketSpace[] getMarketSpaces(){
		return this.marketSpace;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Market Zone \n");
		for(MarketSpace mark: marketSpace){
			str.append(mark.toString());
		}
		
		return str.toString();
	}

}
