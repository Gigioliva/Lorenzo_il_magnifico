package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.effect.ExchangeResource;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.FaithPoint;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.Servant;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;
import it.polimi.ingsw.ps22.server.resource.Wood;

/**
 * 
 * This class check whether the {@link ExchangeResource} class 
 * works properly or not
 *
 */
public class TestExchangeResource {

	Player player;
	ExchangeResource eff;
	ExchangeResource eff2;
	
	/**
	 * It initializes the {@link Player} and effect itself
	 */
	@Before
	public void init(){
		player = new Player("Marco", ColorPlayer.BLUE);
		
		player.addSpecificResource("Coin", new Coin(6));
		player.addSpecificResource("Wood", new Wood(3));
		player.addSpecificResource("MilitaryPoint", new MilitaryPoint(2));
		
		eff = new ExchangeResource();
		eff.addCost("Coin", new Coin(2));
		eff.addCost("MilitaryPoint", new MilitaryPoint(1));
		
		eff.addGain("VictoryPoint", new VictoryPoint(2));
		eff.addGain("Servant", new Servant(1));
		
		eff2 = new ExchangeResource();
		eff2.addCost("Wood", new Wood(3));
		
		eff2.addGain("Stone", new Stone(2));
		eff2.addGain("FaithPoint", new FaithPoint(1));
		
	}

	/**
	 * this method checks whether the performEffect method works
	 * correctly
	 */
	@Test
	public void testPerformEffect() {
		eff.performEffect(player, null);
		assert(player.getSpecificResource("Coin").getQuantity() == 4);
		assert(player.getSpecificResource("MilitaryPoint").getQuantity() == 1);
		assert(player.getSpecificResource("Wood").getQuantity() == 5);
		assert(player.getSpecificResource("Servant").getQuantity() == 4);
		assert(player.getSpecificResource("VictoryPoint").getQuantity() == 2);

	}
	
	/**
	 * this method checks whether the performEffect method works
	 * correctly
	 */
	@Test
	public void testPerformEffect2() {
		eff2.performEffect(player, null);
		assert(player.getSpecificResource("Coin").getQuantity() == 6);
		assert(player.getSpecificResource("MilitaryPoint").getQuantity() == 2);
		assert(player.getSpecificResource("Wood").getQuantity() == 2);
		assert(player.getSpecificResource("Stone").getQuantity() == 4);
		assert(player.getSpecificResource("FaithPoint").getQuantity() == 1);
	}
}
