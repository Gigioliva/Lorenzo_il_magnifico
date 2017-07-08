package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.effect.EndVictoryEffect;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;

/**
 * 
 * This class tests whether the {@link EndVictoryEffect} class behaves
 * properly or not
 *
 */
public class TestEndVictoryEffect {
	
	private EndVictoryEffect eff;
	private Player player = new Player("Lore", ColorPlayer.BLUE);
	
	/**
	 * This class initializes the effect
	 */
	@Before
	public void init(){
		eff = new EndVictoryEffect(4);
	}

	/**
	 * this method checks whether the performEffect method works
	 * correctly
	 */
	@Test
	public void testPerformEffect() {
		eff.performEffect(player, null);
		
		assert(player.getSpecificResource("VictoryPoint").getQuantity() == 4);
	}

}
