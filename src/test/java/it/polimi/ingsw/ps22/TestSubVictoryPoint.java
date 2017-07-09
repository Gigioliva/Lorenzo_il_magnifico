package it.polimi.ingsw.ps22;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.card.CardBuilding;
import it.polimi.ingsw.ps22.server.card.CardVenture;
import it.polimi.ingsw.ps22.server.effect.SubVictoryPoint;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;
import it.polimi.ingsw.ps22.server.resource.Wood;

/**
 * 
 * This class tests whether the {@link SubVictoryPoint} class works
 * properly or not
 *
 */

public class TestSubVictoryPoint {
	
	Player player;
	SubVictoryPoint eff;
	SubVictoryPoint eff2;
	SubVictoryPoint eff3;

	/**
	 * It initializes the {@link Player} and the three {@link SubVictoryPoint}
	 */
	
	@Before
	public void init(){
		player = new Player("Lore", ColorPlayer.BLUE);
		player.addPoints("VictoryPoint", new VictoryPoint(10));
		
		eff3 = new SubVictoryPoint("Building");
		eff2 = new SubVictoryPoint("player");
		eff = new SubVictoryPoint("Venture");
		
		CardBuilding cardB = new CardBuilding();
		cardB.addCost("Stone", new Stone(2));
		cardB.addCost("Wood", new Wood(2));
		player.addDevelopmentCard("Building", cardB);
		
		CardVenture card = new CardVenture();
		HashMap<String, ResourceAbstract> cost = new HashMap<>();
		cost.put("Stone", new Stone(2));
		cost.put("Wood", new Wood(2));
		HashMap<String, ResourceAbstract> requisite = new HashMap<>();
		requisite.put("MilitaryPoint", new MilitaryPoint(2));
		card.addRequisiteCost(cost, requisite);
		player.addDevelopmentCard("Venture", card);
		
		eff.addBonus("Stone", new Stone(1));
		eff.addBonus("Wood", new Wood(2));
		
		eff2.addBonus("VictoryPoint", new VictoryPoint(5));
		
		eff3.addBonus("Stone", new Stone(1));
		eff3.addBonus("Wood", new Wood(1));
	
	}
	
	/**
	 * this method checks whether the performEffect method of eff works
	 * correctly
	 */
	
	@Test
	public void testPerformEffect() {
		eff.performEffect(player, null);
		assert(player.getSpecificResource("VictoryPoint").getQuantity() == 7 );
	}
	
	/**
	 * this method checks whether the performEffect method of eff2 works
	 * correctly
	 */
	
	@Test
	public void testPerformEffect2(){
		
		eff2.performEffect(player, null);
		
		assert(player.getSpecificResource("VictoryPoint").getQuantity() == 8);
	}
	
	/**
	 * this method checks whether the performEffect method of eff3 works
	 * correctly
	 */
	
	@Test
	public void testPerformEffect3(){
		eff3.performEffect(player, null);
		
		assert(player.getSpecificResource("VictoryPoint").getQuantity() == 6);
	}

}
