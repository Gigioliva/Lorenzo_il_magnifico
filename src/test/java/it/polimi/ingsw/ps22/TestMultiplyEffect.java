package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.card.CardCharacter;
import it.polimi.ingsw.ps22.server.effect.MultiplyEffect;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

/**
 * 
 * This class checks whether the {@link MultiplyEffect} works
 * properly or not
 *
 */
public class TestMultiplyEffect {

	Player player;
	MultiplyEffect eff;
	MultiplyEffect eff2;
	
	/**
	 * This method initializes a new {@link Player}, and effects themselves
	 */
	@Before
	public void init(){
		player = new Player("Marco", ColorPlayer.BLUE);
		
		player.addSpecificResource("Stone", new Stone(6));
		
		player.addSpecificResource("VictoryPoint", new VictoryPoint(2));
		
		eff = new MultiplyEffect();
		eff.setMultiplicand(new VictoryPoint(2));
		eff.setMultiplicandType("VictoryPoint");
		eff.setMultiplier("Stone");
		eff.setMultiplierQty(3);
		
		eff2 = new MultiplyEffect();
		CardCharacter card = new CardCharacter();
		player.addDevelopmentCard("Character", card);
		
		eff2.setMultiplicandType("VictoryPoint");
		eff2.setMultiplier("Character");
		eff2.setMultiplierQty(1);
		eff2.setMultiplicand(new VictoryPoint(2));
		
	}

	/**
	 * this method checks whether the performEffect method works
	 * correctly
	 */
	@Test
	public void testPerformEffect() {
		eff.performEffect(player, null);
		assert(player.getSpecificResource("VictoryPoint").getQuantity() == 6);
	}
	
	/**
	 * this method checks whether the performEffect method works
	 * correctly
	 */
	@Test
	public void testPerformEffect2() {
		eff2.performEffect(player, null);
		assert(player.getSpecificResource("VictoryPoint").getQuantity() == 4);
	}
}
