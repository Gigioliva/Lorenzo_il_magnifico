package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.effect.Effect;
import it.polimi.ingsw.ps22.server.effect.StrangeEffect;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;

/**
 * 
 * This class tests whether the {@link StrangeEffect} class works
 * properly or not
 *
 */
public class TestStrangeEffect {
	
	Player player;
	StrangeEffect eff;

	/**
	 * It initializes the {@link Player} and the {@link Effect} itself
	 */
	@Before
	public void init(){
		player = new Player("Lore", ColorPlayer.BLUE);
		eff = new StrangeEffect("NoCostTower");
		eff.performEffect(player, null);
	}
	
	/**
	 * this method checks whether the performEffect method works
	 * correctly
	 */
	@Test
	public void testPerformEffect() {
		assert(player.getSpecBonus().returnBool("NoCostTower") == true);
	}

}
