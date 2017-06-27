package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;

public class TestGainResource {


	Player player;
	GainResource eff;
	
	@Before
	public void init(){
		player = new Player("Marco", ColorPlayer.BLUE);
		
		player.addSpecificResource("Coin", new Coin(6));
		
		player.addSpecificResource("MilitaryPoint", new MilitaryPoint(2));
		
		eff = new GainResource();
		eff.addGain("Coin", new Coin(2));
		eff.addGain("MilitaryPoint", new MilitaryPoint(3));
		
	}

	@Test
	public void testPerformEffect() {
		eff.performEffect(player, null);
		assert(player.getSpecificResource("Coin").getQuantity() == 8);
		assert(player.getSpecificResource("MilitaryPoint").getQuantity() == 5);
	}
	
	@Test
	public void testPerformEffect2() {
		eff.performEffect(player, null);
		eff.doubleGain(player);
		assert(player.getSpecificResource("Coin").getQuantity() == 10);
	}
}
