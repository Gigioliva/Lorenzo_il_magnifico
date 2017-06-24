package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.effect.StrangeEffect;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;

public class TestStrangeEffect {
	
	Player player;
	StrangeEffect eff;

	@Before
	public void init(){
		player = new Player("Lore", ColorPlayer.BLUE);
		eff = new StrangeEffect("NoCostTower");
		eff.performEffect(player, null);
	}
	
	@Test
	public void testPerformEffect() {
		assert(player.getSpecBonus().returnBool("NoCostTower") == true);
	}

}
