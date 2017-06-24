package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.effect.BonusEffect;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.IncrementTerritory;

public class TestBonusEffect {

	Player player;
	BonusEffect eff;
	
	@Before
	public void init(){
		player = new Player("Marco", ColorPlayer.BLUE);
		
		eff = new BonusEffect();
		eff.addBonus("IncrementTerritory", new IncrementTerritory(3));
		
	}

	@Test
	public void testPerformEffect() {
		eff.performEffect(player, null);
		assert(player.getBonusAcc().getBonus("IncrementTerritory").getQuantity() == 3);
	}
}
