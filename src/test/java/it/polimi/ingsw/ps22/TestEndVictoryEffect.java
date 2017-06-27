package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.effect.EndVictoryEffect;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;

public class TestEndVictoryEffect {
	
	private EndVictoryEffect eff;
	private Player player = new Player("Lore", ColorPlayer.BLUE);
	
	@Before
	public void init(){
		eff = new EndVictoryEffect(4);
	}

	@Test
	public void testPerformEffect() {
		eff.performEffect(player, null);
		
		assert(player.getSpecificResource("VictoryPoint").getQuantity() == 4);
	}

}
