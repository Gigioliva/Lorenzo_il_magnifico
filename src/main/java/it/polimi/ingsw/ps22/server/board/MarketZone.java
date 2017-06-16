package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.parser.ZoneBonusSaxParser;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class MarketZone extends Zone {
	
	private static final long serialVersionUID = 1L;
	private transient static final int NUM_SPACES = 4;
	private MarketSpace[] marketSpace;

	public MarketZone() {
		marketSpace = new MarketSpace[NUM_SPACES];
		ArrayList<HashMap<String, ResourceAbstract>> bonus=new ArrayList<HashMap<String, ResourceAbstract>>();
		ZoneBonusSaxParser.BonusRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/MarketSpace.xml",bonus);
		for (int i = 0; i < NUM_SPACES; i++) {
			marketSpace[i] = new MarketSpace(1, false);
			marketSpace[i].addBonus(bonus.get(i));
		}
	}

	public MarketZone(MarketSpace[] marketSp) {
		marketSpace = new MarketSpace[NUM_SPACES];
		for (int i=0;i<NUM_SPACES;i++) {
			this.marketSpace[i] = marketSp[i].clone(null);
		}
	}
	
	@Override
	public MarketZone clone() {
		return new MarketZone(this.marketSpace);
	}
	
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

	public void applyMove(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		applyServant(family, numServant);
		marketSpace[actionSpace].addFamily(family);
		marketSpace[actionSpace].applyBonus(player);
	}
	
	
	@Override
	public boolean cantPlaceZone(Player player){
		 HashMap<Color, Family> family = player.getAllFamily();
		 for(int i=0; i<NUM_SPACES; i++){
			 for(Color color: family.keySet()){
				 if(!family.get(color).isPlaced())
					 if(cantPlaceSpace(player.getSpecificResource("Servant").getQuantity(), i, family.get(color)))
						 return false;
			 }
		 }
		 return true;
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

	}
	
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
