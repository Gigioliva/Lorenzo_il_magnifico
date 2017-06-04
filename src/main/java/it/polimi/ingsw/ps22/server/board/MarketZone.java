package it.polimi.ingsw.ps22.server.board;

import java.util.HashMap;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

public class MarketZone extends Zone {
	private static final int NUM_SPACES = 4;
	private MarketSpace[] marketSpace;

	public MarketZone() {
		marketSpace = new MarketSpace[NUM_SPACES];
		for (int i = 0; i < NUM_SPACES; i++) {
			marketSpace[i] = new MarketSpace(1, false);
		}
		// leggere da file i bonus degli spazi azione
	}

	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		int actionValue = family.getValue();
		if (!(family.isPlaced()) && marketSpace[actionSpace].isPlayable()
				&& (marketSpace[actionSpace].controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& player.getSpecBonus().returnBool("NotMarket")
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
